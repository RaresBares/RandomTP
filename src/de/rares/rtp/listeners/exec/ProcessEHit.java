package de.rares.rtp.listeners.exec;

import de.rares.rtp.tipillager.TPillager;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ProcessEHit extends Thread{

    private final PlayerInteractEntityEvent e;

    public ProcessEHit(PlayerInteractEntityEvent e){
        this.e = e;
       start();
    }


    @Override
    public void run() {
        TPillager tp = TPillager.getByEntity(e.getRightClicked());
        String worldname = tp.world;
        int x = tp.z;
        int y =  tp.y;
        int z =  tp.z;

        World world = Bukkit.getWorld(worldname);
        Location loc  = new Location(world, x, y, z);
        Block b = loc.getBlock();
        loc.setX(loc.getX() * Math.random() * 10000);
        loc.setZ(loc.getZ() * Math.random() * 10000);

        while (b.getType() != Material.AIR ){

            loc.setY(loc.getY() +1);
        }

            e.getPlayer().teleport(loc);



    }
}
