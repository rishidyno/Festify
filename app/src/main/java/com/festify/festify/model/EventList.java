package com.festify.festify.model;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private List<EventModel> eventList;

    public EventList(List<EventModel> eventList) {
        this.eventList = eventList;
    }

    public EventList() {
        eventList = new ArrayList<>();
    }

    public List<EventModel> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventModel> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(EventModel event) {
        eventList.add(event);
    }
}
