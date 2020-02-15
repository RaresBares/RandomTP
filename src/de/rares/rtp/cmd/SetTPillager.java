package de.rares.rtp.cmd;

import de.rares.rtp.TPillgerCompound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetTPillager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("tpillager.place")){
                if(!TPillgerCompound.exists(p)){
                  new TPillgerCompound(p).setTpiloc(p.getLocation());
                  p.sendMessage("bitte schreibe den Namen des Villagers");
                }else {

                }
            }
        }
        return true;
    }
}
