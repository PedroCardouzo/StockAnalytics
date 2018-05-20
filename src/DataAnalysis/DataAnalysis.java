package DataAnalysis;

public abstract class DataAnalysis implements IDataProcessor{
    abstract public void terminate(); // free resources (databases, sockets, etc) related to the data analysis
}
