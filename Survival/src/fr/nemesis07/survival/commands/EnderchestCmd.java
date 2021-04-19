package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnderchestCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		if(label.equalsIgnoreCase("enderchest") || label.equalsIgnoreCase("ec")) {
			Player p = (Player) sender;
			if(p.hasPermission("ec.admin")) {
				if(args.length != 1) {
					p.sendMessage("§5[§dEnderChest§5] §cUsage: §d/ec §7<pseudo>");
					return false;
				}
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage("§5[§dEnderChest§5] §cLe joueur n'existe pas ou n'est pas connecté !");
					return false;
				}
				p.openInventory(target.getEnderChest());
			}
		}
		return false;
	}

}
