package org.pigslayer.godsimulator.Effects.GodEffects.Effects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RegisterEffect
public class LightningSmite implements PlayerTargetedGodEffect, Listener {
    @Override
    public int getMaxPlayers() {
        return PlayerTargetedGodEffect.super.getMaxPlayers();
    }

    private List<Player> smitedPlayers = new ArrayList<>();

    @Override
    public void execute(GodPlayer[] players) {
        smitedPlayers.clear();
        smitedPlayers = Arrays.stream(players).map(GodPlayer::getPlayer).collect(Collectors.toList());
        smitedPlayers.forEach((p)->{
            p.sendMessage("§e§lYOU HAVE BEEN SMITED SMITED!!!");
            for(int i=0;i<50;i++){
                p.getWorld().strikeLightning(p.getLocation());
            }
            p.damage(9999999);
        });
    }

    @EventHandler
    public void die(PlayerDeathEvent e){
        if(smitedPlayers.contains(e.getEntity()))
            e.setDeathMessage(e.getEntity().getName()+" was smited by §e§lZEUS");
    }

    @Override
    public String getName() {
        return "§lLIGHTNING SMITE";
    }

    @Override
    public String getDescription() {
        return "SMITES players with lightning killing them";
    }

    @Override
    public Material getIcon() {
        return Material.END_ROD;
    }
}
