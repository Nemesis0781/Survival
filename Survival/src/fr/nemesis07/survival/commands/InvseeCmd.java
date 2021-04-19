package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class InvseeCmd implements CommandExecutor, Listener {

	private Player target;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			return false;
		if(label.equalsIgnoreCase("invsee")) {
			Player p = (Player) sender;
			if(p.hasPermission("invsee.admin")) {
				if(args.length != 1) {
					p.sendMessage("§c[§6Invsee§c] Usage: §6/invsee §7<pseudo>");
					return false;
				}
				target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					p.sendMessage("§c[§6Invsee§c] Le joueur n'existe pas ou n'est pas connecté !");
					return false;
				}
				p.openInventory(target.getInventory());
			}
		}
		return false;
	}
	

}
