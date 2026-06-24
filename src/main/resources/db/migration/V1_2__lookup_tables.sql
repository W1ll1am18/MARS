INSERT INTO market (market, description) VALUES
    ('stocks', 'US stock market equities'),
    ('crypto', 'Cryptocurrency markets'),
    ('fx', 'Foreign exchange markets'),
    ('indices', 'Market indices'),
    ('otc', 'Over-the-counter markets');

INSERT INTO locale (locale, description) VALUES
    ('us', 'United States'),
    ('global', 'Global/international markets');

INSERT INTO ticker_type (code) VALUES
    ('CS'), ('ADRC'), ('ADRP'), ('ADRR'), ('UNIT'), ('RIGHT'), ('PFD'),
    ('FUND'), ('SP'), ('WARRANT'), ('INDEX'), ('ETF'), ('ETN'), ('OS'),
    ('GDR'), ('OTHER'), ('NYSR'), ('AGEN'), ('EQLK'), ('BOND'), ('ADRW'),
    ('BASKET'), ('LT'), ('ETS'), ('ETV'), ('NYRS');