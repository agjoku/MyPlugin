package com.giyhub.agjoku.bukkit.testplugin;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import static org.bukkit.Material.*;

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
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if(player.getInventory().getItemInMainHand().getType() == Material.DIAMOND) {
                player.openInventory(inv);
                inv.setItem(0,iron_ingot);
                inv.setItem(2,gold_ingot);
                inv.setItem(4,emerald);
                inv.setItem(6,diamond);
                inv.setItem(8, nephrite_ingot);
            }
        }
    }

    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if(event.getCurrentItem().getType().equals(IRON_INGOT)){
            player.sendMessage("バーカ");
            player.closeInventory();
        }
        if(event.getCurrentItem().getType().equals(GOLD_INGOT)) {
            player.giveExpLevels(100);
            player.closeInventory();
            player.sendMessage("レベルを100LV与えました");

        }
        if(event.getCurrentItem().getType().equals(EMERALD)) {
            player.addPotionEffect(new PotionEffect(potionEffectType,2000,100));
            player.closeInventory();
            player.sendMessage("幸運レベル100を100秒付与しました");
        }
        if(event.getCurrentItem().getType().equals(DIAMOND)) {
            player.setGameMode(GameMode.CREATIVE);
            player.closeInventory();
            player.sendMessage("ゲームモードをクリエイティブに変更しました");
        }
        if(event.getCurrentItem().getType().equals(NETHERITE_INGOT)) {
            player.kickPlayer("サーバーから退出しました。\nまた来てね(´・ω・｀)");
            Bukkit.broadcastMessage(player.getName() + "が退出しました");
        }


    }

    Inventory inv = Bukkit.createInventory(null, 9, "⊂二二二（  ＾ω＾）二⊃ﾌﾞｰﾝ");
    ItemStack iron_ingot = new ItemStack(IRON_INGOT);
    ItemStack gold_ingot = new ItemStack(GOLD_INGOT);
    ItemStack emerald = new ItemStack(EMERALD);
    ItemStack diamond = new ItemStack(DIAMOND);
    ItemStack nephrite_ingot = new ItemStack(NETHERITE_INGOT);

    PotionEffectType potionEffectType = PotionEffectType.LUCK;


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        System.out.println("テストプラグインが有効になりました");

    }

    @Override
    public void onDisable() {

    }
}
