package com.data.receiver.alpha_vantage_defintions;

public class Functions {

    public static final String TIME_SERIES_INTRADAY = "TIME_SERIES_INTRADAY";
    public static final String TIME_SERIES_DAILY = "TIME_SERIES_DAILY";
    public static final String TIME_SERIES_DAILY_ADJUSTED = "TIME_SERIES_DAILY_ADJUSTED";
    public static final String TIME_SERIES_WEEKLY = "TIME_SERIES_WEEKLY";
    public static final String TIME_SERIES_WEEKLY_ADJUSTED = "TIME_SERIES_WEEKLY_ADJUSTED";
    public static final String TIME_SERIES_MONTHLY = "TIME_SERIES_MONTHLY";
    public static final String TIME_SERIES_MONTHLY_ADJUSTED = "TIME_SERIES_MONTHLY_ADJUSTED";
    public static final String BATCH_STOCK_QUOTES= "BATCH_STOCK_QUOTES";

    public static final Boolean isAdjusted (String function) {
        return function.contains("ADJUSTED")? true: false;
    }

    public static final Boolean isStockQuotes (String function) {
        return function.contains("STOCK_QUOTES")? true: false;

    }

}

