package DataAnalysis;
import DataReceiver.StockData;
import org.msgpack.rpc.Client;

public class DataAnalyzerRFC extends DataAnalysis{

    private Client client;
    private boolean isClientActive = false;
    private RFCiface analyzer;

    // interface with RFC system : remote system functions must be declared here
    private interface RFCiface {
        double[][] simpleMovingAverage(int size, double[][] list);
        double[][] exponentialMovingAverage(int size, double[][] list);
        double[][] rsi(int size, double[][] list);
        double[][] obv(double[][] close, double[][] volume);
    }

    public DataAnalyzerRFC(String hostname, int port) throws java.net.UnknownHostException {
        if(this.isClientActive()) // however we do not change is client active as we are setting a new client right away
            this.getClient().close();

        this.setClient(hostname, port);
    }

    private boolean isClientActive() {
        return isClientActive;
    }

    private void setClientActive(boolean clientActive) {
        isClientActive = clientActive;
    }

    private Client getClient(){
        return this.client;
    }

    private void setClient(String hostname, int port) throws java.net.UnknownHostException{
        this.client = new Client(hostname, port); // connect to the client
        this.setClientActive(true);
        this.analyzer = this.client.proxy(RFCiface.class); // RFC functions will be called from this variable
    }

    public void terminate(){  // closes connection to the server
        if(this.isClientActive())
            this.getClient().close();
            this.setClientActive(false);
    }

    // API for Remote Procedure Call:


    @Override
    public double[][] simpleMovingAverage(int sampleSize, StockData[] data, String field) {
        double[][] list = StockData.extractFieldForEach(data, field);
        return this.analyzer.simpleMovingAverage(sampleSize, list);
    }

    @Override
    public double[][] exponentialMovingAverage(int sampleSize, StockData[] data, String field) {
        double[][] list = StockData.extractFieldForEach(data, field);
        return this.analyzer.exponentialMovingAverage(sampleSize, list);
    }

    @Override
    public double[][] rsi(int period, StockData[] data, String field) {
        double[][] list = StockData.extractFieldForEach(data, field);
        return this.analyzer.rsi(period, list);
    }

    @Override
    public double[][] obv(StockData[] data) {
        double[][] close = StockData.extractFieldForEach(data, "close");
        double[][] volume = StockData.extractFieldForEach(data, "volume");
        return this.analyzer.obv(close, volume);
    }
    
}
