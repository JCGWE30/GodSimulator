package org.pigslayer.godsimulator;

import org.bukkit.plugin.java.JavaPlugin;
import org.pigslayer.godsimulator.God.GodEvents;
import org.pigslayer.godsimulator.God.InventoryCommand;
import slayerutils.slayerutils.QuickRegistering.QuickRegistering;

public final class GodSimulator extends JavaPlugin {
    public static GodSimulator instance;

    @Override
    public void onEnable() {
        getCommand("godmenu").setExecutor(new InventoryCommand());
        instance=this;
        QuickRegistering.registerEvent(this,new GodEvents());
    }

    @Override
    public void onDisable() {

    }
}
