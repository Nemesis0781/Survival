package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.backpack.Backpack;

public class BackpackCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) 
			return true; 
		Player player = (Player)sender;
		if(args.length == 0) {
			Backpack backpack = Main.getInstance().getBackpackManager().getBackpack(player.getUniqueId());
			player.openInventory(backpack.getInventory());
		} else if(args.length == 1) {
			Player target = Bukkit.getPlayer(args[0]);
			if(target != null) {
				if(target == player) {
					Backpack backpack = Main.getInstance().getBackpackManager().getBackpack(player.getUniqueId());
					player.openInventory(backpack.getInventory());
				} else {
					if(player.hasPermission("backpack.admin")) {
						Backpack bp = Main.getInstance().getBackpackManager().getBackpack(target.getUniqueId());
						player.openInventory(bp.getInventory());
					}
				}
			}
		}
		return true;
	}

}
