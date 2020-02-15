package de.rares.rtp;

import de.rares.rtp.tipillager.TPillager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;

public class RandomTP extends JavaPlugin {
    public static RandomTP randomTP;



    @Override
    public void onEnable() {
        System.out.println("Starting");
        randomTP = this;
        fetchTPillager();

    }

    public void fetchTPillager(){
        FileConfiguration cfg = randomTP.getConfig();
        for (String key : cfg.getKeys(false)) {
            System.out.println("Found: " + key);
            new TPillager(new Location(Bukkit.getWorld(cfg.getString(key+ ".sw")),Integer.parseInt(cfg.getString(key+ ".sx")),Integer.parseInt(cfg.getString(key+ ".sy")),Integer.parseInt(cfg.getString(key+ ".sz") )), new Location(Bukkit.getWorld(cfg.getString(key+ ".vw")),Integer.parseInt(cfg.getString(key+ ".vx")),Integer.parseInt(cfg.getString(key+ ".vy")),Integer.parseInt(cfg.getString(key+ ".vz") )), key);
        }
    }

    @Override
    public void onDisable() {
        TPillager.destroy();

    }
}
