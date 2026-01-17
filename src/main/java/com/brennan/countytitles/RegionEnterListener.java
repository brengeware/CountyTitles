package com.brennan.countytitles;

import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class RegionEnterListener implements Listener {
    private final CountyTitles plugin;
    private final RegionManager regionManager;
    private final Map<Player, BukkitRunnable> playerTasks = new HashMap();

    public RegionEnterListener(CountyTitles plugin, RegionManager regionManager) {
        this.plugin = plugin;
        this.regionManager = regionManager;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Region region = this.regionManager.getRegionByLocation(player.getLocation());
        if (region != null) {
            String actionBarMessage = region.getActionbarMessage();
            this.sendActionBarMessage(player, actionBarMessage.replace("{region}", region.getName()));
        } else {
            this.cancelTask(player);
        }

    }

    private void sendActionBarMessage(final Player player, final String message) {
        this.cancelTask(player);
        BukkitRunnable task = new BukkitRunnable() {
            public void run() {
                player.sendActionBar(LegacyComponentSerializer.legacyAmpersand().deserialize(message));
            }
        };
        task.runTaskTimer(this.plugin, 0L, 20L);
        this.playerTasks.put(player, task);
    }

    private void cancelTask(Player player) {
        BukkitRunnable task = (BukkitRunnable)this.playerTasks.remove(player);
        if (task != null) {
            task.cancel();
        }

    }
}