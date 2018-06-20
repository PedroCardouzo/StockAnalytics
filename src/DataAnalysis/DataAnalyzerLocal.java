package DataAnalysis;

import DataReceiver.StockData;

public class DataAnalyzerLocal extends DataAnalysis {

    @Override
    public double[][] simpleMovingAverage(int sampleSize, StockData[] data, String field) {
        double[][] stock_data = StockData.extractFieldForEach(data, field);
        double[][] output = new double[data.length][];

        for(int i = 0; i < stock_data.length; i++) {
            output[i] = new double[stock_data[i].length-sampleSize+1];
            int j = 0;
            double acc=0;
            while(j < stock_data[i].length-sampleSize){
                acc = 0;
                for (int a = 0; a < sampleSize; a++)
                    acc += stock_data[i][j+a];

                output[i][j] = acc/sampleSize;
                j++;
            }
            acc = 0;
            for(int a = stock_data[i].length-sampleSize; a < stock_data[i].length; a++)
                acc += stock_data[i][a];
            output[i][output[i].length-1] = acc/sampleSize;
        }

        return output;
    }

    @Override
    public double[][] exponentialMovingAverage(int sampleSize, StockData[] data, String field) {
        return new double[0][];
    }

    @Override
    public double[][] rsi(int period, StockData[] data, String field) {
        double[][] stock_data = StockData.extractFieldForEach(data, field);
        double[][] output = new double[data.length][];

        for(int i = 0; i < stock_data.length; i++) {

            int i_len = (int)Math.ceil(((double) stock_data[i].length)/period);
            output[i] = new double[i_len];
            int ind = 0;

            double up = 0, down = 0;
            for(int j=period; j < stock_data[i].length; j += period){

                up = 0; down = 0;
                for(int k=j-period; k<j-1; k++){
                    double head = stock_data[i][k] - stock_data[i][k+1];
                    if(head >= 0)
                        up += head;
                    else
                        down += head;
                }
                output[i][ind] = up/(up-down); // up - down because down was negative
                ind++;
            }
            int n = stock_data[i].length%period;
            up = 0; down = 0;
            for(int a=stock_data[i].length-n; a < stock_data[i].length-1; a++){
                double head = stock_data[i][a] - stock_data[i][a+1];
                if(head >= 0)
                    up += head;
                else
                    down += head;
            }
            output[i][ind] = n > 1 ? up/(up-down) : 0.0; // up - down because down was negative
        }

        return output;
    }

    @Override
    public double[][] obv(StockData[] data) {
        double[][] output = new double[data.length][];

        for(int i = 0; i < data.length; i++) {


            double[] volume = data[i].extractField("volume");
            double[] close = data[i].extractField("close");

            int length = Math.min(volume.length, close.length)-1;
            output[i] = new double[length];
            double acc = 0;
            for(int j=length; j>=1; j--){
                if(close[j-1] > close[j])
                    acc += volume[j-1];
                else if(close[j-1] < close[j])
                    acc -= volume[j-1];
                output[i][j-1] = acc;
            }
        }

        return output;
    }


    @Override
    public void terminate() {

    }
}
