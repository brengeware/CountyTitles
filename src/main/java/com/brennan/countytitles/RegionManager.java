package com.brennan.countytitles;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class RegionManager {
    private final Map<String, Region> regions = new HashMap();
    private final FileConfiguration config;

    public RegionManager(FileConfiguration config) {
        this.config = config;
        this.loadRegions(config);
    }

    public void addRegion(String name) {
        this.regions.put(name, new Region(name));
    }

    public void setRegionPos1(String name, Location pos1) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setPos1(pos1);
            this.config.set("regions." + name + ".pos1", pos1);
        }

    }

    public void setRegionPos2(String name, Location pos2) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setPos2(pos2);
            this.config.set("regions." + name + ".pos2", pos2);
        }

    }

    public void setRegionTitle(String name, String title) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setTitle(title);
            this.config.set("regions." + name + ".title", title);
        }

    }

    public void setRegionSubtitle(String name, String subtitle) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setSubtitle(subtitle);
            this.config.set("regions." + name + ".subtitle", subtitle);
        }

    }

    public void setRegionActionbarMessage(String name, String actionbarMessage) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setActionbarMessage(actionbarMessage);
            this.config.set("regions." + name + ".actionbar-message", actionbarMessage);
        }

    }

    public void disableRegionTitles(String name) {
        Region region = (Region)this.regions.get(name);
        if (region != null) {
            region.setTitle("");
            region.setSubtitle("");
            this.config.set("regions." + name + ".title", "");
            this.config.set("regions." + name + ".subtitle", "");
        }

    }

    public Region getRegionByLocation(Location location) {
        for(Region region : this.regions.values()) {
            if (region.contains(location)) {
                return region;
            }
        }

        return null;
    }

    public Iterable<String> getRegionNames() {
        return this.regions.keySet();
    }

    public boolean hasIncompleteRegions() {
        for(Region region : this.regions.values()) {
            if (region.getPos1() == null || region.getPos2() == null) {
                return true;
            }
        }

        return false;
    }

    public void reloadRegions(Configuration config) {
        this.regions.clear();
        this.loadRegions(config);
    }

    public void removeRegion(String name) {
        this.regions.remove(name);
        this.config.set("regions." + name, (Object)null);
    }

    private void loadRegions(Configuration config) {
        ConfigurationSection regionsSection = config.getConfigurationSection("regions");
        if (regionsSection != null) {
            for(String regionName : regionsSection.getKeys(false)) {
                String title = regionsSection.getString(regionName + ".title", "&oAwaiting Title");
                String subtitle = regionsSection.getString(regionName + ".subtitle", "&a&o/countytitles subtitle <regionname> <subtitle>");
                String actionbarMessage = regionsSection.getString(regionName + ".actionbar-message", "&a&o/countytitles actionbar <regionname> <actionbarmessage>");
                Location pos1 = regionsSection.getLocation(regionName + ".pos1");
                Location pos2 = regionsSection.getLocation(regionName + ".pos2");
                Region region = new Region(regionName, title, subtitle, actionbarMessage);
                region.setPos1(pos1);
                region.setPos2(pos2);
                this.regions.put(regionName, region);
            }
        }

    }
}