package com.data.receiver;

import com.data.receiver.alpha_vantage_defintions.JSONKEYS;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StockQuotes extends OCData {

    private List<String> symbol = new ArrayList<String>();
    private List<Float> price = new ArrayList<Float>();
    private List<String> timeStamp = new ArrayList<String>();



    public StockQuotes (RequestQuery requestQuery){
        super (requestQuery);
    }

    public void convertToOCFormat (String rawData) {

        JsonElement jelement = new JsonParser().parse(rawData);
        JsonObject jsonObject = jelement.getAsJsonObject();
        JsonArray arr = jsonObject.getAsJsonArray(JSONKEYS.resolveKeyName(jsonObject.keySet()));

        for (JsonElement stockItem : arr) {
            JsonObject paymentObj = stockItem.getAsJsonObject();
            symbol.add(paymentObj.get(JSONKEYS.SYMBOL).getAsString());
            price.add(paymentObj.get(JSONKEYS.PRICE).getAsFloat());
            timeStamp.add(paymentObj.get(JSONKEYS.TIMESTAMP).getAsString());
        }

    }

    @Override
    public String toString () {
        Gson gson = new Gson();
        return gson.toJson(this, StockQuotes.class);
    }
}
