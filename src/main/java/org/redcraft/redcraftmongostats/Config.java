package org.redcraft.redcraftmongostats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

  public static long syncTicksInterval = 600;
  public static boolean debugSync = false;

  public static String mongoUri = "mongodb://localhost/?retryWrites=true";
  public static String mongoDatabase = "test";
  public static String mongoCollection = "test";

  public static void readConfig(JavaPlugin plugin) {
    plugin.saveDefaultConfig();

    FileConfiguration config = plugin.getConfig();

    syncTicksInterval = config.getLong("sync-ticks-interval");
    debugSync = config.getBoolean("debug-sync");

    mongoCollection = config.getString("mongo-uri");
    mongoDatabase = config.getString("mongo-database");
    mongoCollection = config.getString("mongo-collection");
  }
}
