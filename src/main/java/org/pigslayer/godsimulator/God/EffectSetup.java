package org.pigslayer.godsimulator.God;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;
import org.pigslayer.godsimulator.Effects.Target.EnvironmentTargetedGodEffect;
import org.pigslayer.godsimulator.Effects.Target.GodEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.God.Inventories.PlayerSelectInventory;
import org.pigslayer.godsimulator.GodSimulator;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.HashMap;

public class EffectSetup {
    private static HashMap<Player,EnvironmentTargetedGodEffect> callingEffects = new HashMap<>();
    public static void callEffect(GodEffect effect, GodPlayer p){
        if(effect.isPlayerTarget()){
            new PlayerSelectInventory(p,(PlayerTargetedGodEffect) effect);
        }else{
            callingEffects.put(p.getPlayer(),(EnvironmentTargetedGodEffect) effect);
            p.getPlayer().closeInventory();
            new BukkitRunnable(){
                @Override
                public void run() {
                    p.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,new TextComponent("§eSelected Effect: §a"+effect.getName()));
                    if(!callingEffects.containsKey(p.getPlayer()))
                        this.cancel();
                }
            }.runTaskTimer(GodSimulator.instance,1,1);
        }
    }

    public static void interactThrow(Player p){
        if(!callingEffects.containsKey(p))
            return;
        World wld = p.getWorld();
        EnvironmentTargetedGodEffect effect = callingEffects.get(p);

        BlockIterator blocks = new BlockIterator(p.getEyeLocation(),0,0);

        while(blocks.hasNext()){
            Block b = blocks.next();
            if(!b.isEmpty()){
                effect.execute(wld,b.getLocation());
                break;
            }
        }
    }

    public static void openMenu(GodPlayer p){
        callingEffects.remove(p.getPlayer());
    }
}
