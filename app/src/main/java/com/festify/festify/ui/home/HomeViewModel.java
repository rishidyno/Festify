package com.festify.festify.ui.home;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.festify.festify.model.EventList;
import com.festify.festify.model.EventModel;
import com.festify.festify.model.RetrofitService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.disposables.Disposables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    MutableLiveData<List<EventModel>> eventList;
    MutableLiveData<Boolean> loading;
    MutableLiveData<Boolean> error;

    public HomeViewModel() {
        loading = new MutableLiveData<>(false);
        error = new MutableLiveData<>(false);
        eventList = new MutableLiveData<>(null);
        fetchEvents();
    }

    public void refreshEvents() {
        fetchEvents();
    }

    public synchronized void fetchEvents() {
        loading.setValue(true);
        error.setValue(false);
        Call<JsonObject> call = RetrofitService.getInstance().getMyApi().getEvents();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                loading.setValue(false);
                error.setValue(false);
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    JsonArray array = response.body().getAsJsonArray("data");
                    Log.i("HomeViewModel", "onResponse: " + gson.fieldNamingStrategy());
                    EventList list_event = new EventList();
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject object = array.get(i).getAsJsonObject();
                        EventModel model = gson.fromJson(object.toString(), EventModel.class);
                        list_event.addEvent(model);
                    }
                    eventList.setValue(list_event.getEventList());
                    Log.i("HomeViewModel", "onResponse: " + response.body());
                }
                Log.i("HomeViewModel", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                loading.setValue(false);
                error.setValue(true);
                t.printStackTrace();
                Log.d("HomeViewModel", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Disposables.empty();
    }


}