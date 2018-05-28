package DataAnalysis;

import DataReceiver.StockData;

// interface of a DataProcessor. Every Data processor will
public interface IDataProcessor {
    double[][] simpleMovingAverage(int size, StockData[] data, String field);
    double[][] exponentialMovingAverage(int size, StockData[] data, String field);
    double[][] rsi(int size, StockData[] data, String field);
    double[][] obv(StockData[] data);
}
