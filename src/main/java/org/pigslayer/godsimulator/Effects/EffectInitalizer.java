package org.pigslayer.godsimulator.Effects;

import org.bukkit.event.Listener;
import org.pigslayer.godsimulator.Effects.Target.GodEffect;
import org.pigslayer.godsimulator.GodSimulator;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import slayerutils.slayerutils.QuickRegistering.QuickRegistering;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EffectInitalizer {
    private static List<GodEffect> effects = null;
    public static List<GodEffect> getEffects(){
        if(effects!=null)
            return effects;
        effects = new ArrayList<>();
        Reflections reflections = new Reflections("org.pigslayer.godsimulator");
        Set<String> annotatedClasses = reflections.get(Scanners.TypesAnnotated.with(RegisterEffect.class));

        for(String str : annotatedClasses){
            try {
                @SuppressWarnings("unchecked")
                Class<? extends GodEffect> clazz = (Class<? extends GodEffect>) Class.forName(str);
                GodEffect cs = clazz.getDeclaredConstructor().newInstance();
                if(cs instanceof Listener){
                    QuickRegistering.registerEvent(GodSimulator.instance,(Listener) cs);
                }
                effects.add(cs);
            }catch(Exception ignored){}
        }
        return effects;
    }
}
