package fr.nemesis07.survival.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nemesis07.survival.utils.ItemBuilder;

public class PetEmerald {
	
	public Inventory inv = Bukkit.createInventory(null, 9*6, ChatColor.DARK_GREEN + "Choisir pet");
	
	public final ItemStack black_pane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName(" ").toItemStack();
	public final ItemStack back = new ItemBuilder(Material.BARRIER).setName(ChatColor.RED + "RETOUR").toItemStack();
	public final ItemStack next = new ItemBuilder(Material.ARROW).setName(ChatColor.AQUA + "Page suivante").toItemStack();
	public final ItemStack callPet = new ItemBuilder(Material.EMERALD).setName(ChatColor.GREEN + "Invoqué votre animal").toItemStack();
	
	public final ItemStack Skeleton_horse = new ItemBuilder(Material.SKELETON_HORSE_SPAWN_EGG).setName(ChatColor.YELLOW + "CHEVAL SQUELETTE").toItemStack();
	public final ItemStack Zombie_horse = new ItemBuilder(Material.ZOMBIE_HORSE_SPAWN_EGG).setName(ChatColor.YELLOW + "CHEVAL ZOMBIE").toItemStack();
	public final ItemStack Guardian = new ItemBuilder(Material.GUARDIAN_SPAWN_EGG).setName(ChatColor.YELLOW + "GUARDIAN").toItemStack();
	public final ItemStack Elder_guardian = new ItemBuilder(Material.ELDER_GUARDIAN_SPAWN_EGG).setName(ChatColor.YELLOW + "ELDER GUARDIAN").toItemStack();
	public final ItemStack Panda = new ItemBuilder(Material.PANDA_SPAWN_EGG).setName(ChatColor.YELLOW + "PANDA").toItemStack();
	public final ItemStack Fox = new ItemBuilder(Material.FOX_SPAWN_EGG).setName(ChatColor.YELLOW + "RENARD").toItemStack();
	public final ItemStack Bee = new ItemBuilder(Material.BEE_SPAWN_EGG).setName(ChatColor.YELLOW + "ABEILLE").toItemStack();
	public final ItemStack Blaze = new ItemBuilder(Material.BLAZE_SPAWN_EGG).setName(ChatColor.YELLOW + "BLAZE").toItemStack();
	public final ItemStack Cat = new ItemBuilder(Material.CAT_SPAWN_EGG).setName(ChatColor.YELLOW + "CHAT").toItemStack();
	public final ItemStack Chicken = new ItemBuilder(Material.CHICKEN_SPAWN_EGG).setName(ChatColor.YELLOW + "POULE").toItemStack();
	public final ItemStack Cave_spider = new ItemBuilder(Material.CAVE_SPIDER_SPAWN_EGG).setName(ChatColor.YELLOW + "ARAIGNÉE VENIMEUSE").toItemStack();
	public final ItemStack Cow = new ItemBuilder(Material.COW_SPAWN_EGG).setName(ChatColor.YELLOW + "VACHE").toItemStack();
	public final ItemStack Creeper = new ItemBuilder(Material.CREEPER_SPAWN_EGG).setName(ChatColor.YELLOW + "CREEPER").toItemStack();
	public final ItemStack Dolphin = new ItemBuilder(Material.DOLPHIN_SPAWN_EGG).setName(ChatColor.YELLOW + "DAUPHIN").toItemStack();
	public final ItemStack Donkey = new ItemBuilder(Material.DONKEY_SPAWN_EGG).setName(ChatColor.YELLOW + "ANE").toItemStack();
	public final ItemStack Enderman = new ItemBuilder(Material.ENDERMAN_SPAWN_EGG).setName(ChatColor.YELLOW + "ENDERMAN").toItemStack();
	public final ItemStack Endermite = new ItemBuilder(Material.ENDERMITE_SPAWN_EGG).setName(ChatColor.YELLOW + "ENDERMITE").toItemStack();
	public final ItemStack Hoglin = new ItemBuilder(Material.HOGLIN_SPAWN_EGG).setName(ChatColor.YELLOW + "HOGLIN").toItemStack();
	public final ItemStack Horse = new ItemBuilder(Material.HORSE_SPAWN_EGG).setName(ChatColor.YELLOW + "CHEVAL").toItemStack();
	public final ItemStack Iron_golem= new ItemBuilder(Material.IRON_BLOCK).setName(ChatColor.YELLOW + "IRON GOLEM").toItemStack();
	public final ItemStack Mooshroom = new ItemBuilder(Material.MOOSHROOM_SPAWN_EGG).setName(ChatColor.YELLOW + "CHAMPIMEUH").toItemStack();
	public final ItemStack Mule = new ItemBuilder(Material.MULE_SPAWN_EGG).setName(ChatColor.YELLOW + "MULE").toItemStack();
	public final ItemStack Ocelot = new ItemBuilder(Material.OCELOT_SPAWN_EGG).setName(ChatColor.YELLOW + "OCELOT").toItemStack();
	public final ItemStack LLama = new ItemBuilder(Material.LLAMA_SPAWN_EGG).setName(ChatColor.YELLOW + "LAMA").toItemStack();
	public final ItemStack Husk = new ItemBuilder(Material.HUSK_SPAWN_EGG).setName(ChatColor.YELLOW + "ZOMBIE MOMIFIÉ").toItemStack();
	public final ItemStack Drowned = new ItemBuilder(Material.DROWNED_SPAWN_EGG).setName(ChatColor.YELLOW + "ZOMBIE NOYÉ").toItemStack();
	public final ItemStack Evoker = new ItemBuilder(Material.EVOKER_SPAWN_EGG).setName(ChatColor.YELLOW + "ÉVOCATEUR").toItemStack();
	public final ItemStack Illusioner = new ItemBuilder(Material.GLOWSTONE_DUST).setName(ChatColor.YELLOW + "ILLUSIONER").toItemStack();
	public final ItemStack Pillager = new ItemBuilder(Material.PILLAGER_SPAWN_EGG).setName(ChatColor.YELLOW + "PILLARD").toItemStack();
	public final ItemStack Pig = new ItemBuilder(Material.PIG_SPAWN_EGG).setName(ChatColor.YELLOW + "PIG").toItemStack();
	public final ItemStack Piglin = new ItemBuilder(Material.PIGLIN_SPAWN_EGG).setName(ChatColor.YELLOW + "PIGLIN").toItemStack();
	public final ItemStack Polar_bear = new ItemBuilder(Material.POLAR_BEAR_SPAWN_EGG).setName(ChatColor.YELLOW + "OURS BLANC").toItemStack();
	public final ItemStack Pufferfish = new ItemBuilder(Material.PUFFERFISH_SPAWN_EGG).setName(ChatColor.YELLOW + "POISSON GLOBE").toItemStack();
	public final ItemStack Rabbit = new ItemBuilder(Material.RABBIT_SPAWN_EGG).setName(ChatColor.YELLOW + "LAPIN").toItemStack();
	public final ItemStack Tropical = new ItemBuilder(Material.TROPICAL_FISH_SPAWN_EGG).setName(ChatColor.YELLOW + "POISSON TROPICAL").toItemStack();
	public final ItemStack Salmon = new ItemBuilder(Material.SALMON_SPAWN_EGG).setName(ChatColor.YELLOW + "SAUMON").toItemStack();
	public final ItemStack Sheep = new ItemBuilder(Material.SHEEP_SPAWN_EGG).setName(ChatColor.YELLOW + "MOUTON").toItemStack();
	public final ItemStack Silverfish = new ItemBuilder(Material.SILVERFISH_SPAWN_EGG).setName(ChatColor.YELLOW + "SILVERFISH").toItemStack();
	public final ItemStack Skeleton = new ItemBuilder(Material.SKELETON_SPAWN_EGG).setName(ChatColor.YELLOW + "SQUELETTE").toItemStack();
	public final ItemStack Spider = new ItemBuilder(Material.SPIDER_SPAWN_EGG).setName(ChatColor.YELLOW + "ARAIGNÉE").toItemStack();
	public final ItemStack Snowman = new ItemBuilder(Material.SNOW_BLOCK).setName(ChatColor.YELLOW + "BONHOMME DE NEIGE").toItemStack();
	public final ItemStack Squid = new ItemBuilder(Material.SQUID_SPAWN_EGG).setName(ChatColor.YELLOW + "POULPE").toItemStack();
	public final ItemStack Stray = new ItemBuilder(Material.STRAY_SPAWN_EGG).setName(ChatColor.YELLOW + "VAGABOND").toItemStack();
	public final ItemStack Strider = new ItemBuilder(Material.STRIDER_SPAWN_EGG).setName(ChatColor.YELLOW + "ARPENTEUR").toItemStack();
	public final ItemStack Trader_LLama = new ItemBuilder(Material.TRADER_LLAMA_SPAWN_EGG).setName(ChatColor.YELLOW + "LAMA DE MARCHAND").toItemStack();
	
