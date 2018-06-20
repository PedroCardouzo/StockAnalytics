package Parser;

import DataAnalysis.DataAnalysis;
import DataAnalysis.DataAnalyzerRFC;
import DataAnalysis.DataAnalyzerLocal;
import DataReceiver.InvalidQueryException;
import DataReceiver.StockData;
import DataReceiver.alpha_vantage_defintions.Functions;
import DataReceiver.alpha_vantage_defintions.Intervals;
import Grapher.GraphFormatData;
import Grapher.Grapher;
import javafx.util.Pair;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
//import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import javax.swing.tree.TreeNode;
import java.net.UnknownHostException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Parser {
    /****************************
     * Allowed commands:
     * pull <stock_key> <type> [<sampling_size>] as <name>
     * pull <stock_key> intraday <minutes> [<sampling_size>] as <name>
     *     where <minutes> can be: 1, 5, 15, 30 or 60 only
     * new graph <graph_name>
     * <graph_name> <- <analysis_id> <field>: <args>
     *     where
     *         <field> is the field (close, open, high, low ..) which we will get the data from
     *              <field> should be empty if using obv, for example
     *         <args> is a sequence os names of stocks that have been pulled (you may use a group here instead)
     * group <args> as <group_name>
     *     where
     *         <args> is a sequence os names of stocks that have been pulled (you may use a group here instead)
     * push <stock_key> to <group_name>
     * remove <stock_key> from <group_name>
     * show <graph_name>
     ****************************/
    HashMap<String, DataReceiver.StockData> stocks;
    HashMap<String, AnalysedData> graphs;
    HashMap<String, String> groups;
    DataAnalysis analyser;
    boolean loop;
    private static final String STOCK_SEQUENCE_REGEX = "([A-Za-z0-9]+ \\| )*[A-Za-z0-9]+";
    public Parser() throws UnknownHostException{
        this.stocks = new HashMap<String, StockData>();
        this.graphs = new HashMap<String, AnalysedData>();
        this.groups = new HashMap<String, String>();
        this.analyser = new DataAnalyzerLocal(); // DataAnalyzerRFC("localhost", 1234); //
        this.loop = false;
    }

    public static void printll(double[][] list){
        for(int i=0; i<list.length; i++){
            System.out.println("list["+i+"] ->");
            for (int j = 0; j < list[i].length; j++) {
                System.out.print(list[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        Parser parser = new Parser();
        boolean loop = true;
        Scanner input = new Scanner(System.in);

        /*
        parser.parse("pull amd monthly as amd_mon");
        parser.parse("new graph g");
        parser.parse("g <- sma close: msft_mon | amd_mon");
        parser.parse("print g");
        while(loop){
            loop = parser.parse(input.nextLine());
        }*/

    }

    private void demo(){
        this.parse("pull MSFT monthly as msft_mon");
        DataReceiver.StockData sd = this.stocks.get("msft_mon");
        Tree.Node root = null;
        for(Double d : sd.extractField("open")){
            if(root == null)
                root = new Tree.Node<Double, String>(d, sd.getCompany());
            else
                root.insert(new Tree.Node(d, sd.getCompany()));
        }
        List<Pair<Double, String>> lp = root.getAllData();
        System.out.println(lp.toString());
        System.out.println(root.find(86.125).toString());
    }

    private boolean parse(String command) {
        command = command.toLowerCase(); // case insensitive
        if(command.matches("^pull.*"))
            this.pullStock(command);
        else if(command.matches("^group.*"))
            this.addGroup(command);
        else if(command.matches("^new graph.*"))
            this.addGraph(command);
        else if(command.matches(".* <- .*"))
            this.runDataAnalysis(command);
        else if(command.matches("^show .*"))
            this.showGraph(command);
        else if(command.matches("^print.*"))
            this.printMetaData(command);
        else if(command.equals("exit"))
            return false;
        else
            System.out.println("Invalid Command. Please check spelling. You may want to use the 'help' function");

        return true;
    }

    private void printMetaData(String command) {
        command = command.substring(5);
        if(command.equals("")) {
            System.out.println("Pulled stocks:");
            for(String s : this.stocks.keySet())
                System.out.println(s);
        }else{
            String graphname = command.substring(1); // removes space
            AnalysedData ad = this.graphs.get(graphname);
            if(ad != null) {
                for (Pair<String, double[]> data : ad.data) {
                    System.out.println(data.getKey());
                    printl(data.getValue());
                }
            }else
                System.out.println("graph named '" + graphname + "' does not exists");

        }
    }

    private void printl(double[] list) {
        if(list.length > 0) {
            System.out.print("[" + list[0]);
            for (int i = 1; i < list.length; i++)
                System.out.print(", " + list[i]);
            System.out.println("]");
        }else
            System.out.println("Empty list");
    }

    private void test() {
        this.analyser = new DataAnalyzerLocal();
        int sampleSize = 4;
        StockData[] data = new StockData[2];
        String field = "only";
        double[] data1 = {1.4, 1.3, 1.98, 2.31, 3.4, 1.3, 0.9, 1.23, 3.40, 2.56, 2.00};
        double[] data2 = {1, 1, 1, 2, 3, 1, 0, 1, 3, 2, 2};
        data[0] = new StockData(data1, data2);
        data[1] = new StockData(data1, data2);
        double[][] out = this.analyser.obv(data);
        printll(out);
    }

    private class AnalysedData{
        // this class is made generic as a processed data could be integer too, live for obv function

        private String analysis;
        String timeSeries;
        String interval;
        private List<Pair<String, double[]>> data;

        AnalysedData(){
            this.data = new ArrayList<Pair<String, double[]>>();
        }

        public String toString(){
            boolean flag = false;
            String buffer =  "Analysis: " + this.getAnalysisName() + '\n' +"Time Series:" + this.getTimeSeries() + '\n';
            if(!this.getInterval().equals(""))
                buffer += "Interval: " + this.getInterval() + '\n';

            if(data.size() > 0) {
                flag = true;
                buffer += "Contains analyzed data from stocks: ";
                for (Pair<String, double[]> p : data)
                    buffer += p.getKey() + ", ";
            }

            return flag ? buffer.substring(0,buffer.length()-2) : buffer;

        }

        public void setAnalysisName(String analysisName){ this.analysis = analysisName; }

        public void addData(List<String> keys, double[][] in_data){
            for(int i=0; i<keys.size(); i++)
                this.data.add(new Pair<String, double[]>(keys.get(i), in_data[i]));
        }

        public List<Pair<String, double[]>> getData(){ return new ArrayList<Pair<String,double[]>>(this.data); }

        public void setTimeSeries(String timeSeries) {
            this.timeSeries = timeSeries;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }

        public String getAnalysisName() {
            return analysis.toUpperCase();
        }

        public String getInterval() {
            return interval.toLowerCase();
        }

        public String getTimeSeries() {
            return timeSeries.toUpperCase();
        }
    }

    private void showGraph(String command) {
        String graphname = command.substring(5);
        AnalysedData data = this.graphs.get(graphname);
        System.out.println(data.toString());
        List<GraphFormatData> plots = new ArrayList<>();
        for(Pair<String, double[]> p : data.getData()){
            plots.add(new GraphFormatData(p));
        }
        System.out.println("graph to be shown");
        Grapher graph = new Grapher(graphname, data.getTimeSeries(), data.getInterval(), data.getAnalysisName(), plots);
        System.out.println("graph shown");
    }

    // this method receives the command that is supposed to be an data analysis execution and break the command into its
    // essential parts
    private void runDataAnalysis(String command) {
        String[] args = command.split(" <- ");
        AnalysedData graph = this.graphs.get(args[0]);
        args = args[1].split(": ");

        String[] analysis_type = args[0].split(" ");
        String analysis_function = analysis_type[0];

        String analysis_field = "";
        if(analysis_type.length == 2) // some calls don't have fields to chose from (like with obv function, from IDataProcessor)
            analysis_field = analysis_type[1];

        String to_analyse = this.groups.get(args[1]); // assume args[1] is a group name
        if(to_analyse == null) // if key doesn't exists, we have the args directly instead, therefore just pass args[1] directly to to_analyse
            to_analyse = args[1];

        String[] stock_keys = to_analyse.split(" \\| "); // split at each ' | '

        this.analyse(graph, analysis_function, analysis_field, stock_keys);
    }

    // this function receives the graph where the data will go, the analysis function chosen by the user, the field which
    // these functions will be applied to (if any) and builds the data array using the provided stock_keys
    // it then decides which function from the analyser interface should be called
    // it processes the data and then pushed to graph the result.
    private void analyse(AnalysedData graph, String analysis_function, String analysis_field, String[] stock_keys) /*throws ValueException*/{
        StockData[] data = new StockData[stock_keys.length];
        List<String> keys = new ArrayList<String>();
        int i=0;
        for(String key : stock_keys){
            data[i] = this.stocks.get(key);
            if(data[i] == null) {
                System.out.println("Key " + key + " doesn't exists. Please use pull function to obtain data or check for misspelling"); //throw new ValueException("Key " + key + "doesn't exists. Please use pull function to obtain data or check for misspelling");
                return;
            }
            keys.add(data[i].getCompany());

            i++;
        }


        double[][] out;
        if(analysis_function.equals("sma")) {
            out = this.analyser.simpleMovingAverage(10, data, analysis_field);
        }else if(analysis_function.equals("ema")) {
            out = this.analyser.exponentialMovingAverage(10, data, analysis_field);
        }else if(analysis_function.equals("rsi")) {
            out = this.analyser.rsi(10, data, analysis_field);
        }else if(analysis_function.equals("obv")) {
            out = this.analyser.obv(data);
        }else {
            System.out.println("Chosen function '" + analysis_function + "' doesn't exists. Please check spelling"); // throw new ValueException("Chosen function '" + analysis_function + "' doesn't exists. Please check spelling");
            return;
        }
        graph.setAnalysisName(analysis_function);
        graph.addData(keys, out);
        graph.setTimeSeries(data[0].getTimeSeries());
        graph.setInterval(data[0].getInterval());

    }

    private void addGraph(String command){
        command = command.substring(10); // removes "new graph "
        this.graphs.put(command, new AnalysedData());
    }

    private void addGroup(String command) {
        command = command.substring(6); // removes "group "
        if(!command.matches(Parser.STOCK_SEQUENCE_REGEX)) {
            System.out.println("Command Regex not matched. Please use the proper pattern: " + Parser.STOCK_SEQUENCE_REGEX);
            return;
        }
        String[] aux = command.split(" as ");
        this.groups.put(aux[1], aux[0]);
    }

    private void pullStock(String command) /*throws ValueException*/ {
        StockData stock;

        command = command.substring(5); // removes "pull "
        String[] aux = command.split(" as ");
        String[] req = aux[0].split(" ");
        String name;

        if(aux.length == 1) // if no name was provided, the name is the stock name
            name = req[0];
        else
            name = aux[1];

        req[1] = Functions.transformInputIntoFormalParameter(req[1]);
        try {
            if (req.length == 2)
                stock = this.makeRequest(req[0], req[1]);
            else if (req.length == 3)
                stock = this.makeRequest(req[0], req[1], Intervals.transformInputIntoFormalParameter(req[2]));
            else
                stock = this.makeRequest(req[0], req[1], req[2]);
            // throw new ValueException("Required stock wasn't found or command was ill structured");
        }catch(InvalidQueryException ieq){
            ieq.printStackTrace();
            System.out.println("Query was invalid or API took to long to answer. Please try again and re-check query validity");
            stock = null;
        }
        this.stocks.put(name, stock);
    }

    private StockData makeRequest(String stockname, String timeSeries){
        return this.makeRequest(stockname, timeSeries, "");
    }

    private StockData makeRequest(String stockname, String timeSeries, String interval){
        System.out.println("Make Request ::");
        System.out.println(stockname);
        System.out.println(timeSeries);
        System.out.println(interval);
        return new StockData(stockname, timeSeries, interval);
    }
}

