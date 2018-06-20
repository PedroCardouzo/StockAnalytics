package DataReceiver;
import DataReceiver.RequestHandler;
import DataReceiver.RequestQuery;
import DataReceiver.alpha_vantage_defintions.Companies;
import DataReceiver.alpha_vantage_defintions.Functions;
import DataReceiver.demo.RequestQueryDemo;
import java.util.List;
import DataReceiver.StockData;



public class Main {

    public static void main(String[] args) {

        /*@Pedro,
        *   Now you're able to do that. Please, import Companies, Functions, etc from
        *       alpha_vantage_definitions folder.
        *           I guess it will be easier for you
        *
        *    Also, you can get the query you asked using sd.getQuery(). It will return
        *       a RequestQuery object. Maybe it will be useful somehow*/


        StockData sd = new StockData(Companies.MICROSOFT,Functions.TIME_SERIES_DAILY,"");
        }
}
