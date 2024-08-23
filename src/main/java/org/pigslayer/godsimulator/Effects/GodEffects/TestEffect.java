package org.pigslayer.godsimulator.Effects.GodEffects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.pigslayer.godsimulator.Effects.GodEffect;
import org.pigslayer.godsimulator.Effects.RegisterEffect;

@RegisterEffect
public class TestEffect implements GodEffect {

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public String getDescription() {
        return "Test";
    }

    @Override
    public Material getIcon() {
        return Material.GLASS;
    }

    @Override
    public int getTargetType() {
        return 0;
    }

    @Override
    public void execute(Player p, Location loc) {

    }
}
