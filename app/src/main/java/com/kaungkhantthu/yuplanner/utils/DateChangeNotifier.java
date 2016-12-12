package com.kaungkhantthu.yuplanner.utils;

import com.kaungkhantthu.yuplanner.mvp.mvpView;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class DateChangeNotifier {
    private static DateChangeNotifier dateChangeNotifier;
    private static Set<mvpView> mvpHashSet;
    private static Calendar calendar;

    private DateChangeNotifier() {
    }

    public static DateChangeNotifier getInstance() {
        if (dateChangeNotifier == null) {
            dateChangeNotifier = new DateChangeNotifier();
            mvpHashSet = new HashSet<>();
            calendar = Calendar.getInstance();
        }
        return dateChangeNotifier;
    }

    public void addNotifyView(mvpView v) {
        mvpHashSet.add(v);
    }

    public void notifyAllView(Calendar c) {
        for (mvpView v : mvpHashSet) {
            v.onDateChange(c);
        }
        this.calendar = c;
    }

    public Calendar getcurrentSelectedDate() {
        return calendar;
    }

    public void setcurrentSelectedDate(Calendar c) {

        calendar = c;

    }


}
