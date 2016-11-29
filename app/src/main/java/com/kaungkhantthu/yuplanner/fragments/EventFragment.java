package com.kaungkhantthu.yuplanner.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Event;
import com.kaungkhantthu.yuplanner.mvp.MainContract;

import java.util.ArrayList;

/**
 * Created by kaungkhantthu on 11/30/16.
 */

public class EventFragment extends Fragment implements MainContract.Eventview{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events,container,false);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void showEvent(ArrayList<Event> eventArrayList) {

    }
}
