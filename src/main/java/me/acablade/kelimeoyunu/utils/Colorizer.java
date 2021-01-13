package me.acablade.kelimeoyunu.utils;

import org.bukkit.ChatColor;

public class Colorizer {

    public static String format(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}
