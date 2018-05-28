package DataReceiver.demo;
import DataReceiver.alpha_vantage_defintions.Companies;
import DataReceiver.alpha_vantage_defintions.Functions;
import DataReceiver.alpha_vantage_defintions.Intervals;
import DataReceiver.RequestQuery;

public class RequestQueryDemo {


    public RequestQueryDemo (){

    }

    public RequestQuery getSample01 () {
        return new RequestQuery(Functions.TIME_SERIES_INTRADAY,
                Companies.MICROSOFT,
                Intervals.FIFTEEN_MIN,
                "full");
    }

    public RequestQuery getSample02 () {
        return new RequestQuery(Functions.TIME_SERIES_DAILY,
                                 Companies.MICROSOFT,
                                Intervals.ONE_MIN,
                                "full");
    }

    public RequestQuery getSample03 () {
        return new RequestQuery(Functions.TIME_SERIES_DAILY_ADJUSTED,
                Companies.MICROSOFT,
                "",
                "full");
    }

    public RequestQuery getSample04 () {
        return new RequestQuery(Functions.TIME_SERIES_WEEKLY,
                Companies.MICROSOFT,
                "",
                "");
    }

    public RequestQuery getSample05 () {
        return new RequestQuery(Functions.TIME_SERIES_WEEKLY_ADJUSTED,
                Companies.MICROSOFT,
                "",
                "");
    }

    public RequestQuery getSample06 () {
        return new RequestQuery(Functions.TIME_SERIES_MONTHLY,
                Companies.MICROSOFT,
                "",
                "");
    }

    public RequestQuery getSample07 () {
        return new RequestQuery(Functions.TIME_SERIES_MONTHLY_ADJUSTED,
                Companies.MICROSOFT,
                "",
                "");
    }

    public RequestQuery getSample08 () {
        return new RequestQuery(Functions.BATCH_STOCK_QUOTES,
                Companies.MICROSOFT+ "," +Companies.FACEBOOK +","+ Companies.APPLE,
                "",
                "");
    }



}
