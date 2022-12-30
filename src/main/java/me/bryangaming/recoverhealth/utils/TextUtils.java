package me.bryangaming.recoverhealth.utils;

public class TextUtils {

    public static boolean isNumeric(String string) {

        try {
            Integer.parseInt(string);

        } catch (NumberFormatException numberFormatException) {
            return false;
        }

        return true;
    }
}
