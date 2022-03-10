package com.giyhub.agjoku.bukkit.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Testplugin extends JavaPlugin implements Listener {

    @EventHandler
    public void onBlockCook(BlockCookEvent e) {
        Bukkit.broadcastMessage("料理ができました");
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
