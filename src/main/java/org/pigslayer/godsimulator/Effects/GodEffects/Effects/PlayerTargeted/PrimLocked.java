package org.pigslayer.godsimulator.Effects.GodEffects.Effects.PlayerTargeted;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.pigslayer.godsimulator.Effects.RegisterEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.Structs.GodPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RegisterEffect
public class PrimLocked implements PlayerTargetedGodEffect {
    private static final ItemStack[] armorItems = Arrays.stream(new Material[]{
            Material.CARVED_PUMPKIN,
            Material.LEATHER_CHESTPLATE,
            Material.LEATHER_LEGGINGS,
            Material.LEATHER_BOOTS
    }).map((m)-> new ItemStack(m,1)).toArray(ItemStack[]::new);

    @Override
    public void start(){
        Arrays.stream(armorItems).forEach((i)->{
            ItemMeta meta = i.getItemMeta();
            meta.setDisplayName("§e§lPRIM LOCKED!!!");
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS,ItemFlag.HIDE_ATTRIBUTES);
            meta.addEnchant(Enchantment.BINDING_CURSE,1,false);
            meta.addEnchant(Enchantment.UNBREAKING,255,true);
            i.setItemMeta(meta);
        });
    }

    @Override
    public int getMaxPlayers() {
        return 20;
    }

    @Override
    public void execute(GodPlayer[] players) {
        Arrays.stream(players).map(GodPlayer::getPlayer).forEach((p)->{
            p.sendMessage("§eSomethings off, Did your armor change?");
            p.getInventory().setHelmet(armorItems[0]);
            p.getInventory().setChestplate(armorItems[1]);
            p.getInventory().setLeggings(armorItems[2]);
            p.getInventory().setBoots(armorItems[3]);
        });
    }

    @Override
    public String getName() {
        return "Prim Locked";
    }

    @Override
    public String getDescription() {
        return "Forces players into a kit of terrible armor";
    }

    @Override
    public Material getIcon() {
        return Material.LEATHER_HELMET;
    }
}
