package DataAnalysis;

import Parser.Temp;

// interface of a DataProcessor. Every Data processor will
public interface IDataProcessor {
    double[][] simpleMovingAverage(int size, Temp[] data, String field);
    double[][] exponentialMovingAverage(int size, Temp[] data, String field);
    double[][] rsi(int size, Temp[] data, String field);
    double[][] obv(Temp[] data);
}
