package com.kaungkhantthu.yuplanner.utils.alarm;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.kaungkhantthu.yuplanner.MainActivity;
import com.kaungkhantthu.yuplanner.R;

/**
 * Created by HeinHtetOo on 06/12/2016.
 */

public class RingtonePlayingService extends Service {
    MediaPlayer mediaPlayer;
    int startID;
    boolean isRunning;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) throws NullPointerException {
        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        // fetch the extra string values
        String state = intent.getExtras().getString("extra");

        // fetch the sound integer values
        Integer code = intent.getExtras().getInt("code");

        // notification
        // set up the notification service
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // set up an intent that goes to the Main Activity
        Intent main_activity_intent = new Intent(this.getApplicationContext(), MainActivity.class);

        // set up a pending intent
        PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this, code, main_activity_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // make the notification parameters
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(Color.parseColor("#FF4081"))
                .setContentTitle("Alarm Off")
                .setContentText("Click me!")
                .setContentIntent(pendingIntentMainActivity)
                .setAutoCancel(true)
                .build();

        // set up the notification call command
        //notificationManager.notify(0, notification);

        // this converts the extra strings from the intent
        // to start IDs, value 0 or 1
        assert state != null;
        switch (state) {
            case "on":
                startId = 1;
                break;
            case "off":
                startId = 0;
                break;
            default:
                startId = 0;
                break;
        }

        // if else condition for alarm on and off cases
        if (!this.isRunning && startId == 1) {
            // create an intent of the media player
            mediaPlayer = MediaPlayer.create(this, R.raw.bad);
            mediaPlayer.start();

            this.isRunning = true;
            this.startID = 0;

            notificationManager.notify(code, notification);

        } else if (this.isRunning && startId == 0) {
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startID = 0;
        } else if (!this.isRunning && startId == 0) {
            this.isRunning = false;
            this.startID = 0;
        } else if (this.isRunning && startId == 1) {
            this.isRunning = true;
            this.startID = 1;
        } else {

        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT).show();

        super.onDestroy();
        this.isRunning = false;
    }
}
