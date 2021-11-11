package com.festify.festify.ui.CreateEvent;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.festify.festify.model.EventModel;
import com.festify.festify.model.EventPostModel;
import com.festify.festify.model.RetrofitService;

import io.reactivex.disposables.Disposables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateEventViewModel extends ViewModel {


    public CreateEventViewModel() {
    }

    public synchronized void postEvent(String eventName,
                                       String startDate, String endDate,
                                       String eventVenue, String eventLocation, String eventDescription) {
        Call<EventPostModel> call = RetrofitService.getInstance().getMyApi().addEvent(new EventPostModel(eventName, startDate, endDate, eventVenue, eventLocation, eventDescription));
        call.enqueue(new Callback<EventPostModel>() {
            @Override
            public void onResponse(Call<EventPostModel> call, Response<EventPostModel> response) {
                Log.d("CreateEventViewModel", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<EventPostModel> call, Throwable t) {
                Log.d("CreateEventViewModel", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Disposables.empty();
    }
}
