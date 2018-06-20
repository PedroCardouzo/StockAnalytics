package Grapher;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFrame;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        double[] data1 = {1,2,3};
        double[] data2 = {4,5,6};
        double[] data3 = {7,8,9};

        List<GraphFormatData> plots = new ArrayList<GraphFormatData>();
        GraphFormatData g1 = new GraphFormatData("MSFT",data1);
        GraphFormatData g2 = new GraphFormatData("FB" ,data2);
        plots.add(g1);
        plots.add(g2);
        Grapher g = new Grapher ("Title", "MONTHLY","15min","avg", plots);
    }
}
