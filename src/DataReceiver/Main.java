package DataReceiver;
import DataReceiver.RequestHandler;
import DataReceiver.RequestQuery;
import DataReceiver.demo.RequestQueryDemo;
import java.util.List;



public class Main {

    public static void main(String[] args) {

        RequestHandler requestHandler = new RequestHandler();
        RequestQueryDemo demo = new RequestQueryDemo();

        /*@Pedro and @Adilson, below you will find some useful samples using the demo apiKey. If you
        want to use your own apiKey, just change it on .conf/apiKey.java */

/*        The main idea is:
        You have a RequestHandler (HTTP requester) which receives a RequestQuery as parameter
        and it returns a QueryResult (Time Series or Stock Quotes type) which on it's data field
        has the OC (Os Corretores) data format;*/

        /* FYI: demo.getSampleXX() actually returns a RequestQuery template*/

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample01());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample02());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample03());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample04());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample05());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample06());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample07());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            DataReceiver.QueryResult result = requestHandler.execute(demo.getSample08());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
