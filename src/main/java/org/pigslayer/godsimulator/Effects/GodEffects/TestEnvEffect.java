package org.pigslayer.godsimulator.Effects.GodEffects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.EnvironmentTargetedGodEffect;

@RegisterEffect
public class TestEnvEffect implements EnvironmentTargetedGodEffect {
    @Override
    public String getName() {
        return "Test Environment";
    }

    @Override
    public String getDescription() {
        return "Test Environment";
    }

    @Override
    public void execute(World world,Location loc) {
        world.strikeLightning(loc);
    }
}
