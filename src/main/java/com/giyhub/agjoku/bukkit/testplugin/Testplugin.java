package com.giyhub.agjoku.bukkit.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.conversations.PlayerNamePrompt;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin extends JavaPlugin implements Listener {

    @EventHandler
    public void onBlockCook(BlockCookEvent e) {
        Bukkit.broadcastMessage("料理ができました");
    }

    @EventHandler
    public void onPlayerFishEvent(PlayerFishEvent event){
        World w = getServer().getWorld("world");
        Player player = event.getPlayer();

        Location location = player.getLocation();

        w.createExplosion(location,100,false);

        Bukkit.broadcastMessage("釣り竿を使ったので"+ event.getPlayer().getName() +"を爆発させました");
        Bukkit.broadcastMessage("§cこの爆発の影響で周りのブロックも破壊されたことでしょう\n"+
                "なぜこのようなことが起きたのかよく考え次は同じ過ちを起こさないようにしましょう");
    }

    @EventHandler
    public void onPlayerInteractEntityEvent(PlayerInteractAtEntityEvent entityEvent){
        Bukkit.broadcastMessage("右クリック");
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        System.out.println("テストプラグインが有効になりました");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
