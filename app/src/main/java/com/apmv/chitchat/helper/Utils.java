package com.apmv.chitchat.helper;

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

}
