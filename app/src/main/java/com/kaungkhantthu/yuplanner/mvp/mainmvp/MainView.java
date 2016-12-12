package com.kaungkhantthu.yuplanner.mvp.mainmvp;

import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.text.ParseException;
import java.util.List;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public interface MainView  {
    void BindToCalendar(List<Event> events) throws ParseException;

    void showSnackBar(String s);

    void showeventtab();
    void hideventtab();
}
