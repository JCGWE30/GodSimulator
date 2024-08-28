package org.pigslayer.godsimulator.Effects.GodEffects.Effects.EnvTargeted;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.EnvironmentTargetedGodEffect;

@RegisterEffect
public class Bomb implements EnvironmentTargetedGodEffect {
    @Override
    public void execute(World world, Location loc) {
        world.createExplosion(loc,4);
    }

    @Override
    public String getName() {
        return "Explosions";
    }

    @Override
    public String getDescription() {
        return "Cast down explosions";
    }

    @Override
    public Material getIcon() {
        return Material.TNT;
    }
}
