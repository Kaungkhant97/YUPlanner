package com.kaungkhantthu.yuplanner.utils.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by HeinHtetOo on 06/12/2016.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // fetch the extra strings from intent
        // tells the app whether the user pressed Alarm 'On' or 'Off'
        String get_extra = intent.getExtras().getString("extra");

        // fetch the extra longs from the intent
        // tells the app which values the user picked from the drop-down list of the spinner
        Integer code = intent.getExtras().getInt("code");

        // create an intent to AlarmService
        Intent serviceIntent = new Intent(context, RingtonePlayingService.class);

        // pass the extra string from Main Activity to the Ringtone Playing Service
        serviceIntent.putExtra("extra", get_extra);

        // pass the extra int from Receiver to the Ringtone Playing Service
        serviceIntent.putExtra("code", code);

        // start the ringtone service
        context.startService(serviceIntent);
    }
}
