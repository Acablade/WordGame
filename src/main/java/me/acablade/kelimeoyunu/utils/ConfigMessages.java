package me.acablade.kelimeoyunu.utils;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.objects.MessageConfiguration;

public class ConfigMessages {

    /**
     * Returns the string formatted with placeholders
     * @param text Text keys in messages.yml
     * @return Formatted string with placeholders
     */
    public static String getFormattedString(String text){

        String config_text = MessageConfiguration.getCustomConfig().getString(text);

        if(KelimeOyunu.getGame() !=  null){
            config_text.
                    replaceAll("%winner%",KelimeOyunu.getGame().getWinner()).
                    replaceAll("%lastChar%", KelimeOyunu.getGame().getLastChar()).
                    replaceAll("%language%", KelimeOyunu.getGame().getWordChecker().getLanguage()[0]);
        }else{
            config_text.
                    replaceAll("%winner%",DefaultConfigMessages.DEFAULT_WINNER).
                    replaceAll("%lastChar%", DefaultConfigMessages.DEFAULT_LAST_CHAR).
                    replaceAll("%language%", DefaultConfigMessages.DEFAULT_LANGUAGE);
        }


        return config_text;
    }

}
