package fr.nemesis07.survival.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nemesis07.survival.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class TpaCmd implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(label.equalsIgnoreCase("tpa")) {
				if(args.length == 0) {
					p.sendMessage(Main.getInstance().prefixTpa + "§cVous devez indiqué un joueur");
					p.sendMessage(Main.getInstance().prefixTpa + "§cUsage: /tpa <pseudo>");
				} else if(args.length == 1) {
					final Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						if(target == p) {
							p.sendMessage(Main.getInstance().prefixTpa + "§cVous ne pouvez pas vous auto-invité !");
							return false;
						} else {
							if(Main.getInstance().inviteLess1Hour.contains(p)) {
								p.sendMessage(Main.getInstance().prefixTpa + "§cVous vous êtes téléporté il y a moins d'une heure !");
							} else {
								// FOR TARGET
								TextComponent prefix = new TextComponent(Main.getInstance().prefixTpa + "§eVous avez reçu une demande de téléportation de §6"+ p.getDisplayName());
								TextComponent accept = new TextComponent(" §8[§aOui§8]");
								accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClique pour accepter sont invitation").create()));
								accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + p.getDisplayName()));
								TextComponent decline = new TextComponent("§8[§cNon§8]");
								decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cClique pour refuser sont invitation").create()));
								decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + p.getDisplayName()));
								TextComponent or = new TextComponent(" §e/ ");
								target.spigot().sendMessage(prefix, accept, or, decline);
								//FOR PLAYER
								p.sendMessage(Main.getInstance().prefixTpa + "§eDemande de téléportation envoyé à " + target.getDisplayName());
							
								Main.getInstance().hasInvite.put(p, target);
							}
						}
					} else {
						p.sendMessage(Main.getInstance().prefixTpa + "§cCe joueur n'existe pas ou n'est pas connecté !");
					}
				} else {
					p.sendMessage(Main.getInstance().prefixTpa + "§cTrop d'arguments");
					p.sendMessage(Main.getInstance().prefixTpa + "§cUsage: /tpa <pseudo>");
				}
			}
		} else {
			sender.sendMessage("Vous ne pouvez pas executé cette commande.");
		}
		return false;
	}

}
