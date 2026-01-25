package CountyTitles.countyTitles;

import java.util.Arrays;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CountyTitlesCommand implements CommandExecutor {
    private final CountyTitles plugin;
    private final RegionManager regionManager;

    public CountyTitlesCommand(CountyTitles plugin, RegionManager regionManager) {
        this.plugin = plugin;
        this.regionManager = regionManager;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &fv1.5\n&bAvailable commands: &a/countytitles reload \n&a/countytitles addregion \n&a/countytitles setpos1 \n&a/countytitles setpos2 \n&a/countytitles title \n&a/countytitles subtitle\n&a/countytitles actionbar\n&a/countytitles disable\n&a/countytitles info \n&a/countytitles list\n&a/countytitles save\n&a/countytitles removeregion\n&a/countytitles regioninfo"));
            return true;
        } else if (args[0].equalsIgnoreCase("info")) {
            sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &fPlugin version 1.5\n&dSTABLE for 1.21.11\n&aCreated by brengeware"));
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("countytitles.reload")) {
                this.plugin.reloadConfig();
                this.regionManager.reloadRegions(this.plugin.getConfig());
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aConfiguration successfully reloaded."));
                return true;
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cYou do not have permission to use this command."));
                return true;
            }
        } else if (args[0].equalsIgnoreCase("addregion")) {
            if (args.length == 2) {
                this.regionManager.addRegion(args[1]);
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aRegion: &f" + args[1] + " &asuccessfully added."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles addregion <regionname>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("setpos1")) {
            if (args.length == 2 && sender instanceof Player) {
                Player player = (Player)sender;
                this.regionManager.setRegionPos1(args[1], player.getLocation());
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aPosition 1 for region: &f" + args[1] + " &aset."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles setpos1 <regionname>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("setpos2")) {
            if (args.length == 2 && sender instanceof Player) {
                Player player = (Player)sender;
                this.regionManager.setRegionPos2(args[1], player.getLocation());
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aPosition 2 for region: &f" + args[1] + " &aset."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles setpos2 <regionname>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("title")) {
            if (args.length >= 3) {
                String regionName = args[1];
                String title = String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 2, args.length));
                if (title.equals("''")) {
                    title = "";
                }

                this.regionManager.setRegionTitle(regionName, title);
                this.plugin.saveConfig();
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aTitle for region: &f" + regionName + " &aset to &f" + title + "&a."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles title <regionname> <title>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("subtitle")) {
            if (args.length >= 3) {
                String regionName = args[1];
                String subtitle = String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 2, args.length));
                if (subtitle.equals("''")) {
                    subtitle = "";
                }

                this.regionManager.setRegionSubtitle(regionName, subtitle);
                this.plugin.saveConfig();
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aSubtitle for region: &f" + regionName + " &aset to &f" + subtitle + "&a."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles subtitle <regionname> <subtitle>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("actionbar")) {
            if (args.length >= 3) {
                String regionName = args[1];
                String actionbarMessage = String.join(" ", (CharSequence[])Arrays.copyOfRange(args, 2, args.length));
                this.regionManager.setRegionActionbarMessage(regionName, actionbarMessage);
                this.plugin.saveConfig();
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aActionbar message for region: &f" + regionName + " &aset to &f" + actionbarMessage + "&a."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles actionbar <regionname> <message>"));
            }

            return true;
        } else if (args[0].equalsIgnoreCase("disable")) {
            if (args.length == 2) {
                String regionName = args[1];
                this.regionManager.disableRegionTitles(regionName);
                this.plugin.saveConfig();
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aTitles and subtitles for region: &f" + regionName + " &ahave been disabled."));
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles disable <regionname>"));
            }

            return true;
        } else if (!args[0].equalsIgnoreCase("list")) {
            if (args[0].equalsIgnoreCase("save")) {
                if (this.regionManager.hasIncompleteRegions()) {
                    sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cYou have incomplete regions. Please set both positions for all regions before saving."));
                } else {
                    this.plugin.saveConfig();
                    sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aAll regions & titles saved! &f(config.yml updated)"));
                }

                return true;
            } else if (args[0].equalsIgnoreCase("removeregion")) {
                if (args.length == 2) {
                    this.regionManager.removeRegion(args[1]);
                    sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aRegion: &f" + args[1] + " &asuccessfully removed."));
                } else {
                    sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cUsage: /countytitles removeregion <regionname>"));
                }

                return true;
            } else if (args[0].equalsIgnoreCase("regioninfo")) {
                if (sender instanceof Player) {
                    Player player = (Player)sender;
                    Region region = this.regionManager.getRegionByLocation(player.getLocation());
                    if (region != null) {
                        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &aYou are currently in region: &f" + region.getName() + "&a."));
                    } else {
                        sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cYou are not in any region."));
                    }
                } else {
                    sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &cThis command can only be used by players."));
                }

                return true;
            } else {
                sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize("&f&lCounty&b&lTitles &7&l>> &c&lHey! &7Sorry, that's an &cUnknown command.\n&bAvailable commands: &a/countytitles <reload | addregion | setpos1 | setpos2 | title | subtitle | actionbar | disable | info | list | save | removeregion | regioninfo>"));
                return true;
            }
        } else {
            StringBuilder regionList = new StringBuilder("&bRegions:\n");

            for(String regionName : this.regionManager.getRegionNames()) {
                regionList.append("&a- ").append(regionName).append("\n");
            }

            sender.sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(regionList.toString()));
            return true;
        }
    }
}
