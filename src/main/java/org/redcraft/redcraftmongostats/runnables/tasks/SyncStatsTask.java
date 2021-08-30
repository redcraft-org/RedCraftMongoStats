package org.redcraft.redcraftmongostats.runnables.tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.redcraft.redcraftmongostats.Config;
import org.redcraft.redcraftmongostats.RedCraftMongoStats;
import org.redcraft.redcraftmongostats.stats.StatsSyncManager;

import net.md_5.bungee.api.ChatColor;

public class SyncStatsTask implements Runnable {

    StatsSyncManager statsSyncManager = StatsSyncManager.getInstance();
    List<Player> playersToSync = new ArrayList<Player>();
    boolean targetOnlinePlayers = false;
    CommandSender sender = null;

    public SyncStatsTask() {
        targetOnlinePlayers = true;
    }

    public SyncStatsTask(List<Player> players) {
        playersToSync.addAll(players);
    }

    public SyncStatsTask(Player player) {
        playersToSync.add(player);
    }

    public void setCommandOutput(CommandSender sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        if (targetOnlinePlayers) {
            playersToSync.clear();
            playersToSync.addAll(Bukkit.getServer().getOnlinePlayers().stream().toList());
        }

        boolean errors = false;

        for (Player player : playersToSync) {
            try {
                statsSyncManager.syncPlayerStats(player);

                if (Config.debugSync) {
                    RedCraftMongoStats.getInstance().getLogger().info("Synchronized " + player.getName() + " stats to Mongo");
                }
            } catch (Exception ex) {
                errors = true;

                String message = "Error while synchronizing " + player.getName() + " stats to Mongo";
                RedCraftMongoStats.getInstance().getLogger().severe(message);
                ex.printStackTrace();

                if (sender != null) {
                    sender.sendMessage(ChatColor.RED + message);
                }
            }
        }

        if (sender != null && !errors) {
            sender.sendMessage(ChatColor.GREEN + "Player stats have been synchronized with Mongo");
        }
    }

}
