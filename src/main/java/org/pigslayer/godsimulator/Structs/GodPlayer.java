package org.pigslayer.godsimulator.Structs;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GodPlayer{
    private boolean isGod = true;
    private Player player;

    private GodPlayer(Player p){
        player=p;
    }

    public boolean isGod(){
        return isGod;
    }
    public Player getPlayer(){ return player; }

    public static GodPlayer convert(Player p){
        return new GodPlayer(p);
    }
    public static GodPlayer convert(CommandSender sender){
        Player p = (Player) sender;
        if(p==null) return null;
        return new GodPlayer(p);
    }
}
