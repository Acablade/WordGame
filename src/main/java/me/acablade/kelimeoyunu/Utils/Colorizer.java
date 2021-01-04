package me.acablade.kelimeoyunu.Utils;

import org.bukkit.ChatColor;

public class Colorizer {

    public static String format(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

}
