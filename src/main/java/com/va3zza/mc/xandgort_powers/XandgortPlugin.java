package com.va3zza.mc.xandgort_powers;

import kr.entree.spigradle.annotations.PluginMain;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

import com.va3zza.mc.xandgort_powers.events.PlayerPunchEvent;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

@PluginMain
public class XandgortPlugin extends JavaPlugin {

    // Since this plugin only effects the XANDGORT player, we need to know the player ID
    public static final UUID XANDGORT_UUID = UUID.fromString("473a01d3-e7f2-4ffc-aeee-9eb8579336c8");

    private Logger logger;

    public XandgortPlugin() {
        // Init the logger
        this.logger = Bukkit.getLogger();
    }

    public XandgortPlugin(JavaPluginLoader loader, PluginDescriptionFile description, File dataFolder, File file) {
        super(loader, description, dataFolder, file);
    }

    @Override
    public void onEnable() {
        logger.info("XANDGORT Powers ENABLED!");

        // Register punch events
        logger.info("Registering event handler for player punch");
        super.getServer().getPluginManager().registerEvents(new PlayerPunchEvent(this.logger), this);

    } 
    
}