package DataReceiver;

import DataReceiver.alpha_vantage_defintions.Functions;
import DataReceiver.alpha_vantage_defintions.JSONKEYS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class StockData extends OCData {

    private HashMap<String, double[]> data;

    public StockData(DataReceiver.RequestQuery requestQuery){
        super (requestQuery);
        this.data = new HashMap<String, double[]>();
    }

    public StockData(String stockname, String timeSeries, String interval){

    }

    public double[] extractField(String field){
        return this.data.get(field);
    }

    public static double[][] extractFieldForEach(StockData[] data, String field){
        double[][] list = new double[data.length][];
        int i = 0;
        for(StockData stockData : data){
            list[i] = stockData.extractField(field);
            i++;
        }
        return list;
    }

    public void convertToOCFormat (String rawData) {
        List<Double> open = new ArrayList<Double>();
        List<Double> high = new ArrayList<Double>();
        List<Double> low = new ArrayList<Double>();
        List<Double> close = new ArrayList<Double>();
        List<Double> adjustedClose = new ArrayList<Double>();
        List<Double> volume = new ArrayList<Double>();
        List<Double> dividendAmount = new ArrayList<Double>();

        JsonElement jelement = new JsonParser().parse(rawData);
        JsonObject jsonObject = jelement.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject(JSONKEYS.resolveKeyName(jsonObject.keySet()));

        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            open.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.OPEN).getAsDouble());
            high.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.HIGH).getAsDouble());
            low.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.LOW).getAsDouble());
            close.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.CLOSE).getAsDouble());

            if (Functions.isAdjusted(this.getQuery().getFunction())) {
                volume.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.ADJUSTED_VOLUME).getAsDouble());
                adjustedClose.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.ADJUSTED_CLOSE).getAsDouble());
                dividendAmount.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.DIVIDEND_AMOUNT).getAsDouble());

            } else {
                volume.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.VOLUME).getAsDouble());
            }
        }
        this.addData("open", open);
        this.addData("close", close);
        this.addData("high", high);
        this.addData("low", low);
        this.addData("volume", volume);
    }

    private void addData(String field, List<Double> data){
        double[] unwrappedData = new double[data.size()];
        int i=0;
        for(Double value : data) {
            unwrappedData[i] = value;
            i++;
        }
        this.data.put(field, unwrappedData);
    }

    @Override
    public String toString () {
        Gson gson = new Gson();
        return gson.toJson(this, StockData.class);
    }
}
