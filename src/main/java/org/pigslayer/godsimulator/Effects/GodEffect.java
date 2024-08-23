package org.pigslayer.godsimulator.Effects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public interface GodEffect {
    String getName();
    String getDescription();
    Material getIcon();
    int getTargetType(); //0 Environmental, 1 Player
    void execute(Player p, Location loc);
}
