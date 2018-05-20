package com.data.receiver;
import com.data.receiver.alpha_vantage_defintions.JSONKEYS;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import org.json.*;
import java.util.Iterator;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;

import com.data.receiver.RequestQuery;
import com.data.receiver.alpha_vantage_defintions.Functions;

import java.lang.reflect.Array;


public class QueryResult {

    private OCData data;

    public OCData getData() {
        return data;
    }

    public void setData(OCData data) {
        this.data = data;
    }

    public QueryResult (){
    }

    public QueryResult (String rawData, RequestQuery query) {

        if (Functions.isStockQuotes(query.getFunction())) {
            data = new StockQuotes(query);
        } else {
            data = new TimeSeries(query);
        }

        data.convertToOCFormat(rawData);


    }

}
