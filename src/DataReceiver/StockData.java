package DataReceiver;

import DataReceiver.alpha_vantage_defintions.Companies;
import DataReceiver.alpha_vantage_defintions.Functions;
import DataReceiver.alpha_vantage_defintions.Intervals;
import DataReceiver.alpha_vantage_defintions.JSONKEYS;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import DataReceiver.RequestQuery;

import javax.management.Query;

public class StockData extends OCData {

    private HashMap<String, double[]> data;
    private RequestQuery query;


    public RequestQuery getQuery() {
        return this.query;
    }

    public StockData(DataReceiver.RequestQuery requestQuery){
        super (requestQuery);
        this.data = new HashMap<String, double[]>();
    }

    public StockData(double[] info, double[] alfo){
        this.data = new HashMap<String, double[]>();
        this.data.put("close", info);
        this.data.put("volume", alfo);

    }

    public StockData(String stockname, String timeSeries, String interval) {
        this.data = new HashMap<>();
        RequestHandler requestHandler = new RequestHandler();

        RequestQuery query= new RequestQuery(timeSeries,
                stockname,
                interval,
                "");
        this.query = query;

        QueryResult result;
        try {
             result = requestHandler.execute(query);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Runtime exception when executing query '" + query + "'");
            return;
        }

        try{
            this.convertToOCFormat(result.getRawData());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidQueryException();
        }

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

        JsonElement jelement = new JsonParser().parse(rawData);
        JsonObject jsonObject = jelement.getAsJsonObject();
        jsonObject = jsonObject.getAsJsonObject(JSONKEYS.resolveKeyName(jsonObject.keySet()));

        /* here I modified so that we end up getting the HashMap<String, double[]>, where String is the name of the field,
           like open, close, etc. and double is the list of data. However, I ended up making a helper method: addData, which
           takes the already existing List<Double> and converts it to double[] in O(n) linear time.
           That's not bad but I think we can do something like:
            (before declarations:)
            int arr_size = jsonObject.keySet().size() // as far as I understood, keySet() is the whole list, in which each element
            is the group of open, high, low, etc for that cell. Therefore the size of this is also the size of data we have,
            therefore we can declare like this:

            double[] open = new double[size]; instead of List<Double> open = new ArrayList<Double>();
            then in the main loop we would do a normal for loop as for(i=0; i<size; i++) and instead of
            open.add(expression) we do open[i] = expression and the conversion of Double -> double will be done automatically

        */
        List<Double> open = new ArrayList<Double>();
        List<Double> high = new ArrayList<Double>();
        List<Double> low = new ArrayList<Double>();
        List<Double> close = new ArrayList<Double>();
        List<Double> adjustedClose = new ArrayList<Double>();
        List<Double> volume = new ArrayList<Double>();
        List<Double> dividendAmount = new ArrayList<Double>();


        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext(); ) {
            String key = (String) iterator.next();
            open.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.OPEN).getAsDouble());
            high.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.HIGH).getAsDouble());
            low.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.LOW).getAsDouble());
            close.add(jsonObject.getAsJsonObject(key).get(JSONKEYS.CLOSE).getAsDouble());

            if (Functions.isAdjusted(this.query.getFunction())){
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

    public String getTimeSeries() {
        return this.query.getTimeSeries();
    }

    public String getInterval() {
        return this.query.getInterval();
    }

    public String getCompany() {
        return this.query.getCompany().toUpperCase();
    }
}
