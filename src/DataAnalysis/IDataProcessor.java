package DataAnalysis;

import DataReceiver.StockData;

// interface of a DataProcessor. Every Data processor will
public interface IDataProcessor {
    double[][] simpleMovingAverage(int sampleSize, StockData[] data, String field);
    double[][] exponentialMovingAverage(int sampleSize, StockData[] data, String field);
    double[][] rsi(int period, StockData[] data, String field);
    double[][] obv(StockData[] data);
}
