package com.apmv.chitchat.helper;

import android.content.ContentResolver;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

import com.apmv.chitchat.App;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by an.pham1611 on 3/23/16.
 */
public class Utils {

    public static String showTime(String time) {
        SimpleDateFormat fmt = new SimpleDateFormat("h:mm a");
        String dateString = fmt.format(new Date(Long.valueOf(time)));
        return dateString;
    }

    // Playing notification sound
    public static void playNotificationSound(Context context) {

        try {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + App.getInstance().getApplicationContext().getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(App.getInstance().getApplicationContext(), alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
