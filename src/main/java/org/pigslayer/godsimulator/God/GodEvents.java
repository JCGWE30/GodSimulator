package org.pigslayer.godsimulator.God;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GodEvents implements Listener {
    @EventHandler
    private void fireArrow(PlayerInteractEvent e){
        if(e.getAction()!= Action.LEFT_CLICK_AIR)
            return;
        EffectSetup.interactThrow(e.getPlayer());
    }
}
