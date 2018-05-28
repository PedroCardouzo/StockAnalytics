package DataReceiver;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import DataReceiver.alpha_vantage_defintions.Functions;

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

    public QueryResult (String rawData, DataReceiver.RequestQuery query) {

        if (Functions.isStockQuotes(query.getFunction())) {
            data = new DataReceiver.StockQuotes(query);
        } else {
            data = new DataReceiver.StockData(query);
        }

        data.convertToOCFormat(rawData);


    }

}
