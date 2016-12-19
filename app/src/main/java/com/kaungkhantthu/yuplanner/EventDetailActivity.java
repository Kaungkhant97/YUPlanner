package com.kaungkhantthu.yuplanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModel;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.Model.EventModelImpl;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import io.realm.Realm;

public class EventDetailActivity extends AppCompatActivity {

    private ImageView ivEventDetail;
    private TextView tvEventTitle;
    private TextView tvDay;
    private TextView tvMonth;
    private TextView tvYear;
    private TextView tvPeriod;
    private TextView tvLocation;
    private TextView tvDescription;

    private Intent intentFromEventFragment;
    private String id;
    private EventModel eventModel;
    private List<Event> e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Event");

        init();
        setData(e);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void setData(List<Event> events) {
        String[] sMonths = {"JAN","FEB","MAR","APR","MAY","JUNE","JULY","AUGUST","SEP","OCT","NOV","DEC"};
        int month;
           if(events != null){
               for(Event event:events){
                   month = Integer.parseInt(event.getDate().substring(3,5));
                   tvEventTitle.setText(event.getTitle());
                   tvDay.setText(event.getDate().substring(0,2));
                   tvMonth.setText(sMonths[month-1]);
                   tvYear.setText(event.getDate().substring(6,10));
                   tvPeriod.setText(event.getTime());
                   tvLocation.setText(event.getPlace());
                   tvDescription.setText(event.getDescription());

                   Glide.with(getApplicationContext())
                           .load(event.getImgUrl())
                           .placeholder(R.drawable.dummy_photo)
                           .error(R.drawable.error)
                           .into(ivEventDetail);
               }
           }
    }

    private void init() {
        ivEventDetail = (ImageView) findViewById(R.id.iv_event_detail);
        tvEventTitle = (TextView) findViewById(R.id.tv_event_detail_title);
        tvDay = (TextView) findViewById(R.id.tv_day);
        tvMonth = (TextView) findViewById(R.id.tv_month);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvPeriod = (TextView) findViewById(R.id.tv_period);
        tvLocation = (TextView) findViewById(R.id.tv_location);
        tvDescription = (TextView) findViewById(R.id.tv_description);

        intentFromEventFragment = getIntent();
        id = intentFromEventFragment.getStringExtra("id");

        eventModel = EventModelImpl.getInstance();
        e = Realm.getDefaultInstance().copyFromRealm(eventModel.getEvent(id));
    }

}
