package com.kaungkhantthu.yuplanner.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaungkhantthu on 11/30/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<Event> dataList;

    public EventAdapter(ArrayList<Event> eventArrayList) {
        this.dataList = eventArrayList;
    }

    public void addallEvents(List<Event> eventArrayList) {
        dataList.addAll(eventArrayList);
        notifyDataSetChanged();
    }

    ;

    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_event,parent,false);

        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {
        holder.bindData(dataList.get(position));

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private final TextView txt_title;

        public EventViewHolder(View itemView) {
            super(itemView);
            txt_title =(TextView)itemView.findViewById(R.id.txt_eventTitle);
        }
        public void bindData(Event e){
            txt_title.setText(e.getTitle());
        }
    }
}
