package com.kaungkhantthu.yuplanner.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class Utils {
    private static final String ISOFORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

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

}
