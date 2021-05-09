package me.bryangaming.recoverhealth.utils;

import org.bukkit.ChatColor;

public class TextUtils {

    public static String colorize(String string){
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
