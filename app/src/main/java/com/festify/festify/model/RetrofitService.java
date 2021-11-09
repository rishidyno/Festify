package com.festify.festify.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
//    private static final String BASE_URL = "http://34.131.78.211";
    private static final String BASE_URL = "https://festify-iiitl.herokuapp.com/";
    private static RetrofitService instance = null;
    private static RetrofitApi api;

    RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(RetrofitApi.class);
    }

    public static synchronized RetrofitService getInstance() {
        if (instance == null)
            instance = new RetrofitService();
        return instance;
    }
    public RetrofitApi getMyapi(){
        return api;
    }
}
