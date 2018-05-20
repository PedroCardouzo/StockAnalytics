package DataAnalysis;

// interface of a DataProcessor. Every Data processor will
public interface IDataProcessor {
    double[][] simpleMovingAverage(int size, double[][] list);
    double[][] exponentialMovingAverage(int size, double[][] list);
    double[][] rsi(int size, double[][] list);
    double[][] obv(double[][] close, double[][] volume);
}
