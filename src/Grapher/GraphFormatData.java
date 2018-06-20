package Grapher;

import javafx.util.Pair;

public class GraphFormatData {

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    private String stock = "UNDEFINED";

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    private double[] data;

    public GraphFormatData (Pair<String, double[]> pair ) {
        this.stock = pair.getKey(); this.data = pair.getValue();
    }
}
