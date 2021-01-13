package me.acablade.kelimeoyunu.utils;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.objects.MessageConfiguration;

import static me.acablade.kelimeoyunu.utils.Colorizer.format;

public class ConfigMessages {

    /**
     * Returns the string formatted with placeholders
     * @param text Text keys in messages.yml
     * @return Formatted string with placeholders
     */
    public static String getFormattedString(String text){

        String configText = format(MessageConfiguration.getCustomConfig().getString(text));


        if(KelimeOyunu.getGame() !=  null){
            configText = configText.
                    replaceAll("%winner%",KelimeOyunu.getGame().getWinner()).
                    replaceAll("%lastChar%", KelimeOyunu.getGame().getLastChar()).
                    replaceAll("%language%", KelimeOyunu.getGame().getWordChecker().getLanguage()[0]);
        }else{
            configText = configText.
                    replaceAll("%lastChar%", DefaultConfigMessages.DEFAULT_LAST_CHAR).
                    replaceAll("%language%", DefaultConfigMessages.DEFAULT_LANGUAGE);
        }


        return configText;
    }

}
