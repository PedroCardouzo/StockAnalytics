import DataAnalysis.*;

public class StockAnalytics {

    public static void main(String[] args) throws java.lang.Exception {

        DataAnalysis analyzer = new DataAnalyzerRFC("localhost", 1333);


        double[][] sample = {{0.0, 1.2, 2.4, 3.5999999999999996, 4.8, 6.0, 7.199999999999999, 8.4, 9.6, 10.799999999999999, 12.0, 13.2, 14.399999999999999, 15.6, 16.8, 18.0, 19.2, 20.4, 21.599999999999998, 22.8, 24.0, 25.2, 26.4, 27.599999999999998, 28.799999999999997, 30.0, 31.2, 32.4, 33.6, 34.8, 36.0, 37.199999999999996, 38.4, 39.6, 40.8, 42.0, 43.199999999999996, 44.4, 45.6, 46.8, 48.0, 49.199999999999996, 50.4, 51.6, 52.8, 54.0, 55.199999999999996, 56.4, 57.599999999999994, 58.8, 60.0, 61.199999999999996, 62.4, 63.599999999999994, 64.8, 66.0, 67.2, 68.39999999999999, 69.6, 70.8, 72.0, 73.2, 74.39999999999999, 75.6, 76.8, 78.0, 79.2, 80.39999999999999, 81.6, 82.8, 84.0, 85.2, 86.39999999999999, 87.6, 88.8, 90.0, 91.2, 92.39999999999999, 93.6, 94.8, 96.0, 97.2, 98.39999999999999, 99.6, 100.8, 102.0, 103.2, 104.39999999999999, 105.6, 106.8, 108.0, 109.2, 110.39999999999999, 111.6, 112.8, 114.0, 115.19999999999999, 116.39999999999999, 117.6, 118.8}};
        sample = analyzer.simpleMovingAverage(10, sample);
        for(double[] row : sample){
            for(double el : row){
                System.out.print(el);
                System.out.print(", ");
            }
            System.out.println("");
        }

        // closes connection to database, socket, etc. depending on what kind of analyzer you are using
        analyzer.terminate();

        /*
        System.out.println("Test");
        Client client = new Client("localhost", 1236);
        // int[][] list = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}};
        int[] x = {2};
        java.lang.Object[] arg = {x};
        Value y = client.callApply("func", arg);

        System.out.println(y);
        //*/
    }
}

