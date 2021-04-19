package fr.nemesis07.survival.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nemesis07.survival.utils.ItemBuilder;

public class PetEmerald2 {
	
public Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.DARK_GREEN + "PAGE 2");
	
	public final ItemStack black_pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack();
	public final ItemStack back = new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "RETOUR").toItemStack();
	public final ItemStack previous = new ItemBuilder(Material.ARROW).setName(ChatColor.AQUA + "Page précedente").toItemStack();
	public final ItemStack callPet = new ItemBuilder(Material.EMERALD).setName(ChatColor.GREEN + "Invoqué votre animal").toItemStack();
	
	public final ItemStack Turtle = new ItemBuilder(Material.TURTLE_SPAWN_EGG).setName(ChatColor.YELLOW + "TORTUE").toItemStack();
	public final ItemStack Vex = new ItemBuilder(Material.VEX_SPAWN_EGG).setName(ChatColor.YELLOW + "VEX").toItemStack();
	public final ItemStack Vindicator = new ItemBuilder(Material.VINDICATOR_SPAWN_EGG).setName(ChatColor.YELLOW + "VINDICATEUR").toItemStack();
	public final ItemStack Witch = new ItemBuilder(Material.WITCH_SPAWN_EGG).setName(ChatColor.YELLOW + "SORCIÈRE").toItemStack();
	public final ItemStack Wolf = new ItemBuilder(Material.WOLF_SPAWN_EGG).setName(ChatColor.YELLOW + "LOUP").toItemStack();
	public final ItemStack WitherSquel = new ItemBuilder(Material.WITHER_SKELETON_SPAWN_EGG).setName(ChatColor.YELLOW + "WITHER SQUELETTE").toItemStack();
	public final ItemStack Zombie = new ItemBuilder(Material.ZOMBIE_SPAWN_EGG).setName(ChatColor.YELLOW + "ZOMBIE").toItemStack();
	public final ItemStack Zoglin = new ItemBuilder(Material.ZOGLIN_SPAWN_EGG).setName(ChatColor.YELLOW + "ZOGLIN").toItemStack();
	public final ItemStack Piglin = new ItemBuilder(Material.PIGLIN_SPAWN_EGG).setName(ChatColor.YELLOW + "PIGLIN ZOMBIFIÉ").toItemStack();
	public final ItemStack Villager = new ItemBuilder(Material.VILLAGER_SPAWN_EGG).setName(ChatColor.YELLOW + "VILLAGEOIS").toItemStack();
	public final ItemStack Wither = new ItemBuilder(Material.WITHER_SKELETON_SPAWN_EGG).setName(ChatColor.RED + "WITHER").toItemStack();
	public final ItemStack Giant = new ItemBuilder(Material.ZOMBIE_SPAWN_EGG).setName(ChatColor.RED + "GÉANT").toItemStack();

	
	public Inventory getInv() {
		for(int i = 45; i < 53; i++) {
			inv.setItem(i, black_pane);
		}
		inv.setItem(0, Turtle);
		inv.setItem(1, Vex);
		inv.setItem(2, Vindicator);
		inv.setItem(3, Witch);
		inv.setItem(4, Wolf);
		inv.setItem(5, WitherSquel);
		inv.setItem(6, Zombie);
		inv.setItem(7, Zoglin);
		inv.setItem(8, Piglin);
		inv.setItem(9, Villager);
		/*inv.setItem(10, Wither);
		inv.setItem(11, Giant);*/
		
		inv.setItem(45, previous);
		inv.setItem(49, callPet);
		inv.setItem(53, back);
		return inv;
	}

}
