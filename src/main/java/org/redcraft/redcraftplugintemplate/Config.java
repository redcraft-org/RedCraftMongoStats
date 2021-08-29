package org.redcraft.redcraftplugintemplate;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

  public static String databaseUri = "jdbc:sqlite:%plugin_config_path%/plugins/RedCraftPluginTemplate/database.db";
  public static String databaseUsername = "";
  public static String databasePassword = "";

  public static boolean redisEnabled = false;
  public static String redisUri = "";
  public static String redisKeyPrefix = "rct"; // Change this to the prefix that will be used by the plugin

  public static void readConfig(JavaPlugin plugin) {
    plugin.saveDefaultConfig();

    FileConfiguration config = plugin.getConfig();

    databaseUri = config.getString("database-uri");
    databaseUsername = config.getString("database-username");
    databasePassword = config.getString("database-password");

    redisEnabled = config.getBoolean("redis-enabled");
    redisUri = config.getString("redis-uri");
    redisKeyPrefix = config.getString("redis-key-prefix");
  }
}
