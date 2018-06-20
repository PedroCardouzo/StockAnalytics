package DataReceiver;
import DataReceiver.alpha_vantage_defintions.QUERYKEYWORDS;
import DataReceiver.config.ApiKey;
import DataReceiver.alpha_vantage_defintions.Functions;


public class RequestQuery {

    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    private String company;
    private String interval;
    private String outputSize;

    public RequestQuery () {

    }
    public RequestQuery (String function, String company, String interval, String outputSize) {
        this.company=company;
        this.function=function;
        this.interval=interval;
        this.outputSize=outputSize;
        System.out.println("Created a Request Query Object");
    }

    public String toAlphaVantageURL () {

        String url = QUERYKEYWORDS.PROTOCOL+QUERYKEYWORDS.API_URL_BASE+
                QUERYKEYWORDS.FUNCTION+this.function+QUERYKEYWORDS.AND;

        if (this.function.equals(Functions.BATCH_STOCK_QUOTES)) {
            url += QUERYKEYWORDS.COMPANIES+this.company+QUERYKEYWORDS.AND;
        } else {
            url += QUERYKEYWORDS.COMPANY+this.company+QUERYKEYWORDS.AND;

        }

        if (this.function.equals(Functions.TIME_SERIES_INTRADAY))
            url += QUERYKEYWORDS.INTERVAL+this.interval+QUERYKEYWORDS.AND;

        if (outputSize.equals("full") || outputSize.equals("compact")) {
            url += QUERYKEYWORDS.OUTPUTSIZE+this.outputSize+QUERYKEYWORDS.AND;
        }

        return url + QUERYKEYWORDS.API_KEY+ApiKey.ALPHA_VANTAGE_API_KEY;
    }

    public String getTimeSeries() {
        return this.function;
    }

    public String getCompany() {
        return company;
    }
}
