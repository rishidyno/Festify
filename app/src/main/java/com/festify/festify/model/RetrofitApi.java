package com.festify.festify.model;

import com.festify.festify.ui.CreateEvent.CreateEventViewModel;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApi {
    @GET("/get_events")
    Call<JsonObject> getEvents();
    @POST("/post")
    Call<EventPostModel> addEvent(@Body EventPostModel eventModel);
}
