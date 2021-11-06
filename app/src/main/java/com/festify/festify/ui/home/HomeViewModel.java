package com.festify.festify.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.festify.festify.model.EventModel;
import com.festify.festify.model.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<EventModel>> eventList;
    private MutableLiveData<Boolean> loading;
    private MutableLiveData<Boolean> error;

    public HomeViewModel() {
        eventList = new MutableLiveData<>();
        fetchEvents();
    }

    public void refreshEvents() {
        fetchEvents();
    }

    public void fetchEvents() {
        loading.setValue(true);
        error.setValue(false);
        Call<List<EventModel>> call = RetrofitService.getInstance().getMyapi().getEvents();
        call.enqueue(new Callback<List<EventModel>>() {
            @Override
            public void onResponse(Call<List<EventModel>> call, Response<List<EventModel>> response) {
                if (response.isSuccessful()) {
                    eventList.setValue(response.body());
                    Log.i("HomeViewModel", "onResponse: " + response.body());
                }
                Log.i("HomeViewModel", "onResponse: " + response.body());
            }
        
            @Override
            public void onFailure(Call<List<EventModel>> call, Throwable t) {
                t.printStackTrace();
                Log.d("HomeViewModel", "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<List<EventModel>> getEvents() {
        return eventList;
    }

    public LiveData<String> getText() {
    }
}