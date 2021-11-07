package com.festify.festify.ui.CreateEvent;

import android.text.Editable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.festify.festify.model.EventModel;
import com.festify.festify.model.RetrofitService;

public class CreateEventViewModel extends ViewModel {

    int count = 0;
    MutableLiveData<EventModel> newEvent;
    MutableLiveData<String> eventName;
    MutableLiveData<String> startDate;
    MutableLiveData<String> endDate;
    MutableLiveData<String> eventVenue;
    MutableLiveData<String> eventDescription;

    CreateEventViewModel() {
        newEvent = new MutableLiveData<>(null);
        eventName = new MutableLiveData<>(null);
        startDate = new MutableLiveData<>(null);
        endDate = new MutableLiveData<>(null);
        eventVenue = new MutableLiveData<>(null);
        eventDescription = new MutableLiveData<>(null);
    }

    public LiveData<EventModel> postEvent(String eventName,
                                          String startDate, String endDate,
                                          String eventVenue, String eventDescription,String eventLocation) {
        String _id = Integer.toString(count++), __v = null;
        EventModel eventModel = new EventModel(_id, eventName, startDate
                , endDate, eventVenue, eventDescription, null,eventLocation);
        RetrofitService.getInstance().getMyApi().addEvent(eventModel);
        return newEvent;
    }

//    public LiveData<EventModel> postEvent(){
//
//    }
}
