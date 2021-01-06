package me.acablade.kelimeoyunu.Listeners;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.Game;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static me.acablade.kelimeoyunu.Utils.Colorizer.format;
import static me.acablade.kelimeoyunu.Utils.ConfigMessages.getFormattedString;

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
                if(words.length == 1){
                    Game g = KelimeOyunu.getGame();
                    String word = words[0];
                    //check if there is any number in the word
                    //if you have better regex send it to me via discord pls thxxx
                    Pattern pattern = Pattern.compile("([0-9])", Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(word);
                    boolean matchFound = matcher.find();
                    //this part is just a whole mess
                    if(!matchFound){
                        if(!KelimeOyunu.getGame().getWordChecker().isWord(word)) {
                            player.sendMessage(format(getFormattedString("wordgame.error.not_a_word")));
                            event.setCancelled(true);
                        }else{
                            String firstChar = String.valueOf(word.charAt(0));
                            String lastChar = String.valueOf(word.charAt(word.length()-1));
                            //if the last char isnt set
                            if(g.getLastChar().length()==0){
                                KelimeOyunu.getGame().setPlayer(player,KelimeOyunu.getGame().getWordCount(player.getDisplayName())+1);
                                g.setLastChar(lastChar);
                                g.getUsedWordList().add(word);
                            //if the last char is set
                            }else if(firstChar.equalsIgnoreCase(g.getLastChar())){
                                if(!g.getUsedWordList().contains(word)){
                                    KelimeOyunu.getGame().setPlayer(player,KelimeOyunu.getGame().getWordCount(player.getDisplayName())+1);
                                    g.setLastChar(lastChar);
                                    g.getUsedWordList().add(word);
                                }else{
                                    player.sendMessage(format(getFormattedString("wordgame.error.already_used_word")));
                                    event.setCancelled(true);
                                }

                            }else{
                                player.sendMessage(format(getFormattedString("wordgame.error.wrong_first_character")));
                                event.setCancelled(true);
                            }
                        }
                    }else{
                        player.sendMessage(format(getFormattedString("wordgame.error.numbers_in_word")));
                        event.setCancelled(true);
                    }
                }else{
                    if(!player.hasPermission("wordgame.bypass")){
                        player.sendMessage(format(getFormattedString("wordgame.error.multiple_words")));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
