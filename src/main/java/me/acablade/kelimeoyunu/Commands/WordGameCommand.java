package me.acablade.kelimeoyunu.Commands;

import me.acablade.kelimeoyunu.KelimeOyunu;
import me.acablade.kelimeoyunu.Objects.Game;
import me.acablade.kelimeoyunu.Objects.WordCheckers.WordChecker;
import me.acablade.kelimeoyunu.Objects.WordCheckers.WordCheckerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class WordGameCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("wordgame.game")){
                handleGameCommand(sender,args);
            }else{
                player.sendMessage("§cInsufficient permissions");
            }
        }else{
            handleGameCommand(sender,args);
        }


        return false;
    }

    //Do the method here so there is no duplicates and it looks nice
    public void handleGameCommand(CommandSender sender,String[] args){
        if(args[0].equalsIgnoreCase("start")){
            if(KelimeOyunu.getGame()==null){
                //again feel free to hit me up on discord to optimize this code
                //it shouldnt do much problem since its only used in command
                for(WordChecker wc: KelimeOyunu.wordCheckerManager.getWordCheckerList()){
                    for(String alias: wc.getLanguage()){
                        if(args[1].equalsIgnoreCase(alias)){
                            //Will implement the config later
                            KelimeOyunu.setGame(new Game(wc, "/say Game finished the winner is %winner%",null));
                            sender.sendMessage("§aThe game has started");
                        }
                    }
                }
            }else{
                sender.sendMessage("§cThere is already a game going on!");
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> tabComplete = new ArrayList<>();


        return null;
    }
}