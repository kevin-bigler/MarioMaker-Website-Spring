package com.kevinbigler.mariomakertracker.common;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by Kevin on 3/12/2017.
 */
public class MMUtils {
    public static boolean isCourseNintendoIdValid(String nintendoId) {
        if (nintendoId == null)
            return false;

        String regex = "^[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}$";

        return nintendoId.matches(regex);
    }

    public static boolean isPlayerNintendoIdValid(String nintendoId) {
        // TODO not sure what characters are allowed, and also what the max character limit is
        return StringUtils.isNotBlank(nintendoId);
    }

    /**
     * Used on date strings from Nintendo's mario maker website (e.g. course upload date)
     * Note: We only care about date, not time.
     * @param dateString
     * @return
     */
    public static Date parseDateString(String dateString) {
        if (dateString == null)
            return null;

        Date date = null;

        if (dateString.contains("days")) {
            // example: 3 days ago
            String numberDaysAgoString = StringUtils.substringBefore(dateString,"days").trim();
            int numberDaysAgo = Integer.parseInt(numberDaysAgoString);
            date = DateUtils.daysAgo(numberDaysAgo);
        } else if (StringUtils.countMatches(dateString, "/") == 2) {
            // example: 10/17/2016
            date = DateUtils.createFromString("m/d/Y", dateString);
        } else {
            // x seconds ago
            // x minutes ago
            // x hours ago
            date = new Date();  // today
        }

        return date;
    }
}
