package org.redcraft.redcraftmongostats;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.redcraft.redcraftmongostats.listeners.PlayerQuitListener;
import org.redcraft.redcraftmongostats.runnables.commands.SyncStatsCommand;
import org.redcraft.redcraftmongostats.runnables.tasks.SyncStatsTask;

public class RedCraftMongoStats extends JavaPlugin {

  private static RedCraftMongoStats instance;

  public static RedCraftMongoStats getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;

    // Setup
    Config.readConfig(this);

    // Schedulers
    BukkitScheduler scheduler = getServer().getScheduler();
    scheduler.runTaskTimerAsynchronously(this, new SyncStatsTask(), 0, Config.syncTicksInterval);

    // Game listeners
    PluginManager pluginManager = this.getServer().getPluginManager();
    pluginManager.registerEvents(new PlayerQuitListener(), this);

    // Commands
    this.getCommand("syncstats").setExecutor(new SyncStatsCommand());
  }

  @Override
  public void onDisable() {
    getServer().getScheduler().cancelTasks(this);
  }
}
