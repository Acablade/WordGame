package me.acablade.kelimeoyunu.Objects;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.WordCheckers.WordChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    //Word checking and i made that with abstract class to make it look cool B)
    private WordChecker wordChecker;

    // Command that runs when the game finishes
    private final String gameFinishCommand;

    //Latest char of the word
    String latestChar = "";

    //Word counter hashmap to declare who is the winner
    //Used string instead of player or uuid because of cracked servers
    //its better to use uuid tho
    private Map<String, Integer> wordCounter;

    //How many second the game goes on for (integer might be a bad type for this lol) default: 30
    private final Integer lastingSeconds;

    //Used word list
    private final List<String> usedWordList;


    //pretty self explanatory(wont go with enum because the game doesnt have many states)
    private boolean isStarted;

    //Task id to stop the bukkit task manually
    private int taskId;

    /**
     * Initialize game
     * @param wordChecker Word checker
     * @param gameFinishCommand Command that runs when the game finishes
     * @param lastingSeconds How many seconds the game will last
     */
    public Game(WordChecker wordChecker,String gameFinishCommand,Integer lastingSeconds){
        //init variables
        this.gameFinishCommand = gameFinishCommand;
        this.wordCounter = new HashMap<>();
        this.usedWordList = new ArrayList<>();
        this.isStarted = false;
        //might be a bad way to implement this, hit me up on discord if you have better way implementing this
        if(lastingSeconds == null){
            this.lastingSeconds = 30;
        }else{
            this.lastingSeconds = lastingSeconds;
        }

        //do the word checker bcs its cool
        this.wordChecker = wordChecker;
    }

    /**
     * Game starting method
     * @return if the game can start/started
     */
    public boolean start(){
        //Return if the game has already started
        if(this.isStarted) return false;
        this.isStarted = true;
        //Handle the finishing automatically here
        this.taskId = Bukkit.getScheduler().runTaskLaterAsynchronously(KelimeOyunu.getInstance(),() ->
        {
            //command thing
            Bukkit.getScheduler().runTask(KelimeOyunu.getInstance(), () -> Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), gameFinishCommand.replaceAll("%winner%", getWinner())));
            //self destruct lol
            KelimeOyunu.setGame(null);

        }, this.lastingSeconds * 20).getTaskId();
        return true;
    }

    /**
     * Get winner of the game (player who has the most words written)
     * @return Player that wins the game
     */
    public String getWinner(){
        //Return if the game hasnt started yet
        if(!isStarted) return null;
        int biggestNumber = 0;
        String playerName = "no one";
        for(Map.Entry<String, Integer> entry: wordCounter.entrySet()){
            if(entry.getValue() > biggestNumber){
                biggestNumber = entry.getValue();
                playerName = entry.getKey();
            }
        }

        return playerName;
    }

    /**
     * Get word count of a player
     * @param p Player that you want to get count of
     * @return Player's word count
     */
    public Integer getWordCount(String p){
        //if the player hasnt written anything (might not need this not quite sure)
        if(!this.wordCounter.containsKey(p)) return 0;
        return this.wordCounter.get(p);
    }

    //implement this
    public boolean finish(){
        if(!isStarted) return false;
        KelimeOyunu.setGame(null);
        Bukkit.getScheduler().cancelTask(this.taskId);
        isStarted = false;
        return true;
    }

    /**
     * Is the game started
     * @return is game started
     */
    public boolean isStarted(){
        return isStarted;
    }
    //Wont do set method because i handle that in the start method and the task that finishes the game
    //Might add that later

    /**
     * Get word checker
     * @return word checker of the specified language
     */
    public WordChecker getWordChecker(){
        return this.wordChecker;
    }

    /**
     * Set last char
     * @param lastChar last char of the word of the last message
     */
    public void setLastChar(String lastChar) {
        this.latestChar = lastChar;
    }

    /**
     * Get last char of the word of the last message
     * @return last char of the word of the last message
     */
    public String getLastChar() {
        return latestChar;
    }

    /**
     * Set player's word count
     * @param p player you want to add to list
     * @param count the amount of words you want to add
     */
    public void setPlayer(Player p, int count){
        wordCounter.put(p.getDisplayName(), count);
    }

    /**
     * Returns the list of used words in the game
     * @return list of used words
     */
    public List<String> getUsedWordList(){
        return usedWordList;
    }
}
