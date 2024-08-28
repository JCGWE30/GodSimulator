package org.pigslayer.godsimulator.Effects.GodEffects.Effects.EnvTargeted;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.EnvironmentTargetedGodEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.Arrays;

@RegisterEffect
public class LightningStrike implements EnvironmentTargetedGodEffect {

    @Override
    public void execute(World wld, Location loc) {
        wld.strikeLightning(loc);
    }

    @Override
    public String getName() {
        return "Lightning Strike";
    }

    @Override
    public String getDescription() {
        return "Rain down hellfire from above";
    }

    @Override
    public Material getIcon() {
        return Material.LIGHTNING_ROD;
    }
}
