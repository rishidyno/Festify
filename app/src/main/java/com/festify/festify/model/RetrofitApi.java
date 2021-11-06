package com.festify.festify.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApi {
    @GET("/get_events")
    Call<List<EventModel>> getEvents();
    @POST("/add_event")
    Call<EventModel> addEvent(EventModel event);
}
