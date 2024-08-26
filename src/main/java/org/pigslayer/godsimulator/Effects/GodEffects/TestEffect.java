package org.pigslayer.godsimulator.Effects.GodEffects;

import org.bukkit.Material;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

@RegisterEffect
public class TestEffect implements PlayerTargetedGodEffect {

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
        GodPlayer player = players[0];
        player.getPlayer().sendMessage("Hey there sunshine");
    }
}
