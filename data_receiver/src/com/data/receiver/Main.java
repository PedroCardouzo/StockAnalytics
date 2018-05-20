package com.data.receiver;
import com.data.receiver.RequestHandler;
import com.data.receiver.RequestQuery;
import com.data.receiver.demo.RequestQueryDemo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
            QueryResult result = requestHandler.execute(demo.getSample01());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample02());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample03());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample04());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample05());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample06());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample07());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            QueryResult result = requestHandler.execute(demo.getSample08());
            System.out.println(result.getData());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
