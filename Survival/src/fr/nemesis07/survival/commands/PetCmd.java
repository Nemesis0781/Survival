package fr.nemesis07.survival.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.inventory.PetMain;

public class PetCmd implements CommandExecutor {

	private PetMain petMain = new PetMain();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player))
			sender.sendMessage("Commande interdite pour la console");
		if(label.equalsIgnoreCase("pet")) {
			Player p = (Player) sender;
			initPlayerPetConfig(p);
			if(args.length == 0) {
				Inventory petMainInv = petMain.getInv(p);
				p.playSound(p.getLocation(), Sound.BLOCK_CHEST_OPEN, 5, 10);
				p.openInventory(petMainInv);
			} else if(args.length == 1) {
				if(args[0].equalsIgnoreCase("wither")) {
					if(p.hasPermission("admin.wither")) {
						
					}
				}
			}
		}
		
		return false;
	}
	
	private final void initPlayerPetConfig(Player p) {
		String basicName = "&2Animal de "+p.getDisplayName();
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()) == null) {
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.chest", "off");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.ride", "off");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.attack", "off");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SKELETON_HORSE");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isBaby", "off");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "off");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.inventory", "");
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.name", basicName);
		}
		Main.getInstance().getPlayers().save();
	}

}
