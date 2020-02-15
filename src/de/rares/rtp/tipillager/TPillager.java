package de.rares.rtp.tipillager;

import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TPillager   {

    public static ArrayList<TPillager> tPillagers = new ArrayList<>();

    Location spawnLoc;
    Location tpilloc;
    String name;
    public Entity vil;
    public int x;
    public int y;
    public int z;
    public String world;
    public TPillager(Location spawnLoc, Location tpilloc, String name) {
        this.spawnLoc = spawnLoc;
        this.tpilloc = tpilloc;
        this.name = name;
        tpilloc.setY(tpilloc.getBlockY());
        tpilloc.setZ(tpilloc.getBlockZ());
        tpilloc.setX(tpilloc.getBlockX());
        vil = tpilloc.getWorld().spawnEntity(tpilloc, EntityType.VILLAGER);
        tPillagers.add(this);
        setup();
    }

    public static TPillager getByEntity(Entity e){
        for (TPillager tPillager : tPillagers) {
            if(tPillager.vil  == e){
                return tPillager;
            }
        }
        return null;
    }

    private void setup(){

        vil.setCustomName(name.replace("&","§"));
        this.setNBTs();

    }
    private  void setNBTs(){
        net.minecraft.server.v1_8_R3.Entity nmsEntity = ((CraftEntity) vil).getHandle();
        NBTTagCompound compound = new NBTTagCompound();
        nmsEntity.c(compound);
       x = spawnLoc.getBlockX() ;
        y = spawnLoc.getBlockY() ;
       z =  spawnLoc.getBlockZ() ;
      world =   spawnLoc.getWorld().getName();

        compound.setInt("Invulnerable",1 );
        compound.setInt("NoAI",1 );
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
