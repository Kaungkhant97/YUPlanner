package com.kaungkhantthu.yuplanner.mvp.views;

import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.util.Calendar;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public interface EventView extends mvpView {
    @Override
    void onDateChange(Calendar c);

    void showEvent(List<Event> eventList);

    void showError();
}
