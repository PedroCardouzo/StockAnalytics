package com.data.receiver;

import com.data.receiver.alpha_vantage_defintions.Functions;
import com.data.receiver.alpha_vantage_defintions.JSONKEYS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TimeSeries extends OCData {

    private List<Float> open = new ArrayList<Float>();
    private List<Float> high = new ArrayList<Float>();
    private List<Float> low = new ArrayList<Float>();
    private List<Float> close = new ArrayList<Float>();
    private List<Float> adjustedClose = new ArrayList<Float>();
    private List<Float> volume = new ArrayList<Float>();
    private List<Float> dividendAmount = new ArrayList<Float>();

    public TimeSeries (RequestQuery requestQuery){
        super (requestQuery);
    }

    public void convertToOCFormat (String rawData) {

        JsonElement jelement = new JsonParser().parse(rawData);
        JsonObject jsonObject = jelement.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject(JSONKEYS.resolveKeyName(jsonObject.keySet()));

        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            this.open.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.OPEN).getAsFloat());
            this.high.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.HIGH).getAsFloat());
            this.low.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.LOW).getAsFloat());
            this.close.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.CLOSE).getAsFloat());

            if (Functions.isAdjusted(this.getQuery().getFunction())) {
                this.volume.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.ADJUSTED_VOLUME).getAsFloat());
                this.adjustedClose.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.ADJUSTED_CLOSE).getAsFloat());
                this.dividendAmount.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.DIVIDEND_AMOUNT).getAsFloat());

            } else {
                this.volume.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.VOLUME).getAsFloat());
            }

        }
    }

    @Override
    public String toString () {
        Gson gson = new Gson();
        return gson.toJson(this, TimeSeries.class);
    }
}
