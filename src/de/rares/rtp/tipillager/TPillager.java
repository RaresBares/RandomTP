package de.rares.rtp.tipillager;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TPillager   {

    public static ArrayList<TPillager> tPillagers = new ArrayList<>();

    Location spawnLoc;
    Location tpilloc;
    String name;
    public Entity vil;
    public TPillager(Location spawnLoc, Location tpilloc, String name) {
        this.spawnLoc = spawnLoc;
        this.tpilloc = tpilloc;
        this.name = name;
        vil = tpilloc.getWorld().spawnEntity(tpilloc, EntityType.VILLAGER);
        tPillagers.add(this);
    }

    private void setup(){

        vil.setCustomName(name);
        this.setNBTs();

    }
    private  void setNBTs(){
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) vil).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEntity.c(compound);
        compound.setString("x", String.valueOf(spawnLoc.getBlockX()));
        compound.setString("y", String.valueOf(spawnLoc.getBlockY()));
        compound.setString("z", String.valueOf(spawnLoc.getBlockZ()));
        compound.setString("world", spawnLoc.getWorld().getName());
        nmsEntity.f(compound);

    }


    public Location getSpawnLoc() {
        return spawnLoc;
    }

    public void setSpawnLoc(Location spawnLoc) {
        this.spawnLoc = spawnLoc;
    }

    public Location getTpilloc() {
        return tpilloc;
    }

    public void setTpilloc(Location tpilloc) {
        this.tpilloc = tpilloc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void destroy(){
        tPillagers.forEach(c -> {
           c.vil.remove();
           tPillagers.remove(c);
            c = null;
        });
    }

}
