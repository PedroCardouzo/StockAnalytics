package com.data.receiver;
import com.data.receiver.RequestHandler;
import com.data.receiver.RequestQuery;
import com.data.receiver.demo.RequestQueryDemo;

public class Main {

    public static void main(String[] args) {

        RequestHandler h = new RequestHandler();
        RequestQuery q = new RequestQuery();
        RequestQueryDemo demo = new RequestQueryDemo();

        System.out.println(demo.getSample01().toAlphaVantageURL());

        try {
                String a = h.execute(demo.getSample01());
                System.out.println(a);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(demo.getSample02().toAlphaVantageURL());

        try {
            String a = h.execute(demo.getSample02());
            System.out.println(a);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
