package com.kaungkhantthu.yuplanner.mvp.eventmvp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.recyclerView.EventAdapter;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.eventmvp.presenter.EventPresenterImpl;
import com.kaungkhantthu.yuplanner.utils.DateChangeNotifier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 11/30/16.
 */

public class EventFragment extends Fragment implements EventView {
    private RecyclerView recycler_event;
    private ArrayList<Event> eventArrayList;
    private EventAdapter adapter;
    private EventPresenterImpl eventPresenter;
    private FrameLayout errorlayout;
    private Button errorbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        recycler_event = (RecyclerView) v.findViewById(R.id.recycler_event);
        errorlayout = (FrameLayout) v.findViewById(R.id.errorLayout);
        errorbtn = (Button) v.findViewById(R.id.btn_error);
        initRecycler();
        init();
        return v;
    }

    private void init() {
        eventPresenter = EventPresenterImpl.getInstance(this);
        eventPresenter.init();
        DateChangeNotifier.getInstance().addNotifyView(this);
    }

    private void initRecycler() {
        eventArrayList = new ArrayList<>();
        recycler_event.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EventAdapter(eventArrayList);
        recycler_event.setAdapter(adapter);
    }


    @Override
    public void onDateChange(Calendar c) {

        eventPresenter.onDateChange(c);
    }

    @Override
    public void showEvent(List<Event> eventList) {
        errorlayout.setVisibility(View.GONE);
        recycler_event.setVisibility(View.VISIBLE);
        adapter.clearEvents();
        adapter.addallEvents(eventList);
    }

    @Override
    public void showErrorView() {
        Log.e( "showErrorView: ", "error in event");
        errorlayout.setVisibility(View.VISIBLE);
        recycler_event.setVisibility(View.GONE);
        errorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e( "onClick: ", "click"+"");
                eventPresenter.requestEvents();
            }
        });
    }
}
