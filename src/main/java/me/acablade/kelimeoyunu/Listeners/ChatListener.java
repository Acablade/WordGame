package me.acablade.kelimeoyunu.Listeners;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.acablade.kelimeoyunu.Utils.Colorizer.format;
import static me.acablade.kelimeoyunu.Utils.ConfigMessages.getFormattedString;

public class ChatListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) throws IOException {
        Game game = KelimeOyunu.getGame();
        if (game == null) return;
        if (!game.isStarted()) return;
        
        Player player = event.getPlayer();
        
        String message = event.getMessage();
        String[] words = message.split(" ");
        
        if (words.length != 1) {
            if(!player.hasPermission("wordgame.bypass")){
                player.sendMessage(format(getFormattedString("wordgame.error.multiple_words")));
                event.setCancelled(true);
            }
        } else {
            String word = words[0];
            
            Pattern pattern = Pattern.compile("([0-9])", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(word);
            
            boolean matchFound = matcher.find();
            if (!matchFound) {
                if (!game.getWordChecker().isWord(word)) {
                    player.sendMessage(format(getFormattedString("wordgame.error.not_a_word")));
                    event.setCancelled(true);
                } else {
                    String firstChar = String.valueOf(word.charAt(0));
                    String lastChar = String.valueOf(word.charAt(word.length()-1));
                    
                    //if the last char isnt set
                    if (game.getLastChar().length() == 0) {
                        game.setPlayer(player, game.getWordCount(player.getDisplayName())+1);
                        game.setLastChar(lastChar);
                        game.getUsedWordList().add(word);
                        //if the last char is set
                    } else if(firstChar.equalsIgnoreCase(game.getLastChar())) {
                        if (!game.getUsedWordList().contains(word)) {
                            game.setPlayer(player, game.getWordCount(player.getDisplayName())+1);
                            game.setLastChar(lastChar);
                            game.getUsedWordList().add(word);
                        } else {
                            player.sendMessage(format(getFormattedString("wordgame.error.already_used_word")));
                            event.setCancelled(true);
                        }
                    } else {
                        player.sendMessage(format(getFormattedString("wordgame.error.wrong_first_character")));
                        event.setCancelled(true);
                    }
                }
            } else {
                player.sendMessage(format(getFormattedString("wordgame.error.numbers_in_word")));
                event.setCancelled(true);
            }
        }
    }
}
