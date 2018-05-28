package com.nerdsoft.coder.notty.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String desiredPattern = "YYYY-MM-d   h:mm:ss a";
    public static String formatDateOnDisplay(){
        SimpleDateFormat format = new SimpleDateFormat(desiredPattern);
        return format.format(new Date());
    }
    public static String formatDateOnDisplay(Date date){
        SimpleDateFormat format = new SimpleDateFormat(desiredPattern);
        return format.format(date);
    }
}
