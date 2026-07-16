package org.application.mars.Common.utils;

import java.time.Duration;
import java.time.Instant;

public class Constant {
    public static final String X_REQUESTED_WITH = "X-Requested-With";
    public static final Duration TICKER_TTL = Duration.ofHours(24);
    public static final Integer YEARS_OF_DATA = 5;
    public static final String VOO = "VOO";

    public static boolean isStale(Instant accessed) {
        return accessed == null || accessed.isBefore(Instant.now().minus(TICKER_TTL));
    }
}
