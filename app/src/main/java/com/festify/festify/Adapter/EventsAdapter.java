package com.festify.festify.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.festify.festify.R;
import com.festify.festify.model.EventModel;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {
    List<EventModel> eventList;
    Context context;

    public EventsAdapter(List<EventModel> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }
//    public void updateEvents(List<EventModel> eventList) {
//        eventList.clear();
//        this.eventList = eventList;
//        notifyDataSetChanged();
//    }
    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        holder.bind(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView eventName,startDate,endDate;
        ImageView eventImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            startDate = itemView.findViewById(R.id.event_start_date);
            endDate = itemView.findViewById(R.id.event_end_date);
        }

        public void bind(EventModel eventModel) {
            eventName.setText(eventModel.getEventName());
            startDate.setText(eventModel.getEventData());
            endDate.setText(eventModel.getEventData());
        }
    }
}
