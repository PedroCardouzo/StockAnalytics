package Grapher;

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

    public GraphFormatData (String stock, double[] data) {
        this.stock = stock; this.data = data;

    }
}
