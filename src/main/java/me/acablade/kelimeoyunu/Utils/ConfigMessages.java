package me.acablade.kelimeoyunu.Utils;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.MessageConfiguration;
import org.bukkit.ChatColor;

public class ConfigMessages {

    /**
     * Returns the string formatted with placeholders
     * @param text Text keys in messages.yml
     * @return Formatted string with placeholders
     */
    public static String getFormattedString(String text){

        String config_text = MessageConfiguration.getCustomConfig().getString(text);

        return config_text.replaceAll("%winner%", KelimeOyunu.getGame().getWinner())
                .replaceAll("%lastChar%", KelimeOyunu.getGame().getLastChar());
    }

}
