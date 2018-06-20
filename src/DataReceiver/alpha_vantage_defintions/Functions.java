package DataReceiver.alpha_vantage_defintions;

import java.security.InvalidParameterException;

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
        return function.contains("ADJUSTED");
    }

    public static final Boolean isStockQuotes (String function) {
        return function.contains("STOCK_QUOTES");
    }

    public static String transformInputIntoFormalParameter(String input){
        switch(input) {
            case "intraday": return Functions.TIME_SERIES_INTRADAY;
            case "daily": return Functions.TIME_SERIES_DAILY;
            case "weekly": return Functions.TIME_SERIES_WEEKLY;
            case "monthly": return Functions.TIME_SERIES_MONTHLY;
            default: throw new InvalidParameterException("Invalid Parameter: " + input);
        }
    }

}

