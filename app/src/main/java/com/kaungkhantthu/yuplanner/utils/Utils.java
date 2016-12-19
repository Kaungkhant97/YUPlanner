package com.kaungkhantthu.yuplanner.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.kaungkhantthu.yuplanner.R;
import com.kaungkhantthu.yuplanner.data.entity.Subject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class Utils {
    private static final String ISOFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static HashMap<Object, Object> periodTimeMap;
    private static HashMap<String, String> majorMap;
    private static HashMap<String, String> classMap;

    public static String formatDate(Date d) {

        SimpleDateFormat format = new SimpleDateFormat(ISOFORMAT);

        String publishedDate = format.format(d);
        return publishedDate;
    }

    public static Date formatDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(ISOFORMAT);

        Date d = format.parse(s);
        return d;
    }

    public static String formatDATEIntoYearMonthDay(Date d) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(d);
        return date;
    }

    public static String periodToTimeCoverter(Subject prevsubject, Subject subject) {
        initPeriodTimeMap();
        int mperiod;
        if (prevsubject == null) {
            mperiod = subject.getTimetable().get(0).getPeriod().get(0).getP();
        } else {
            if (subject.equals(prevsubject)) {
                mperiod = subject.getTimetable().get(0).getPeriod().get(1).getP();
            } else {
                mperiod = subject.getTimetable().get(0).getPeriod().get(0).getP();
            }
        }
        String time = (String) periodTimeMap.get(mperiod);
        return time;

    }

    public static boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    private static void initPeriodTimeMap() {
        periodTimeMap = new HashMap<>();
        periodTimeMap.put(0, "9:00-10:00");
        periodTimeMap.put(1, "10:00-11:00");
        periodTimeMap.put(2, "11:00-12:00");
        periodTimeMap.put(3, "12:30-1:30");
        periodTimeMap.put(4, "1:30-2:30");
        periodTimeMap.put(5, "2:30-3:30");
        periodTimeMap.put(6, "3:30-4:30");
    }

    public static String yearConverter(String year, Context c) {
        initYearMap(c);
        return majorMap.get(year);
    }

    private static void initYearMap(Context c) {
        majorMap = new HashMap<>();

        String[] valueYear = c.getResources().getStringArray(R.array.year_list);
        String[] keyYear = c.getResources().getStringArray(R.array.myear_list);
        for (int i = 0; i < keyYear.length; i++) {
            majorMap.put(keyYear[i], valueYear[i]);
        }


    }

    public static String classConverter(String mClass, Context c) {
        initclassMap(c);
        return classMap.get(mClass);
    }

    private static void initclassMap(Context c) {
        classMap = new HashMap<>();

        String[] valueClass = c.getResources().getStringArray(R.array.class_list);
        String[] keyClass = c.getResources().getStringArray(R.array.mclass_list);
        for (int i = 0; i < keyClass.length; i++) {
            classMap.put(keyClass[i], valueClass[i]);
        }


    }



}
