package org.pigslayer.godsimulator.God;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.pigslayer.godsimulator.God.Inventories.GodInventory;
import org.pigslayer.godsimulator.Structs.GodPlayer;

public class InventoryCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        GodPlayer p = GodPlayer.convert(commandSender);
        if(p==null||!p.isGod()) return true;
        GodInventory.openInventory(p);
        return true;
    }
}
