package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nemesis07.survival.Main;

public class TpdenyCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player target = (Player) sender;
			if(label.equalsIgnoreCase("tpdeny")) {
				if(args.length == 1) {
					final Player p = Bukkit.getPlayer(args[0]);
					if(p != null) {
						if(p != target) {
							p.sendMessage(Main.getInstance().prefixTpa + "§cDemande de téléportation refusé !");
							Main.getInstance().hasInvite.remove(p, target);
						} 
					} else {
						target.sendMessage(Main.getInstance().prefixTpa + "§cLe joueur n'existe pas ou n'est pas connecté !");
					}
					
				} 
			}
		} else {
			sender.sendMessage("Vous ne pouvez pas executé cette commande.");
		}
		return false;
	}

}
