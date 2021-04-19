package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.events.PlayerListeners;
import fr.nemesis07.survival.mobs.CustomPet;
import net.md_5.bungee.api.ChatColor;

public class BcrlCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("bcrl")) {
			if(sender.hasPermission("bcrl")) {
				Bukkit.broadcastMessage(ChatColor.RED + "ATTENTION FREEZE");
				for(CustomPet pet: Main.getInstance().map.values()) {
					PlayerListeners pl = new PlayerListeners();
					pl.kill(pet);
				}
				Main.getInstance().getPlayers().save();
				Main.getInstance().map.clear();
				Bukkit.getPluginManager().disablePlugin(Bukkit.getPluginManager().getPlugin("Survival"));
				Bukkit.getPluginManager().enablePlugin(Bukkit.getPluginManager().getPlugin("Survival"));
				Bukkit.broadcastMessage(ChatColor.DARK_GREEN + "FREEZE FINI");
			} else {
				sender.sendMessage("Vous n'avez pas la permission");
			}
		}
		return false;
	}

}
