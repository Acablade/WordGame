package me.acablade.kelimeoyunu.Commands;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.Game;
import me.acablade.kelimeoyunu.Objects.WordCheckers.WordChecker;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static me.acablade.kelimeoyunu.Utils.Colorizer.format;
import static me.acablade.kelimeoyunu.Utils.ConfigMessages.getFormattedString;

public class WordGameCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("wordgame.game")){
                handleGameCommand(sender,args);
            }else{
                player.sendMessage(format(getFormattedString("wordgame.error.permission")));
            }
        }else{
            handleGameCommand(sender,args);
        }


        return false;
    }

    //Do the method here so there is no duplicates and it looks nice
    public void handleGameCommand(CommandSender sender,String[] args){
        if(args.length == 0){
            sender.sendMessage(format(getFormattedString("wordgame.error.syntax")));
        }else{
            if(args[0].equalsIgnoreCase("start")){
                if(args.length == 2){
                    if(KelimeOyunu.getGame()==null){
                        //again feel free to hit me up on discord to optimize this code
                        //it shouldnt do much problem since its only used in command
                        for(WordChecker wc: KelimeOyunu.wordCheckerManager.getWordCheckerList()){
                            for(String alias: wc.getLanguage()){
                                if(args[1].equalsIgnoreCase(alias)){
                                    //Will implement the config later
                                    Game g = new Game(wc, getFormattedString("wordgame.command_on_finish"),null);
                                    KelimeOyunu.setGame(g);
                                    g.start();
                                    sender.sendMessage(format(getFormattedString("gameword.messages.game_started")));
                                }
                            }
                        }
                    }else{
                        sender.sendMessage(format(getFormattedString("gameword.error.game_already_started")));
                    }
                }else{
                    sender.sendMessage(format(getFormattedString("gameword.error.syntax")));
                }
            }else if(args[0].equalsIgnoreCase("help")){
                sender.sendMessage(format(getFormattedString("gameword.error.syntax")));
            }
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        if(args.length == 2){
            for(WordChecker wordChecker: KelimeOyunu.wordCheckerManager.getWordCheckerList()){
                for(String aliases: wordChecker.getLanguage()){
                    if(aliases.startsWith(args[1])){
                        tabComplete.add(aliases);
                    }
                }
            }
        }

        return tabComplete;
    }
}
