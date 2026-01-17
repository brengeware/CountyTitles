package com.brennan.countytitles;

import org.bukkit.Location;

public class Region {
    private String name;
    private String title;
    private String subtitle;
    private String actionbarMessage;
    private Location pos1;
    private Location pos2;

    public Region(String name) {
        this.name = name;
        this.title = "&oAwaiting Title";
        this.subtitle = "&a&o/countytitles subtitle <regionname> <subtitle>";
        this.actionbarMessage = "&a&o/countytitles actionbar <regionname> <actionbarmessage>";
    }

    public Region(String name, String title, String subtitle, String actionbarMessage) {
        this.name = name;
        this.title = title;
        this.subtitle = subtitle;
        this.actionbarMessage = actionbarMessage;
    }

    public void setPos1(Location pos1) {
        this.pos1 = pos1;
    }

    public void setPos2(Location pos2) {
        this.pos2 = pos2;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setActionbarMessage(String actionbarMessage) {
        this.actionbarMessage = actionbarMessage;
    }

    public boolean contains(Location location) {
        if (this.pos1 != null && this.pos2 != null && location != null) {
            double minX = Math.min(this.pos1.getX(), this.pos2.getX());
            double maxX = Math.max(this.pos1.getX(), this.pos2.getX());
            double minY = Math.min(this.pos1.getY(), this.pos2.getY());
            double maxY = Math.max(this.pos1.getY(), this.pos2.getY());
            double minZ = Math.min(this.pos1.getZ(), this.pos2.getZ());
            double maxZ = Math.max(this.pos1.getZ(), this.pos2.getZ());
            double locX = location.getX();
            double locY = location.getY();
            double locZ = location.getZ();
            return locX >= minX && locX <= maxX && locY >= minY && locY <= maxY && locZ >= minZ && locZ <= maxZ;
        } else {
            return false;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getActionbarMessage() {
        return this.actionbarMessage;
    }

    public Location getPos1() {
        return this.pos1;
    }

    public Location getPos2() {
        return this.pos2;
    }
}