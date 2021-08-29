package org.redcraft.redcraftmongostats.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.redcraft.redcraftmongostats.RedCraftMongoStats;
import org.redcraft.redcraftmongostats.runnables.tasks.SyncStatsTask;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        SyncStatsTask task = new SyncStatsTask(event.getPlayer());

        RedCraftMongoStats plugin = RedCraftMongoStats.getInstance();
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, task);
    }
}
