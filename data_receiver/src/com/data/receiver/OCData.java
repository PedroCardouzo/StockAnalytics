package com.data.receiver;

public class OCData {

    private RequestQuery query;


    public OCData () {
    }

    public OCData ( RequestQuery query) {
        this.setQuery(query);
    }

    public void convertToOCFormat (String rawData) {
    }

    public RequestQuery getQuery() {
        return query;
    }

    public void setQuery(RequestQuery query) {
        this.query = query;
    }

}
