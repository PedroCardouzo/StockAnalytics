package DataReceiver;

public class OCData {

    private DataReceiver.RequestQuery query;


    public OCData () {
    }

    public OCData ( DataReceiver.RequestQuery query) {
        this.setQuery(query);
    }

    public void convertToOCFormat (String rawData) {
    }

    public DataReceiver.RequestQuery getQuery() {
        return query;
    }

    public void setQuery(DataReceiver.RequestQuery query) {
        this.query = query;
    }

}
