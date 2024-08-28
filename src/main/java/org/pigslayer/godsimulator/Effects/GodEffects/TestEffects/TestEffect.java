package org.pigslayer.godsimulator.Effects.GodEffects.TestEffects;

import org.bukkit.Material;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.Arrays;

@RegisterEffect
public class TestEffect implements PlayerTargetedGodEffect {

    @Override
    public int getMinPlayers() {
        return 1;
    }
    @Override
    public int getMaxPlayers() {
        return 6;
    }

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
    public void execute(GodPlayer[] players) {
        Arrays.stream(players).map(GodPlayer::getPlayer).forEach((p)->{
            p.sendMessage("Hey there sunshine");
        });
    }
}
