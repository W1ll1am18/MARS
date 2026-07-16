--Look up tables
CREATE TABLE locale (
    locale      VARCHAR(20) PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE market (
    market      VARCHAR(20) PRIMARY KEY,
    description VARCHAR(255)
);

CREATE TABLE ticker_type (
    code        VARCHAR(20) PRIMARY KEY,
    description VARCHAR(255),
    asset_class VARCHAR(50)
);

-- Users
CREATE TABLE users (
    user_id         UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    username        VARCHAR(50) UNIQUE NOT NULL,
    email           VARCHAR(255) UNIQUE NOT NULL,
    password_hash   VARCHAR(255) NOT NULL
);

-- Ticker (core reference table)
CREATE TABLE ticker (
    ticker_id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    active               BOOLEAN,
    base_currency_name   VARCHAR(255),
    base_currency_symbol VARCHAR(255),
    cik                  VARCHAR(10),
    composite_figi       VARCHAR(50),
    currency_name        VARCHAR(50),
    currency_symbol      VARCHAR(50),
    delisted_utc         TIMESTAMPTZ,
    last_updated_utc     TIMESTAMPTZ,
    locale               VARCHAR(20) NOT NULL REFERENCES locale (locale),
    market               VARCHAR(20) NOT NULL REFERENCES market (market),
    name                 VARCHAR(255) NOT NULL,
    primary_exchange     VARCHAR(50),
    share_class_figi     VARCHAR(50),
    ticker               VARCHAR(10) NOT NULL,
    type                 VARCHAR(20) REFERENCES ticker_type (code),
    accessed             TIMESTAMPTZ
);

CREATE UNIQUE INDEX idx_ticker_exchange ON ticker (ticker, primary_exchange);
CREATE UNIQUE INDEX idx_ticker_composite_figi ON ticker (composite_figi) WHERE composite_figi IS NOT NULL;
CREATE INDEX idx_ticker_cik ON ticker (cik);
CREATE INDEX idx_ticker_type ON ticker (type);

-- Ticker company info (1:1 extension of ticker)
CREATE TABLE ticker_company_info (
    ticker_id                      BIGINT PRIMARY KEY REFERENCES ticker (ticker_id),
    description                    TEXT,
    homepage_url                   VARCHAR(255),
    list_date                      DATE,
    market_cap                     BIGINT,
    phone_number                   VARCHAR(20),
    round_lot                      INTEGER,
    share_class_shares_outstanding BIGINT,
    sic_code                       VARCHAR(4),
    sic_description                VARCHAR(255),
    ticker_root                    VARCHAR(10),
    ticker_suffix                  VARCHAR(10),
    total_employees                BIGINT,
    weighted_shares_outstanding    BIGINT,
    accessed                       TIMESTAMPTZ
);

CREATE INDEX idx_company_market_cap ON ticker_company_info (market_cap);

-- Ticker financials also an extension of ticker
CREATE TABLE ticker_financials (
   ticker_id                BIGINT PRIMARY KEY REFERENCES ticker (ticker_id),

-- Valuation
   pe_ratio_ttm             DOUBLE PRECISION,
   pe_ratio_annual          DOUBLE PRECISION,
   forward_pe               DOUBLE PRECISION,
   price_to_book            DOUBLE PRECISION,
   peg_ratio                DOUBLE PRECISION,
   forward_peg_ratio        DOUBLE PRECISION,
   ev_ebitda_ttm            DOUBLE PRECISION,
   ev_free_cash_flow        DOUBLE PRECISION,
   price_to_free_cash_flow  DOUBLE PRECISION,
   enterprise_value         DOUBLE PRECISION,
   dividend_yield           DOUBLE PRECISION,
   dividend_per_year        DOUBLE PRECISION,

-- Price metrics
   week_high_52             DOUBLE PRECISION,
   week_high_52_date        VARCHAR(10),
   week_low_52              DOUBLE PRECISION,
   week_low_52_date         VARCHAR(10),
   beta                     DOUBLE PRECISION,
   eps_ttm                  DOUBLE PRECISION,

-- Profitability
   gross_margin_ttm         DOUBLE PRECISION,
   gross_margin_annual      DOUBLE PRECISION,
   operating_margin_ttm     DOUBLE PRECISION,
   net_profit_margin_ttm    DOUBLE PRECISION,
   net_profit_margin_annual DOUBLE PRECISION,
   return_on_equity         DOUBLE PRECISION,
   return_on_assets         DOUBLE PRECISION,
   return_on_investment     DOUBLE PRECISION,
   payout_ratio             DOUBLE PRECISION,

-- Financial health
   current_ratio            DOUBLE PRECISION,
   quick_ratio              DOUBLE PRECISION,
   debt_to_equity           DOUBLE PRECISION,

-- Growth
   revenue_growth_yoy       DOUBLE PRECISION,
   revenue_growth_3y        DOUBLE PRECISION,
   revenue_growth_5y        DOUBLE PRECISION,
   eps_growth_yoy           DOUBLE PRECISION,
   eps_growth_3y            DOUBLE PRECISION,
   eps_growth_5y            DOUBLE PRECISION,

   accessed                 TIMESTAMPTZ
);

-- Saved stocks (user <-> ticker, many-to-many)
CREATE TABLE saved_stocks (
    user_id   UUID NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    ticker_id BIGINT NOT NULL REFERENCES ticker (ticker_id),
    accessed  TIMESTAMPTZ NOT NULL,
    PRIMARY KEY (user_id, ticker_id)
);

-- Ticker relation (self-referencing many-to-many)
CREATE TABLE ticker_relation (
    ticker_id         BIGINT NOT NULL REFERENCES ticker (ticker_id),
    related_ticker_id BIGINT NOT NULL REFERENCES ticker (ticker_id),
    grouping          VARCHAR(20) NOT NULL,

    CHECK (ticker_id < related_ticker_id),
    PRIMARY KEY (ticker_id, related_ticker_id, grouping)
);

-- Price (daily bars only for now)
CREATE TABLE price (
    ticker_id  BIGINT NOT NULL REFERENCES ticker (ticker_id),
    trade_date DATE NOT NULL,
    open       NUMERIC(12,4) NOT NULL,
    high       NUMERIC(12,4) NOT NULL,
    low        NUMERIC(12,4) NOT NULL,
    close      NUMERIC(12,4) NOT NULL,
    volume     BIGINT NOT NULL,
    PRIMARY KEY (ticker_id, trade_date)
);

-- Market Holidays
CREATE TABLE market_holiday (
    holiday_id  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    exchange VARCHAR(20) NOT NULL,
    holiday_date DATE NOT NULL,
    name VARCHAR(100),
    status VARCHAR(20) NOT NULL,
    open_time TIMESTAMPTZ,
    close_time TIMESTAMPTZ,

    UNIQUE(exchange, holiday_date)
);