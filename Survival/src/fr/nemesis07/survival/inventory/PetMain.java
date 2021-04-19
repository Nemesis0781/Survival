package fr.nemesis07.survival.inventory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.mobs.CustomPet;
import fr.nemesis07.survival.utils.ItemBuilder;

public class PetMain {
	
	final File playerFile = new File(Main.getInstance().getDataFolder(), "players.yml");
	final YamlConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
	
	Map<Player, CustomPet> map = Main.getInstance().map;
	public Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.DARK_GREEN + "Pet");
	public final ItemStack black_pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack();
	public final ItemStack callPet = new ItemBuilder(Material.EMERALD).setName(ChatColor.GREEN + "Choisir votre pet").toItemStack();
	public final ItemStack removePet = new ItemBuilder(Material.REDSTONE).setName(ChatColor.RED + "Renvoyé votre animal").toItemStack();
	public final ItemStack chest = new ItemBuilder(Material.CHEST).setName(ChatColor.YELLOW + "Inventaire de votre pet").toItemStack();
	public final ItemStack cpt = new ItemBuilder(Material.GOLDEN_CARROT).setName(ChatColor.LIGHT_PURPLE + "Compétences").toItemStack();
	public final ItemStack saddle = new ItemBuilder(Material.SADDLE).setName(ChatColor.RED + "Chevauché votre animal").toItemStack();
	public final ItemStack name = new ItemBuilder(Material.NAME_TAG).setName(ChatColor.AQUA + "Changé le nom de votre animal").toItemStack();
	//public final ItemStack attackOff = new ItemBuilder(Material.NETHERITE_SWORD).setName(ChatColor.DARK_RED + "Attaque").toItemStack();
	//public final ItemStack attackOn = new ItemBuilder(Material.NETHERITE_SWORD).setName(ChatColor.DARK_RED + "Attaque").addEnchant(Enchantment.DAMAGE_ALL, 0, false).toItemStack();
	
	public Inventory getInv(Player p) {
		for(int i = 0; i < 45; i++) {
			inv.setItem(i, black_pane);
		}
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.isOut").equalsIgnoreCase("on")) {
			inv.setItem(11, removePet);
		} else {
			inv.setItem(11, callPet);
		}
		try {
			playerConfig.save(playerFile);
        } catch (IOException er) {
			er.printStackTrace();
		}
		
		inv.setItem(13, chest);
		inv.setItem(15, cpt);
		inv.setItem(30, saddle);
		inv.setItem(32, name);
		return inv;
	}

}
