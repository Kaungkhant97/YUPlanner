package com.kaungkhantthu.yuplanner.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kaungkhantthu on 12/4/16.
 */

public class Utils {
    private static final String SIMPLE_DATE_FORMATE = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static String formatDate(Date d) {

        SimpleDateFormat format = new SimpleDateFormat(SIMPLE_DATE_FORMATE);

        String publishedDate = format.format(d);
        return publishedDate;
    }

    public static Date formatDate(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(SIMPLE_DATE_FORMATE);

        Date d=format.parse(s);
        return d;
    }


}
