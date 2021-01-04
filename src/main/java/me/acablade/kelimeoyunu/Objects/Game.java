package me.acablade.kelimeoyunu.Objects;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import me.acablade.kelimeoyunu.KelimeOyunu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Game {

    //Language of the game (not implemented yet)
    private final Language language;

    // Command that runs when the game finishes
    private final String gameFinishCommand;

    //Word counter hashmap to declare who is the winner
    private Map<Player, Integer> wordCounter;

    //How many second the game goes on for (integer might be a bad type for this lol) default: 30
    private final Integer lastingSeconds;

    //pretty self explanatory(wont go with enum because the game doesnt have many states)
    private boolean isStarted;

    //Task id to stop the bukkit task manually
    private int taskId;

    /**
     * Initialize game
     * @param language Language of the game
     * @param gameFinishCommand Command that runs when the game finishes
     * @param lastingSeconds How many seconds the game will last
     */
    public Game(@NotNull Language language,@NotNull String gameFinishCommand, @Nullable Integer lastingSeconds){
        //init variables
        this.language = language;
        this.gameFinishCommand = gameFinishCommand;
        //TreeMap to sort the map
        this.wordCounter = new TreeMap<>();
        this.isStarted = false;
        //might be a bad way to implement this, hit me up on discord if you have better way implementing this
        if(lastingSeconds == null){
            this.lastingSeconds = 30;
        }else{
            this.lastingSeconds = lastingSeconds;
        }
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
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), gameFinishCommand.replaceAll("%winner%", getWinner().getDisplayName()));
        }, this.lastingSeconds * 20).getTaskId();
        return true;
    }

    /**
     * Get winner of the game (player who has the most words written)
     * @return Player that wins the game
     */
    public Player getWinner(){
        //Return if the game hasnt started yet
        if(!isStarted) return null;
        //yoinked this from https://stackoverflow.com/a/64648401
        List list = new ArrayList(wordCounter.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Player, Integer>>() {
            @Override
            public int compare(Map.Entry<Player, Integer> e1, Map.Entry<Player, Integer> e2) {
                return e1.getValue().compareTo(e2.getValue());
            }
        });
        return (Player) list.get(0);
    }



}
