package com.kevinbigler.mariomakertracker.common;

/**
 * Created by Kevin on 3/12/2017.
 */
public class MMUtils {
    public static boolean isCourseNintendoIdValid(String nintendoId) {
        if (nintendoId == null)
            return false;

        String regex = "/^[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}$/";

        return nintendoId.matches(regex);
    }
}
