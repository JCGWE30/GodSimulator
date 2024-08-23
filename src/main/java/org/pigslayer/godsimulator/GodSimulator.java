package org.pigslayer.godsimulator;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.pigslayer.godsimulator.God.GodEvents;
import org.pigslayer.godsimulator.Structs.GodPlayer;
import slayerutils.slayerutils.QuickRegistering.QuickRegistering;

public final class GodSimulator extends JavaPlugin {
    public static GodSimulator instance;
    public static GodPlayer god;

    @Override
    public void onEnable() {
        instance=this;
        QuickRegistering.registerEvent(this,new GodEvents());
    }

    @Override
    public void onDisable() {

    }
}
