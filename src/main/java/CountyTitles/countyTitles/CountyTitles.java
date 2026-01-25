package CountyTitles.countyTitles;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.Title.Times;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CountyTitles extends JavaPlugin {
    private RegionManager regionManager;
    private Map<Player, Region> playerRegions = new HashMap();

    public void onEnable() {
        this.saveDefaultConfig();
        this.regionManager = new RegionManager(this.getConfig());
        Bukkit.getConsoleSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&aCountyTitles &bv1.5 &ahas started up!\n               &fMy very first plugin! \n&dCreated with love, by brengeware"));
        this.getCommand("countytitles").setExecutor(new CountyTitlesCommand(this, this.regionManager));
        this.getServer().getPluginManager().registerEvents(new RegionEnterListener(this, this.regionManager), this);
        (new BukkitRunnable() {
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    Location loc = player.getLocation();
                    Region currentRegion = CountyTitles.this.regionManager.getRegionByLocation(loc);
                    Region previousRegion = (Region)CountyTitles.this.playerRegions.get(player);
                    if (currentRegion != null && !currentRegion.equals(previousRegion)) {
                        String titleText = CountyTitles.this.getConfig().getString("regions." + currentRegion.getName() + ".title", "&oAwaiting Title");
                        String subtitleText = CountyTitles.this.getConfig().getString("regions." + currentRegion.getName() + ".subtitle", "&a&o/countytitles subtitle <regionname> <subtitle>");
                        Component titleComponent = titleText.isEmpty() ? Component.empty() : LegacyComponentSerializer.legacyAmpersand().deserialize(titleText);
                        Component subtitleComponent = LegacyComponentSerializer.legacyAmpersand().deserialize(subtitleText);
                        Title title = Title.title(titleComponent, subtitleComponent, Times.times(Duration.ofSeconds(1L), Duration.ofSeconds(3L), Duration.ofSeconds(1L)));
                        player.showTitle(title);
                        CountyTitles.this.playerRegions.put(player, currentRegion);
                    } else if (currentRegion == null && previousRegion != null) {
                        CountyTitles.this.playerRegions.remove(player);
                    }
                }

            }
        }).runTaskTimer(this, 0L, 20L);
    }

    public RegionManager getRegionManager() {
        return this.regionManager;
    }

    public void onDisable() {
        this.getLogger().info("CountyTitles has been disabled!");
    }
}
