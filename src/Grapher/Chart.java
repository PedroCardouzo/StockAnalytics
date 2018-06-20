package Grapher;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.Title;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartPanel;

public class Chart extends JFrame{

    public Chart (String title, String analysisType, String function, List<GraphFormatData> plots) {
        super("New Chart");

        JPanel chartPanel = createChartPanel(title, analysisType, function, plots);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(String title, String analysisType, String function, List<GraphFormatData> plots) {


        XYDataset dataset = createDataset(plots);

        JFreeChart chart = ChartFactory.createXYLineChart(title,
                "X", "Y", dataset);

        return new ChartPanel(chart);
    }

    private XYDataset createDataset(List<GraphFormatData> plots) {

        /*WIP: Fill the dataSet*/
        XYSeriesCollection dataset = new XYSeriesCollection();

        plots.forEach(item -> {
            XYSeries series = new XYSeries(item.getStock());


           // series.add(counter, 2 );
        });


    
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

        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        return dataset;
    }

}

