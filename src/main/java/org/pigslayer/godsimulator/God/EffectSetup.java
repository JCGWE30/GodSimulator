package org.pigslayer.godsimulator.God;

import org.pigslayer.godsimulator.Effects.Target.GodEffect;
import org.pigslayer.godsimulator.God.Inventories.PlayerSelectInventory;
import org.pigslayer.godsimulator.Structs.GodPlayer;

public class EffectSetup {
    public static void callEffect(GodEffect effect, GodPlayer p){
        if(effect.isPlayerTarget()){
            new PlayerSelectInventory(p);
        }else{
            //Shit goes here
        }
    }
}
