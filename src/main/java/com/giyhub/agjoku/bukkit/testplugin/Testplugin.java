package com.giyhub.agjoku.bukkit.testplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Material.IRON_INGOT;

public final class Testplugin<meta> extends JavaPlugin implements Listener {

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
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
                player.openInventory(inv);
                inv.setItem(1,item);
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if(event.getCurrentItem().getType().equals(IRON_INGOT)){
            event.getWhoClicked().sendMessage("バーカ");
        }

    }

    Inventory inv = Bukkit.createInventory(null, 9, "⊂二二二（  ＾ω＾）二⊃ﾌﾞｰﾝ");
    ItemStack item = new ItemStack(IRON_INGOT);


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        System.out.println("テストプラグインが有効になりました");

    }

    @Override
    public void onDisable() {

    }
}