	public Inventory getInv() {
		for(int i = 45; i < 53; i++) {
			inv.setItem(i, black_pane);
		}
		inv.setItem(0, Skeleton_horse);
		inv.setItem(1, Zombie_horse);
		inv.setItem(2, Guardian);
		inv.setItem(3, Elder_guardian);
		inv.setItem(4, Panda);
		inv.setItem(5, Fox);
		inv.setItem(6, Bee);
		inv.setItem(7, Blaze);
		inv.setItem(8, Cat);
		inv.setItem(9, Chicken);
		inv.setItem(10, Cave_spider);
		inv.setItem(11, Cow);
		inv.setItem(12, Creeper);
		inv.setItem(13, Dolphin);
		inv.setItem(14, Donkey);
		inv.setItem(15, Enderman);
		inv.setItem(16, Endermite);
		inv.setItem(17, Hoglin);
		inv.setItem(18, Horse);
		inv.setItem(19, Iron_golem);
		inv.setItem(20, Mooshroom);
		inv.setItem(21, Mule);
		inv.setItem(22, Ocelot);
		inv.setItem(23, LLama);
		inv.setItem(24, Husk);
		inv.setItem(25, Drowned);
		inv.setItem(26, Evoker);
		inv.setItem(27, Illusioner);
		inv.setItem(28, Pillager);
		inv.setItem(29, Pig);
		inv.setItem(30, Piglin);
		inv.setItem(31, Polar_bear);
		inv.setItem(32, Pufferfish);
		inv.setItem(33, Rabbit);
		inv.setItem(34, Tropical);
		inv.setItem(35, Salmon);
		inv.setItem(36, Sheep);
		inv.setItem(37, Silverfish);
		inv.setItem(38, Skeleton);
		inv.setItem(39, Spider);
		inv.setItem(40, Snowman);
		inv.setItem(41, Squid);
		inv.setItem(42, Stray);
		inv.setItem(43, Strider);
		inv.setItem(44, Trader_LLama);
		
		inv.setItem(45, back);
		inv.setItem(49, callPet);
		inv.setItem(53, next);
		return inv;
	}
	
}
