package de.rares.rtp.listeners.exec;

import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityInteractEvent;

public class ProcessEHit extends Thread{

    private final EntityInteractEvent e;

    public ProcessEHit(EntityInteractEvent e){
        this.e = e;
        start();
    }


    @Override
    public void run() {
        Entity entity = ((CraftEntity) e.getEntity()).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        entity.c(compound);
        String worldname = compound.getString("tpworld");
        int x = Integer.parseInt(compound.getString("x"));
        int y = Integer.parseInt(compound.getString("y"));
        int z = Integer.parseInt(compound.getString("z"));

        World world = Bukkit.getWorld(worldname);
        Location loc  = new Location(world, x, y, z);
        Block b = loc.getBlock();
        loc.setX(loc.getX() * Math.random() * 10000);
        loc.setZ(loc.getZ() * Math.random() * 10000);
        while (b.getType() != Material.AIR){
            loc.setY(loc.getY() +1);
        }
        if(e.getEntity() instanceof Player){
            e.getEntity().teleport(loc);
        }

    }
}
