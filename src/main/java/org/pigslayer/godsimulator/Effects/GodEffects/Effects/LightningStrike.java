package org.pigslayer.godsimulator.Effects.GodEffects.Effects;

import org.bukkit.Material;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.Arrays;

@RegisterEffect
public class LightningStrike implements PlayerTargetedGodEffect {
    @Override
    public int getMaxPlayers() {
        return 50;
    }

    @Override
    public void execute(GodPlayer[] players) {
        Arrays.stream(players).map(GodPlayer::getPlayer).forEach((p)->{
            p.sendMessage("§eYou have been Striked!!!");
            p.getWorld().strikeLightning(p.getLocation());
        });
    }

    @Override
    public String getName() {
        return "Lightning Strike";
    }

    @Override
    public String getDescription() {
        return "Strike players with lighting!";
    }

    @Override
    public Material getIcon() {
        return Material.LIGHTNING_ROD;
    }
}
