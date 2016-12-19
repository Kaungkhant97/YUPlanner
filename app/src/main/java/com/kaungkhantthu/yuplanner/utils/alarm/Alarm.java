package com.kaungkhantthu.yuplanner.utils.alarm;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import static android.R.attr.id;
import static android.R.attr.parentActivityName;
import static android.content.Context.ALARM_SERVICE;

/**
 * Created by HeinHtetOo on 06/12/2016.
 */

public class Alarm {



    private static Alarm alarm;



    private Alarm() {

    }

    public static Alarm getInstance() {
        if (alarm == null) {
            alarm = new Alarm();

        }
        return alarm;
    }


    public static void setAlarm(Calendar c, int code, Context context) {
        Calendar calendar = c;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);

        // put in extra String into alarm_intent
        // tells the clock that you pressed the "alarm on" click
        intent.putExtra("extra", "on");

        // put in an extra int into alarm_intent
        // tells the clock that you want a certain value from the drop-down list of the spinner
        intent.putExtra("code", code);

        // create a pending intent that delays the intent
        // until the specified calendar time
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, code, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        // set the alarm manager
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Log.e("Alarm ON : ", calendar.getTime().toString());

    }

    public void cancelAlarm(int code,Context context) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("extra", "off");
        intent.putExtra("code", code);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, code, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        context.sendBroadcast(intent);
    }
}
