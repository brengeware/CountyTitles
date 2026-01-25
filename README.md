# CountyTitles

CountyTitles is a lightweight Minecraft Paper plugin that displays custom titles and subtitles to players when they enter defined regions in the world. Regions are created in-game using player positions and are fully world-specific, meaning regions in the Overworld will not trigger in the Nether or the End.

This plugin was created as a learning project and focuses on simplicity, performance, and clear configuration.

---

## Features

- Create cuboid regions using player position commands (no WorldEdit required)
- Display titles and subtitles when players enter a region
- Regions are world-specific and do not trigger across dimensions
- Titles are only shown when entering a new region (no spam)
- Supports legacy color codes using `&`
- Uses Paper’s Adventure API for modern title handling
- Lightweight and efficient (location checks run once per second)

---

## Requirements

- Paper 1.21.11
- Java 21

---

## Installation

1. Download or build the CountyTitles plugin JAR.
2. Place the JAR file into your server’s `plugins` folder.
3. Start or restart the server.
4. A default `config.yml` will be generated automatically.

---

## Creating Regions

Regions are defined by selecting two positions in the world.

1. Stand at the first corner of the region and run:
   /countytitles setpos1 <regionname>
2. Move to the opposite corner and run:
   /countytitles setpos2 <regionname>

The region is now created in the world you were standing in when the positions were set.

---

## Setting Titles and Subtitles

Set a title for a region:
/countytitles title <regionname> <title>


Set a subtitle for a region:
/countytitles subtitle <regionname> <subtitle>


### Legacy color codes using `&` are supported, for example:
&bBlue &aGreen &eYellow


---

## Configuration

All regions are stored in `config.yml`. Each region includes its position data, title, subtitle, and optional actionbar message.

Example configuration:
```yml
regions:
  downtown:
    pos1:
      ==: org.bukkit.Location
      world: world
      x: 100.0
      y: 64.0
      z: 100.0
    pos2:
      ==: org.bukkit.Location
      world: world
      x: 200.0
      y: 90.0
      z: 200.0
    title: "&bDowntown"
    subtitle: "&7Welcome to the city!"
```


# How It Works

### The plugin checks player locations once per second
### When a player enters a new region, a title and subtitle are displayed
### Regions only trigger if the player is in the same world the region was created in
### Titles are not repeatedly shown while the player remains inside the same region

---

# Development Information
### Built using the Paper API
### Uses Gradle for builds
### Java toolchain set to Java 21
### Main plugin class: CountyTitles.countyTitles.CountyTitles

---

# Credits

### Created by Brennan Cheatwood (brengeware).
### This plugin was developed as a personal learning project and first public Minecraft plugin.


---

# License

### This project is open-source.
### You are free to use, modify, and learn from this project.
