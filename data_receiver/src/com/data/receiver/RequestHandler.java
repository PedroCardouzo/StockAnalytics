package com.data.receiver;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RequestHandler {

    public RequestHandler(){
        System.out.println("Create a new Request Handler Object");
    }

    public String a (String b) {
        return b;
    }

    public String execute(RequestQuery query) throws Exception {

            String urlToRead = query.toAlphaVantageURL();
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
            return result.toString();
        }
}

/*
    TODO:
        Tirar o try catch do usuário;
        Adicionar o outputSize como opçao (talvez criar um construtor para cada coisa);
*/
