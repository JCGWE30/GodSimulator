package org.pigslayer.godsimulator.God.Inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.pigslayer.godsimulator.Structs.GodPlayer;
import slayerutils.slayerutils.CustomInventories.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerSelectInventory {
    private List<GodPlayer> selectedPlayers = new ArrayList<>();

    public PlayerSelectInventory(GodPlayer p){
        List<GodPlayer> players = Bukkit.getOnlinePlayers().stream().map(GodPlayer::convert).collect(Collectors.toList());
        InnerInventory inner = new InnerInventory(7,getRowCount(0,players.size())-2,10);
        Template template = new Template()
                .name("Select Player(s)")
                .rows(getRowCount(0,players.size()))
                .setInnerInventory(inner, GodInventory.backgroundSlot)
                .updateOnClick();
        int count = 0;
        for (GodPlayer player : players) {
            Slot s = new Slot()
                    .location(inner.convertToInnerSlot(count))
                    .skull(player.getPlayer())
                    .name("§e"+player.getPlayer().getName())
                    .lore("§7They are not selected")
                    .clickAction((click) -> toggle(player))
                    .updateAction((slot) -> processPlayer(slot,player));


            template.addSlot(s);
            count++;
        }
        new CustomInventory(template,p.getPlayer());
    }

    private static int getRowCount(int page, int effects){
        int sub = effects - (page*28);
        int rows = (int)Math.ceil((double)sub/7);
        return rows+2;
    }

    private Slot processPlayer(Slot s,GodPlayer p){
        if(selectedPlayers.contains(p)){
                    return s.clone()
                    .type(Material.YELLOW_STAINED_GLASS_PANE)
                    .shiny()
                    .splitLore("§2They are selected");
        }else{
            return s
            .skull(p.getPlayer())
            .splitLore("§7They are not selected");
        }
    }

    private void toggle(GodPlayer p){
        if (!selectedPlayers.remove(p)) {
            selectedPlayers.add(p);
        }
    }
}
