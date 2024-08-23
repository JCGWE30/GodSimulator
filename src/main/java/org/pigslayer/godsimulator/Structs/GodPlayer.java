package org.pigslayer.godsimulator.Structs;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class GodPlayer implements Player {
    private boolean isGod = false;

    public boolean isGod(){
        return isGod;
    }

    public static GodPlayer convert(Player p){
        return (GodPlayer) p;
    }
    public static GodPlayer convert(CommandSender sender){
        Player p = (Player) sender;
        if(p==null) return null;
        return (GodPlayer) p;
    }
}
