package com.festify.festify.model;

import android.text.Editable;

import com.google.gson.annotations.SerializedName;

public class EventModel {
    private String _id;
    private String eventName;
    //what is serialized name?
    //because the json response uses   different name the model
    @SerializedName("eventDate")
    private String eventData;
    private String eventTime;
    private String eventLocation;
    private String eventDescription;
    private String eventImage;
    private String __v;
    public EventModel(String _id,String eventName, String eventData, String eventTime, String eventLocation, String eventDescription, String eventImage,String __v) {
        //what is this?
        this._id=_id;
        this.eventName = eventName;
        this.eventData = eventData;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.eventDescription = eventDescription;
        this.eventImage = eventImage;
        this.__v=__v;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventImage() {
        return eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }
}
