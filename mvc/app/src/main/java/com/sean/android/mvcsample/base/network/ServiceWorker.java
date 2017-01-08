package com.sean.android.mvcsample.base.network;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Seonil on 2016-12-05.
 */

public abstract class ServiceWorker<T> implements Callback<T> {

    protected Retrofit retrofit;
    protected Call<T> serviceCall;
    protected ServiceWorkEventListener serviceWorkEventListener;

    public ServiceWorker() {
        this.retrofit = createRetrofit();

    }

    public void executeAsync() {
        if(serviceWorkEventListener != null) {
            serviceWorkEventListener.onPreExecute();
        }

        if(serviceCall == null) {
            this.serviceCall = createService();
        }

        if (serviceCall != null && !serviceCall.isExecuted()) {
            serviceCall.enqueue(this);
        }
    }

    public Response<T> execute() throws IOException {
        if(serviceWorkEventListener != null) {
            serviceWorkEventListener.onPreExecute();
        }

        if(serviceCall == null) {
            this.serviceCall = createService();
        }
        Response<T> responseData = null;

        if (serviceCall != null) {
            responseData = serviceCall.execute();
        }

        return responseData;
    }

    protected Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getURLAddress())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()) {
            HttpResponseData<T> httpResponseData = new HttpResponseData<>();
            httpResponseData.setStatusCode(response.code());
            httpResponseData.setResponseData(response.body());
            httpResponseData.setResponseHeaderMap(getHeaderMap(response.headers()));


            if(serviceWorkEventListener != null) {
                serviceWorkEventListener.onComplete(httpResponseData);
            }

        } else {
            if(serviceWorkEventListener != null) {
                String errorBodyStr = "";
                if(response.errorBody() != null) {
                    try {
                        errorBodyStr = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                serviceWorkEventListener.onFail(new Throwable("Service on Failed\n" + errorBodyStr));
            }
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if (serviceWorkEventListener != null) {
            serviceWorkEventListener.onFail(t);
        }
    }

    private Map<String, String> getHeaderMap(Headers headers) {
        Map<String, String> headerMap = new LinkedHashMap<>();

        Iterator<String> iterator = headers.names().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = headers.get(key);
            headerMap.put(key, value);
        }

        return headerMap;
    }

    public void setServiceWorkEventListener(ServiceWorkEventListener serviceWorkEventListener) {
        this.serviceWorkEventListener = serviceWorkEventListener;
    }

    protected abstract Call<T> createService();


    public static interface ServiceWorkEventListener {
        void onPreExecute();

        void onComplete(HttpResponseData result);

        void onFail(Throwable e);
    }


    protected abstract String getURLAddress();
}
