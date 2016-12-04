package com.kaungkhantthu.yuplanner.mvp.views;

import com.kaungkhantthu.yuplanner.data.entity.Event;

import java.text.ParseException;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public interface MainView {
    void BindToCalendar(List<Event> events) throws ParseException;

    void showSnackBar(String s);
}
