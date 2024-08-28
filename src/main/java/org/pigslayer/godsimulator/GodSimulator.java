package org.pigslayer.godsimulator;

import org.bukkit.plugin.java.JavaPlugin;
import org.pigslayer.godsimulator.God.GodEvents;
import org.pigslayer.godsimulator.God.InventoryCommand;
import org.slf4j.LoggerFactory;
import slayerutils.slayerutils.QuickRegistering.QuickRegistering;
import slayerutils.slayerutils.TestingAparatus;

import java.util.logging.Logger;

public final class GodSimulator extends JavaPlugin {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(GodSimulator.class);
    public static GodSimulator instance;
    private static Logger logger;

    @Override
    public void onEnable() {
        if(!TestingAparatus.enabled(this))
            return;
        logger = getLogger();
        getCommand("godmenu").setExecutor(new InventoryCommand());
        instance=this;
        QuickRegistering.registerEvent(this,new GodEvents());
    }

    @Override
    public void onDisable() {

    }

    public static void log(String message){
        logger.info(message);
    }
}
