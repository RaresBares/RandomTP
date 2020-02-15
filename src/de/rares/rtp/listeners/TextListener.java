package de.rares.rtp.listeners;

import de.rares.rtp.TPillager_Set;
import de.rares.rtp.TPillgerCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TextListener implements Listener {

    @EventHandler
    public void onText(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (TPillgerCompound.exists(p)) {

            TPillgerCompound tp = TPillgerCompound.getByPlayer(p);

            if (tp.getState() == TPillager_Set.SAYNAME) {
                tp.setName(e.getMessage());
                tp.getByPlayer(p).setState(TPillager_Set.SET_LOCATION);
                p.sendMessage("Bitte schreibe !location wenn du deinen SPawn hast");
            }else{
                if(e.getMessage().equalsIgnoreCase("!location")){
                    tp.setSpawnloc(e.getPlayer().getLocation());
                    tp.end();
                    System.out.println("Villager wird erstellt");
                }
            }

        }
    }

}
