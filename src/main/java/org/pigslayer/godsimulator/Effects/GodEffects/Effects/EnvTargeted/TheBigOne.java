package org.pigslayer.godsimulator.Effects.GodEffects.Effects.EnvTargeted;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.EnvironmentTargetedGodEffect;

@RegisterEffect
public class TheBigOne implements EnvironmentTargetedGodEffect {
    @Override
    public void execute(World world, Location loc) {
        world.createExplosion(loc,50);
    }

    @Override
    public String getName() {
        return "The Big One";
    }

    @Override
    public String getDescription() {
        return "Explosive... VERY BIG EXPLOSIVE";
    }

    @Override
    public Material getIcon() {
        return Material.TNT_MINECART;
    }
}
