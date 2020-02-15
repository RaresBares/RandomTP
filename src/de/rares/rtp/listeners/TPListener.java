package de.rares.rtp.listeners;

import de.rares.rtp.listeners.exec.ProcessEHit;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TPListener implements Listener {


    @EventHandler
    public void onInteract(EntityInteractEvent e){

        Entity entity = ((CraftEntity) e.getEntity()).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        entity.c(compound);
        if(compound.hasKey("tpworld") && compound.hasKey("x") && compound.hasKey("y") && compound.hasKey("z")) {
            new ProcessEHit(e);
        }

    }

}
