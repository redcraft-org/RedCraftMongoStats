package org.redcraft.redcraftmongostats.runnables.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.redcraft.redcraftmongostats.RedCraftMongoStats;
import org.redcraft.redcraftmongostats.runnables.tasks.SyncStatsTask;

public class SyncStatsCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		SyncStatsTask task = new SyncStatsTask();
		task.setCommandOutput(sender);

		RedCraftMongoStats plugin = RedCraftMongoStats.getInstance();
		plugin.getServer().getScheduler().runTaskAsynchronously(plugin, task);

		return true;
	}
}
