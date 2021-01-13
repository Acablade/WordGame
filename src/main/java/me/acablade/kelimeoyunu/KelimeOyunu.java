package me.acablade.kelimeoyunu;

import me.acablade.kelimeoyunu.commands.WordGameCommand;
import me.acablade.kelimeoyunu.listeners.ChatListener;
import me.acablade.kelimeoyunu.objects.Game;
import me.acablade.kelimeoyunu.objects.MessageConfiguration;
import me.acablade.kelimeoyunu.objects.wordcheckers.EnglishWordChecker;
import me.acablade.kelimeoyunu.objects.wordcheckers.TurkishWordChecker;
import me.acablade.kelimeoyunu.objects.wordcheckers.IWordChecker;
import me.acablade.kelimeoyunu.objects.wordcheckers.WordCheckerManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public final class KelimeOyunu extends JavaPlugin {

    private static KelimeOyunu instance;

    //Most likely not the way you want to handle it
    //But the server will only have one game going only so i dont care lol
    private static Game game;

    public static IWordChecker englishWordChecker;
    public static IWordChecker turkishWordChecker;

    public static WordCheckerManager wordCheckerManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        wordCheckerManager = new WordCheckerManager();

        //Register word checkers
        turkishWordChecker = wordCheckerManager.register(new TurkishWordChecker());
        englishWordChecker = wordCheckerManager.register(new EnglishWordChecker());

        //Register listeners
        getServer().getPluginManager().registerEvents(new ChatListener(), this);

        //Register commands
        WordGameCommand wgc = new WordGameCommand();
        PluginCommand cmd = getCommand("wordgame");
        cmd.setExecutor(wgc);
        cmd.setTabCompleter(wgc);

        //Create messages.yml
        MessageConfiguration.createCustomConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //I like to reload a lot thats why i used this
        HandlerList.unregisterAll(this);


    }
    /**
     * Pretty self explanatory
     * @return instance of this class
     */
    public static KelimeOyunu getInstance(){
        return instance;
    }

    /**
     * Self explanatory
     * @return The game thats going on right now
     */
    public static Game getGame(){
        return game;
    }

    /**
     * Set the game
     * @param gameSet Game that you want to create
     */
    public static void setGame(Game gameSet){
        game = gameSet;
    }

    //FOR FUTURE SELF: YOU WANT TO ADD A WAY OF WORD CHECKING AND ADD COMMANDS TO SETUP THE GAME ASWELL DO IT WITH JSON BECAUSE ITS FANCY

}
