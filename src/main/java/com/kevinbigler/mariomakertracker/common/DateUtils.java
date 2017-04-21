package com.kevinbigler.mariomakertracker.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by Kevin on 3/3/2017.
 */
public class DateUtils {
    public static Date createFromString(String format, String date) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date daysAgo(int numberDaysAgo) {
        LocalDate daysAgo = LocalDate.now().minusDays(numberDaysAgo);
        return DateUtils.createFromString("yyyy-MM-dd", daysAgo.toString());
    }
}
