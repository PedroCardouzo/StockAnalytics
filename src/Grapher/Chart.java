package Grapher;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;

import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Chart extends JFrame{

    public Chart (String title, String analysisType, String function, String interval, List<GraphFormatData> plots) {
        super(analysisType + " " +function + " " + interval);

        JPanel chartPanel = createChartPanel(title, analysisType, function, interval, plots);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(String title, String analysisType, String function, String interval, List<GraphFormatData> plots) {


        XYDataset dataset = createDataset(plots);

        JFreeChart chart = ChartFactory.createXYLineChart(title,
                "X    " + (interval!=""? "Interval: "+interval:""), "Y", dataset);

        customizeChart(chart.getXYPlot());

        return new ChartPanel(chart);
    }

    private XYDataset createDataset(List<GraphFormatData> plots) {

        XYSeriesCollection dataset = new XYSeriesCollection();

        plots.forEach(item -> {
            XYSeries series = new XYSeries(item.getStock());

            double xAxis = 0;

            for (double value : item.getData()) {
                series.add(xAxis++, value);
            }
            dataset.addSeries(series);
        });

        return dataset;


/*
        XYSeries series1 = new XYSeries("Object 1");
        XYSeries series2 = new XYSeries("Object 2");
        XYSeries series3 = new XYSeries("Object 3");

        int xAxis;

        series1.add(1.0, 2.0);
        series1.add(2.0, 3.0);
        series1.add(3.0, 2.5);
        series1.add(3.5, 2.8);
        series1.add(4.2, 6.0);

        series2.add(2.0, 1.0);
        series2.add(2.5, 2.4);
        series2.add(3.2, 1.2);
        series2.add(3.9, 2.8);
        series2.add(4.6, 3.0);

        series3.add(1.2, 4.0);
        series3.add(2.5, 4.4);
        series3.add(3.8, 4.2);
        series3.add(4.3, 3.8);
        series3.add(4.5, 4.0);


        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
*/
    }

    public void customizeChart (XYPlot plot) {
        plot.getDomainAxis().setTickLabelsVisible(false);
        plot.setBackgroundPaint(Color.DARK_GRAY);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

    }

}

