package Parser;

import java.util.Arrays;
import java.util.HashMap;

public class Temp {
    private double[] close = {1.0, 1.0, 1.0, 1.0};
    private double[] open = {1.5, 1.5, 1.5, 1.5};
    private HashMap<String, double[]> data;

    Temp(){
        this.data = new HashMap<String, double[]>();
        this.data.put("close", this.close);
        this.data.put("open", this.open);
    }

    public double[] extractField(String field){
        return this.data.get(field);
    }

    public static double[][] extractFieldForEach(Temp[] data, String field){
        double[][] list = new double[data.length][];
        int i = 0;
        for(Temp stockData : data){
            list[i] = stockData.extractField(field);
            i++;
        }
        return list;
    }
}
