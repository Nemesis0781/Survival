package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nemesis07.survival.Main;

public class TpacceptCmd implements CommandExecutor {
	
	 private final Main main;
     
	 public TpacceptCmd(Main main) {
		 this.main = main;
	 }
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player target = (Player) sender;
			if(label.equalsIgnoreCase("tpaccept") || label.equalsIgnoreCase("tpyes")) {
				if(args.length == 0) {
					target.sendMessage(Main.getInstance().prefixTpa + "§cVous devez indiqué un joueur");
					target.sendMessage(Main.getInstance().prefixTpa + "§cUsage: /tpyes <pseudo>");
				} else if(args.length == 1) {
					final Player p = Bukkit.getPlayer(args[0]);
					if(Main.getInstance().hasInvite.containsKey(p) && Main.getInstance().hasInvite.containsValue(target)) {
						if(p != target) {
							if(p != null) {

								Location tloc = target.getLocation();
								p.sendMessage(Main.getInstance().prefixTpa + "§7Téléportation dans 5 sec !");
								Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
									
									@Override
									public void run() {
										Main.getInstance().inviteLess1Hour.add(p);
										Main.getInstance().hasInvite.remove(p, target);
										p.teleport(tloc);
									}
								}, 100L);
								Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
									
									@Override
									public void run() {
										Main.getInstance().inviteLess1Hour.remove(p);
									}
								}, 72000L);
								
							} else {
								target.sendMessage(Main.getInstance().prefixTpa + "§cLe joueur n'existe pas ou n'est pas connecté !");
							}
						} else {
							target.sendMessage(Main.getInstance().prefixTpa + "§cVous ne pouvez pas vous tp à vous même");
						}
					}
				} else {
					target.sendMessage(Main.getInstance().prefixTpa + "§cTrop d'arguments");
					target.sendMessage(Main.getInstance().prefixTpa + "§cUsage: /tpyes <pseudo>");
				}
					
			}
		} else {
			sender.sendMessage("Vous ne pouvez pas executé cette commande.");
		}
		return false;
	}

}
