package fr.nemesis07.survival.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.utils.ItemBuilder;

public class PetCompetence {
	
	public Inventory inv = Bukkit.createInventory(null, 9*4, ChatColor.LIGHT_PURPLE + "Compétences");
	public final ItemStack black_pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack();
	public final ItemStack back = new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "RETOUR").toItemStack();
	
	public Inventory getInv(Player p) {
		List<String> ChestLoreB = new ArrayList<String>();
		ChestLoreB.add(" ");
		ChestLoreB.add("§cPour débloqué cette compétence:");
		ChestLoreB.add(" ");
		ChestLoreB.add("§5Vous avez besoin de 5 400 xp");
		ChestLoreB.add("§5Soit jusqu'à environ 50 levels");
		ChestLoreB.add(" ");
		ChestLoreB.add("§c§lBLOQUÉ");
		
		List<String> ChestLoreD = new ArrayList<String>();
		ChestLoreD.add(" ");
		ChestLoreD.add("§cPour débloqué cette compétence:");
		ChestLoreD.add(" ");
		ChestLoreD.add("§5Vous avez besoin de 5 400 xp");
		ChestLoreD.add("§5Soit jusqu'à 50 levels");
		ChestLoreD.add(" ");
		ChestLoreD.add("§a§lDÉBLOQUÉ");
		
		
		
		List<String> rideLoreB = new ArrayList<String>();
		rideLoreB.add(" ");
		rideLoreB.add("§cPour débloqué cette compétence:");
		rideLoreB.add(" ");
		rideLoreB.add("§5Vous avez besoin de 31 000 xp");
		rideLoreB.add("§5Soit jusqu'à 100 levels");
		rideLoreB.add("§5Vous devez avoir une §cselle §5dans");
		rideLoreB.add("§5Votre inventaire");
		rideLoreB.add(" ");
		rideLoreB.add("§c§lBLOQUÉ");
		
		List<String> rideLoreD = new ArrayList<String>();
		rideLoreD.add(" ");
		rideLoreD.add("§cPour débloqué cette compétence:");
		rideLoreD.add(" ");
		rideLoreD.add("§5Vous avez besoin de 31 000 xp");
		rideLoreD.add("§5Soit jusqu'à 100 levels");
		rideLoreD.add("§5Vous devez avoir une §cselle §5dans");
		rideLoreD.add("§5Votre inventaire");
		rideLoreD.add(" ");
		rideLoreD.add("§a§lDÉBLOQUÉ");
		
		
		
		List<String> attackLoreB = new ArrayList<String>();
		attackLoreB.add(" ");
		attackLoreB.add("§cPour débloqué cette compétence:");
		attackLoreB.add(" ");
		attackLoreB.add("§5Vous avez besoin de 57 200 xp");
		attackLoreB.add("§5Soit jusqu'à 130 levels");
		attackLoreB.add("§5Vous devez avoir une §cépée en netherite");
		attackLoreB.add("§5Dans votre inventaire");
		attackLoreB.add(" ");
		attackLoreB.add("§c§lBLOQUÉ");
		
		List<String> attackLoreD = new ArrayList<String>();
		attackLoreD.add(" ");
		attackLoreD.add("§cPour débloqué cette compétence:");
		attackLoreD.add(" ");
		attackLoreD.add("§5Vous avez besoin de 57 200 xp");
		attackLoreD.add("§5Soit jusqu'à 130 levels");
		attackLoreD.add("§5Vous devez avoir une §cépée en netherite");
		attackLoreD.add("§5Dans votre inventaire");
		attackLoreD.add(" ");
		attackLoreD.add("§a§lDÉBLOQUÉ");
		
		final ItemStack chestB = new ItemBuilder(Material.CHEST).setName(ChatColor.YELLOW + "Compétence inventaire").setLore(ChestLoreB).toItemStack();
		final ItemStack saddleB = new ItemBuilder(Material.SADDLE).setName(ChatColor.GOLD + "Compétence de chevauché").setLore(rideLoreB).toItemStack();
		final ItemStack attackB = new ItemBuilder(Material.NETHERITE_SWORD).setName(ChatColor.RED + "Compétence d'attaque").setLore(attackLoreB).toItemStack();
		
		final ItemStack chestD = new ItemBuilder(Material.CHEST).setName(ChatColor.YELLOW + "Compétence inventaire").setLore(ChestLoreD).toItemStack();
		final ItemStack saddleD = new ItemBuilder(Material.SADDLE).setName(ChatColor.GOLD + "Compétence de chevauché").setLore(rideLoreD).toItemStack();
		final ItemStack attackD = new ItemBuilder(Material.NETHERITE_SWORD).setName(ChatColor.RED + "Compétence d'attaque").setLore(attackLoreD).toItemStack();
		
		for(int i = 0; i < 9*4; i++) {
			inv.setItem(i, black_pane);
		}
		//CHECK IF CPT INV = TRUE
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".competences.chest").equalsIgnoreCase("on")) {
			inv.setItem(10, chestD);
		} else {
			inv.setItem(10, chestB);
		}
		//CHECK IF CPT RIDE = TRUE
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".competences.ride").equalsIgnoreCase("on")) {
			inv.setItem(13, saddleD);
		} else {
			inv.setItem(13, saddleB);
		}
		//CHECK IF CPT ATTACK = TRUE
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".competences.attack").equalsIgnoreCase("on")) {
			inv.setItem(16, attackD);
		} else {
			inv.setItem(16, attackB);
		}
		
		inv.setItem(31, back);
		return inv;
	}

}
