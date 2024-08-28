package org.pigslayer.godsimulator.God.Inventories;

import org.bukkit.Material;
import org.pigslayer.godsimulator.Effects.EffectInitalizer;
import org.pigslayer.godsimulator.Effects.Target.GodEffect;
import org.pigslayer.godsimulator.God.EffectSetup;
import org.pigslayer.godsimulator.Structs.GodPlayer;
import slayerutils.slayerutils.CustomInventories.*;

public class GodInventory {
    protected final static Slot backgroundSlot = new Slot()
            .type(Material.BEDROCK)
            .name(" ");

    public static void openInventory(GodPlayer p){
        InnerInventory inner = new InnerInventory(7,getRowCount(0, EffectInitalizer.getEffects().size())-2,10);
        Template godMenu = new Template()
                .name("Effects")
                .rows(getRowCount(0, EffectInitalizer.getEffects().size()))
                .updateOnClick()
                .setInnerInventory(inner, backgroundSlot);
        int count = 0;
        for (GodEffect effect : EffectInitalizer.getEffects()){
            godMenu.addSlot(new Slot()
                    .location(inner.convertToInnerSlot(count))
                    .name(effect.getName())
                    .splitLore(effect.getDescription())
                    .type(effect.getIcon())
                    .amount(1)
                    .clickAction((click) -> {
                        EffectSetup.callEffect(effect,p);
                    }));
                    count++;
        }
        EffectSetup.openMenu(p);
        new CustomInventory(godMenu,p.getPlayer());
    }

    private static int getRowCount(int page, int effects){
        int sub = effects - (page*28);
        int rows = (int)Math.ceil((double)sub/7);
        return rows+2;
    }
}
