package com.kaungkhantthu.yuplanner.recyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by kaungkhantthu on 11/30/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private ArrayList<Event> dataList;
    private Context context;

    public EventAdapter(ArrayList<Event> eventArrayList) {
        this.dataList = eventArrayList;
    }

    public void addallEvents(List<Event> eventArrayList) {
        dataList.addAll(eventArrayList);
        Collections.sort(dataList, new Comparator<Event>() {
            @Override
            public int compare(Event event, Event t1) {
                Date date1 = event.getFormattedDate();
                Date date2 = t1.getFormattedDate();
                return date1.after(date2) ? 1 : date1.equals(date2) ? 0 : -1;
            }
        });
        notifyDataSetChanged();
    }

    ;

    @Override
    public EventAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_event, parent, false);
        this.context = parent.getContext();
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {
        holder.bindData(dataList.get(position), context);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void clearEvents() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        private  TextView txt_eventSpeaker;
        private TextView txt_title, txt_eventTime, txtevent_date;
        private ImageView img_cover;


        public EventViewHolder(View itemView) {
            super(itemView);
            txt_title = (TextView) itemView.findViewById(R.id.txt_eventTitle);
            txt_eventTime = (TextView) itemView.findViewById(R.id.txt_eventTime);
            txt_eventSpeaker = (TextView) itemView.findViewById(R.id.txt_speaker);

            txtevent_date = (TextView) itemView.findViewById(R.id.txt_eventDate);
            img_cover = (ImageView) itemView.findViewById(R.id.img_cover);
        }

        public void bindData(Event e, Context c) {
            txt_title.setText(e.getTitle() + "");
            txt_eventTime.setText(e.getTime() + "");
            txtevent_date.setText(e.getDate() + "");
            txt_eventSpeaker.setText(e.getUsername()+"");

            Glide.with(c)
                    .load(e.getImgUrl())
                    .placeholder(R.drawable.dummy_photo)
                    .error(R.drawable.error)
                    .into(img_cover);
        }
    }
}
