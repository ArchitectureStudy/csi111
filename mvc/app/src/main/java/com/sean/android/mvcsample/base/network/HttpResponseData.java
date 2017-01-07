package com.sean.android.mvcsample.base.network;

import java.util.Map;

/**
 * Created by Seonil on 2016-12-05.
 */

public class HttpResponseData<T> {

    private int statusCode = 0;
    private Map<String, String> responseHeaderMap;
    private T responseData;


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Map<String, String> getResponseHeaderMap() {
        return responseHeaderMap;
    }

    public void setResponseHeaderMap(Map<String, String> responseHeaderMap) {
        this.responseHeaderMap = responseHeaderMap;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}
