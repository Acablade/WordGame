package me.acablade.kelimeoyunu.Listeners;

import me.acablade.kelimeoyunu.KelimeOyunu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) throws IOException {

        Player player = event.getPlayer();

        String message = event.getMessage();
        String[] words = message.split(" ");
        //its a lot of if statements and i hate it but i have to do it
        //if you have a better way to implement this add me on discord pls thx :*
        if(KelimeOyunu.getGame()!=null){
            if(KelimeOyunu.getGame().isStarted()){
                if(!player.hasPermission("wordgame.bypass")){
                    if(words.length == 1){
                        String word = words[0];
                        //check if there is any number in the word
                        //if you have better regex send it to me via discord pls thxxx
                        Pattern pattern = Pattern.compile("([0-9])", Pattern.CASE_INSENSITIVE);
                        Matcher matcher = pattern.matcher(word);
                        boolean matchFound = matcher.find();
                        if(!matchFound){
                            if(KelimeOyunu.getGame().getWordChecker().isWord(word)){

                            }else{
                                event.setCancelled(true);
                            }
                        }else{
                            event.setCancelled(true);
                        }
                    }else{
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
