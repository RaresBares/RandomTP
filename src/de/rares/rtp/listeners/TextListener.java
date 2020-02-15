package de.rares.rtp.listeners;

import de.rares.rtp.TPillager_Set;
import de.rares.rtp.TPillgerCompound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class TextListener implements Listener {

    @EventHandler
    public void onText(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if (TPillgerCompound.exists(p)) {

            TPillgerCompound tp = TPillgerCompound.getByPlayer(p);

            if (tp.getState() == TPillager_Set.SAYNAME) {
                e.setCancelled(true);
                tp.setName(e.getMessage());
                tp.getByPlayer(p).setState(TPillager_Set.SET_LOCATION);
                p.sendMessage("§2[2/3]§a Schreibe §2'!location'§a um den Mittelpunkt des SpawnUmkreises zu speichern");
            }else{
                if(e.getMessage().equalsIgnoreCase("!location")){
                    e.setCancelled(true);
                    tp.setSpawnloc(e.getPlayer().getLocation());
                    tp.end();
                    System.out.println("§2[3/3]§a Der TP-Villager wird erstellt!");
                }
            }

        }
    }

}
