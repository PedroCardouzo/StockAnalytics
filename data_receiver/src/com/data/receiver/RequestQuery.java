package com.data.receiver;
import com.data.receiver.alpha_vantage_defintions.QUERYKEYWORDS;
import com.data.receiver.config.ApiKey;
import com.data.receiver.alpha_vantage_defintions.Functions;


public class RequestQuery {

    private String function;
    private String company;
    private String interval;

    public RequestQuery () {

    }


    public RequestQuery (String function, String company, String interval) {
        this.company=company;
        this.function=function;
        this.interval=interval;
        System.out.println("Created a Request Query Object");
    }

    public String toAlphaVantageURL () {

        String url = QUERYKEYWORDS.PROTOCOL+QUERYKEYWORDS.API_URL_BASE+
                QUERYKEYWORDS.FUNCTION+this.function+QUERYKEYWORDS.AND+
                QUERYKEYWORDS.COMPANY+this.company+QUERYKEYWORDS.AND;

        switch (this.function) {
            case Functions.TIME_SERIES_INTRADAY: {url = url + QUERYKEYWORDS.INTERVAL+this.interval+QUERYKEYWORDS.AND;};break;
        }

        return
                url + QUERYKEYWORDS.API_KEY+ApiKey.ALPHA_VANTAGE_API_KEY;







    }
}
