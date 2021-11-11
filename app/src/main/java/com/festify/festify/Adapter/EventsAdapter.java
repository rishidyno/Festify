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
    OnItemClickListener listener;

    public EventsAdapter(List<EventModel> eventList, Context context, OnItemClickListener listener) {
        this.eventList = eventList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_layout, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.ViewHolder holder, int position) {
        holder.bind(eventList.get(position));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView eventName, startDate, endDate;
        ImageView eventImage;
        OnItemClickListener listener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            startDate = itemView.findViewById(R.id.event_start_date);
            endDate = itemView.findViewById(R.id.event_end_date);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        public void bind(EventModel eventModel) {
            eventName.setText(eventModel.getEventName());
            startDate.setText(eventModel.getEventData());
            endDate.setText(eventModel.getEventTime());
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
