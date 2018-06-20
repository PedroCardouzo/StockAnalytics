package Grapher;
import java.util.List;

public class Grapher {

    private String title;
    private String function;
    private String interval;
    private String analysisType;
    private List<GraphFormatData> plots;

    public Grapher (String title, String function, String interval, String analysisType, List<GraphFormatData> plots) {
        this.title = title; this.function = function;
        this.interval = interval;this.analysisType = analysisType;this.plots = plots;

        Chart c = new Chart (this.title, this.analysisType, this.function, this.plots);
        c.setVisible(true);



   }
}
