package com.data.receiver.demo;
import com.data.receiver.alpha_vantage_defintions.Companies;
import com.data.receiver.alpha_vantage_defintions.Functions;
import com.data.receiver.alpha_vantage_defintions.Intervals;
import com.data.receiver.RequestQuery;

public class RequestQueryDemo {


    public RequestQueryDemo (){

    }

    public RequestQuery getSample01 () {
        return new RequestQuery(Functions.TIME_SERIES_INTRADAY,
                                 Companies.MICROSOFT,
                                Intervals.ONE_MIN);
    }

    public RequestQuery getSample02 () {
        return new RequestQuery(Functions.TIME_SERIES_DAILY_ADJUSTED,
                Companies.MICROSOFT,
                "");
    }

}
