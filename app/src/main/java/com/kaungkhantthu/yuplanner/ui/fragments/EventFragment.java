package com.kaungkhantthu.yuplanner.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.adapter.EventAdapter;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.presenter.EventPresenterImpl;
import com.kaungkhantthu.yuplanner.mvp.views.EventView;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        recycler_event = (RecyclerView) v.findViewById(R.id.recycler_event);
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
        Log.e("onDateChange: ", c + "");
        eventPresenter.onDateChange(c);
    }

    @Override
    public void showEvent(List<Event> eventList) {
        adapter.addallEvents(eventList);
    }

    @Override
    public void showError() {

    }
}
