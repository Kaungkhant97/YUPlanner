package com.kaungkhantthu.yuplanner.utils;

import com.kaungkhantthu.yuplanner.mvp.views.mvpView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class DateChangeNotifier {
    private static DateChangeNotifier dateChangeNotifier;
    private static ArrayList<mvpView> mvpViewArrayList;
    private static Calendar calendar ;

    private DateChangeNotifier() {
    }
    public static DateChangeNotifier getInstance(){
        if(dateChangeNotifier == null){
            dateChangeNotifier = new DateChangeNotifier();
            mvpViewArrayList = new ArrayList<>();
            calendar = Calendar.getInstance();
        }
        return dateChangeNotifier;
    }
    public void addNotifyView(mvpView  v){
        mvpViewArrayList.add(v);
    }

    public void notifyAllView(Calendar c){
        for (mvpView v : mvpViewArrayList ){
            v.onDateChange(c);
        }
        this.calendar = c;
    }

    public Calendar getcurrentSelectedDate(){
        return calendar;
    }
}
