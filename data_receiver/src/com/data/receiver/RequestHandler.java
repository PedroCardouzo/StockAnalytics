package com.data.receiver;


import com.google.gson.Gson;

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RequestHandler {

    public RequestHandler() {}

    public QueryResult execute(RequestQuery query) throws Exception {

            System.out.println("Requesting URL: "+ query.toAlphaVantageURL());

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


            return new QueryResult(result.toString(),query);
        }
}

/*
    TODO:
        Adicionar o outputSize como opçao (talvez criar um construtor para cada coisa);
        implementar o batch stock quotes (multiplas empresas);
        converter para o formato esperado pelo Haskell;
        lidar melhor com o user input (se várias queries são necessárias por exemplo
                                       ou se o OutputSize precisa ser full);


*/
