package de.rares.rtp;

import de.rares.rtp.tipillager.TPillager;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class TPillgerCompound {

    public static ArrayList<TPillgerCompound> compounds = new ArrayList<>();
    Player p;
    TPillager_Set state;
    String name;
    Location tpiloc;
    Location spawnloc;

    public Location getTpiloc() {
        return tpiloc;
    }

    public void setTpiloc(Location tpiloc) {
        this.tpiloc = tpiloc;
    }

    public Location getSpawnloc() {
        return spawnloc;
    }

    public void setSpawnloc(Location spawnloc) {
        this.spawnloc = spawnloc;
    }

    public TPillgerCompound(Player p) {
        this.p = p;
        compounds.add(this);
        setState(TPillager_Set.SAYNAME);
    }

    public static ArrayList<TPillgerCompound> getCompounds() {
        return compounds;
    }

    public static void setCompounds(ArrayList<TPillgerCompound> compounds) {
        TPillgerCompound.compounds = compounds;
    }

    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public TPillager_Set getState() {
        return state;
    }

    public void setState(TPillager_Set state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TPillgerCompound getByPlayer(Player p) {
        final TPillgerCompound[] compound = {null};
        compounds.stream().forEach(t -> {

            if (t.p == p) {
                compound[0] = t;
            }

        });
        if (compound.length == 0) {
            return null;
        }
        return compound[0];

    }

    public static boolean exists(Player p) {
        if (getByPlayer(p) == null) {
            return false;
        }
        return true;
    }
    public void end(){
        compounds.remove(this);
       TPillager tp =  new TPillager(spawnloc, tpiloc, name);
        logInFile(tp);

    }



    private void logInFile(TPillager tp){
        JSONObject jsonObject = new JSONObject();
        FileConfiguration cfg = RandomTP.randomTP.getConfig();
        cfg.set(name + "." + "sx", tp.getSpawnLoc().getBlockX());
        cfg.set(name + "." + "sy", tp.getSpawnLoc().getBlockY());
        cfg.set(name + "." + "sz", tp.getSpawnLoc().getBlockZ());
        cfg.set(name + "." + "sw", tp.getSpawnLoc().getWorld().getName());

        cfg.set(name + "." + "vx", tp.getTpilloc().getBlockX());
        cfg.set(name + "." + "vy", tp.getTpilloc().getBlockY());
        cfg.set(name + "." + "vz", tp.getTpilloc().getBlockZ());
        cfg.set(name + "." + "vw", tp.getTpilloc().getWorld().getName());
        RandomTP.randomTP.saveConfig();
    }

}
