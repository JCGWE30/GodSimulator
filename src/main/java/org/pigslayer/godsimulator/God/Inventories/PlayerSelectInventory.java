package org.pigslayer.godsimulator.God.Inventories;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.checkerframework.common.reflection.qual.GetMethod;
import org.pigslayer.godsimulator.Effects.Target.GodEffect;
import org.pigslayer.godsimulator.Effects.Target.PlayerTargetedGodEffect;
import org.pigslayer.godsimulator.GodSimulator;
import org.pigslayer.godsimulator.Structs.GodPlayer;
import slayerutils.slayerutils.CustomInventories.*;
import slayerutils.slayerutils.SlayerJson.SlayerJson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class PlayerSelectInventory {
    private class FakePlayer{
        public String name;
        public FakePlayer(String name){
            this.name=name;
        }
    }
    private List<GodPlayer> selectedPlayers = new ArrayList<>();
    private PlayerTargetedGodEffect selectedEffect;
    private GodPlayer viewer;
    private String dirtySlot;
    private boolean shouldClose = false;

    public PlayerSelectInventory(GodPlayer p, PlayerTargetedGodEffect effect){
        List<GodPlayer> players = Bukkit.getOnlinePlayers().stream().map(GodPlayer::convert).collect(Collectors.toList());
        viewer=p;
        selectedEffect=effect;

        InnerInventory inner = new InnerInventory(7,getRowCount(0,players.size())-2,10);
        Template template = new Template()
                .name("Select Player(s)")
                .rows(getRowCount(0,players.size()))
                .setInnerInventory(inner, GodInventory.backgroundSlot)
                .updateOnClick()
                //This is very odd, Not waiting a tick causes the meny to break, might wanna figure this one out.
                .closeAction(()->{if(shouldClose) return; Bukkit.getScheduler().runTaskLater(GodSimulator.instance,() -> {GodInventory.openInventory(p);},2);});
        int count = 0;

        Slot infoSlot = new Slot()
                .location(4)
                .splitLore("§7"+count+"/"+selectedEffect.getMaxPlayers()+" players selected")
                .updateAction(this::updateInfo)
                .clickAction(this::activateEffect);

        if(effect.getMinPlayers()==0){
            infoSlot.name("§eClick to activate effect")
                    .type(Material.GOLD_BLOCK);
        }else{
            infoSlot.name("§cYou need to select "+effect.getMinPlayers()+" more players")
                    .type(Material.REDSTONE_BLOCK);
        }

        template.addSlot(infoSlot);
        for (GodPlayer player : players) {
            Slot s = new Slot()
                    .location(inner.convertToInnerSlot(count))
                    .skull(player.getPlayer())
                    .name("§e"+player.getPlayer().getName())
                    .lore("§7They are not selected")
                    .clickAction((click) -> toggle(player.getPlayer().getName(),player))
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
        if(!Objects.equals(dirtySlot, p.getPlayer().getName()))
            return s;
        dirtySlot = null;
        if(selectedPlayers.contains(p)){
                    return s
                    .stripSkull()
                    .type(Material.YELLOW_STAINED_GLASS_PANE)
                    .shiny()
                    .splitLore("§2They are selected");
        }else{
            return s
            .skull(getSkinURL(p.getPlayer().getName()))
            .splitLore("§7They are not selected");
        }
    }

    private Slot updateInfo(Slot s){
        int count = selectedPlayers.size();
        s.splitLore("§7"+count+"/"+selectedEffect.getMaxPlayers()+" players selected");

        if(selectedEffect.getMinPlayers()>count){
            s.name("§cYou need to select "+(selectedEffect.getMinPlayers()-count)+" more players")
                    .type(Material.REDSTONE_BLOCK);
        }else if(selectedEffect.getMaxPlayers()<count){
            s.name("§cYou need to select "+(count-selectedEffect.getMaxPlayers())+" less players")
                    .type(Material.REDSTONE_BLOCK);
        }else{
            s.name("§eClick to activate effect")
                    .type(Material.GOLD_BLOCK);
        }
        return s;
    }

    private void activateEffect(ClickType ct){
        shouldClose=true;
        viewer.getPlayer().closeInventory();
        selectedEffect.execute(selectedPlayers.toArray(new GodPlayer[0]));
    }

    private void toggle(String s,GodPlayer p){
        dirtySlot = s;
        if (!selectedPlayers.remove(p)) {
            selectedPlayers.add(p);
        }
    }

    //Debugging function
    private List<FakePlayer> getFakes(){
        List<FakePlayer> fakes = Arrays.asList(
                "Hypixel",
                "LePigSlayer",
                "TheGoldorChad",
                "Refraction",
                "tialaontheroad",
                "I_love_skywars",
                "TonSteineScherbe",
                "Max34d1p",
                "tsukitaiyo",
                "xNiekapek",
                "eepygirlluna",
                "SalamancaJr",
                "IllariQuispe",
                "axusss",
                "BiBiBo_9RgF1D"
        ).stream().map(FakePlayer::new).collect(Collectors.toList());
        return fakes;
    }

    private String getSkinURL(String username){
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet get = new HttpGet("https://api.mojang.com/users/profiles/minecraft/"+username);
            CloseableHttpResponse response = client.execute(get);

            String jsonString = EntityUtils.toString(response.getEntity());

            SlayerJson json = SlayerJson.loadFromString(jsonString);

            String uid = json.get("id");
            HttpGet textureGet = new HttpGet("https://sessionserver.mojang.com/session/minecraft/profile/"+uid);
            response = client.execute(textureGet);

            jsonString = EntityUtils.toString(response.getEntity());
            json = SlayerJson.loadFromString(jsonString);

            String texture = json.getJsonList("properties").get(0).get("value");
            return decryptTextures(texture);

        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String decryptTextures(String textures){
        byte[] decoded = Base64.getDecoder().decode(textures);
        String decodedString = new String(decoded, StandardCharsets.UTF_8);
        SlayerJson textureInfo = SlayerJson.loadFromString(decodedString);
        return textureInfo.getSub("textures").getSub("SKIN").get("url");
    }
}
