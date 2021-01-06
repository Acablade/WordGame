package me.acablade.kelimeoyunu.Commands;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.Game;
import me.acablade.kelimeoyunu.Objects.MessageConfiguration;
import me.acablade.kelimeoyunu.Objects.WordCheckers.IWordChecker;
import org.bukkit.Bukkit;
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
            sendHelp(sender);
        }else{
            if(args.length == 2){
                if(args[0].equalsIgnoreCase("start")){
                    if(KelimeOyunu.getGame()==null){
                        //again feel free to hit me up on discord to optimize this code
                        //it shouldnt do much problem since its only used in command
                        for(IWordChecker wc: KelimeOyunu.wordCheckerManager.getWordCheckerList()){
                            for(String alias: wc.getLanguage()){
                                if(args[1].equalsIgnoreCase(alias)){
                                    Game g = new Game(wc, getFormattedString("wordgame.command_on_finish"),null);
                                    KelimeOyunu.setGame(g);
                                    g.start();
                                    Bukkit.getOnlinePlayers().forEach((p) -> p.sendMessage(format(getFormattedString("wordgame.messages.game_started"))));
                                    sender.sendMessage(format(getFormattedString("wordgame.messages.game_started")));
                                }
                            }
                        }
                    }else{
                        sender.sendMessage(format(getFormattedString("wordgame.error.game_already_started")));
                    }
                }
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("reload")){
                    MessageConfiguration.createCustomConfig();
                    sender.sendMessage(format(getFormattedString("wordgame.messages.reload")));
                } else if(args[0].equalsIgnoreCase("help")){
                    sendHelp(sender);
                }
            }else{
                sendHelp(sender);
            }
        }

    }

    private void sendHelp(CommandSender sender){
        //this is so bad
        sender.sendMessage(format("&aWordGame Plugin:\n" +
                "&e- &7/wordgame start &8<lang> &e: Starts the game\n"+
                "&e- &7/wordgame reload &e: Reloads the config\n"+
                "&e- &7/wordgame help &e: Shows this menu"));
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        if(args.length == 2){
            for(IWordChecker wordChecker : KelimeOyunu.wordCheckerManager.getWordCheckerList()){
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
