package com.festify.festify.model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//why is all the member functiosn private and member functions public
public class RetrofitService {
    //    private static final String BASE_URL = "http://34.131.78.211";
    private static final String BASE_URL = "https://festify-iiitl.herokuapp.com/";
    private static RetrofitService retrofitService = null; // object of our class
    private static RetrofitApi api; // object of our retrofit interface

    RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(RetrofitApi.class);
    }
//this function creates the retrofitService of the retrofit service so that it can be used in other classes if no isntance is created it automatically creates a new retrofitService

    //Why is synchronized used here?
    // So that if multipe threads are calling the api it not clahes the api only one thread can access the api at a time
    
    //why is here static function
    public static synchronized RetrofitService getInstance() {
        if (retrofitService == null)
            retrofitService = new RetrofitService();
        return retrofitService;
    }
    public RetrofitApi getMyApi() {
        return api;
    }
}
