package fr.nemesis07.survival.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import fr.nemesis07.survival.Main;
import fr.nemesis07.survival.inventory.PetCompetence;
import fr.nemesis07.survival.inventory.PetEmerald;
import fr.nemesis07.survival.inventory.PetEmerald2;
import fr.nemesis07.survival.inventory.PetMain;
import fr.nemesis07.survival.mobs.CustomPet;
import net.minecraft.server.v1_16_R3.ChatComponentText;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityLiving;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.WorldServer;

public class PlayerListeners implements Listener {
	
	Map<Player, CustomPet> map = Main.getInstance().map;
	final PetMain petMain = new PetMain();
	List<Player> newName = new ArrayList<Player>();

	Map<String, EntityTypes<? extends EntityCreature>> type = Main.getInstance().type;
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "off");
		if(map.containsKey(p)) {
			CustomPet pet = map.get(p);
			kill(pet);
			map.remove(p, pet);
		}
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if(e.getCurrentItem() == null) return;
		ItemStack current = e.getCurrentItem();
		
		if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "Pet")) {
			e.setCancelled(true);
			switch (current.getType()) {
				case EMERALD:
					p.closeInventory();
					PetEmerald choose = new PetEmerald();
					p.openInventory(choose.getInv());
					break;
				
				case REDSTONE:
					CustomPet petToKill = map.get(p);
					kill(petToKill);
					p.sendMessage(ChatColor.DARK_GREEN + "§2[§aPet§2] Votre pet est retourné à l'écurie !");
					map.remove(p, petToKill);
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "off");
					p.closeInventory();
					break;
					
				case CHEST:
					break;
					
				case GOLDEN_CARROT:
					p.closeInventory();
					PetCompetence cpt = new PetCompetence();
					p.openInventory(cpt.getInv(p));
					break;
					
				case SADDLE:
					if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".competences.ride").equalsIgnoreCase("on")) {
						if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.isOut").equalsIgnoreCase("on")) {
							CustomPet pet = Main.getInstance().map.get(p);
							//p.setInvulnerable(true);
							pet.getBukkitEntity().addPassenger(p);
							p.closeInventory();
						} else {
							p.sendMessage("§2[§aPet§2] "+ChatColor.RED + "Vous n'avez pas de pet de sorti !");
						}
					} else {
						p.sendMessage("§2[§aPet§2] "+ChatColor.RED + "Vous n'avez pas competence de chevauché !");
					}
					p.closeInventory();
					break;
					
				case NAME_TAG:
					if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.isOut").equalsIgnoreCase("on")) {
						p.sendMessage("§2[§aPet§2] " +ChatColor.GREEN + "Vous devez écrire son nouveau nom dans le chat");
						p.sendMessage("§2[§aPet§2] " +ChatColor.GREEN + "Vous pouvez ajouté de la couleur avec le symbole '"+ChatColor.DARK_GREEN+"&"+ChatColor.GREEN+"'");
						p.closeInventory();
						newName.add(p);
					} else {
						p.closeInventory();
						p.sendMessage("§2[§aPet§2] " +ChatColor.RED + "Vous devez d'abord appelé votre animal !");
					}
					break;
				
				default:
					break;
			}

			
		} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "Compétences")) {
			e.setCancelled(true);
			switch (current.getType()) {
				case CHEST:
					int expChest = 5400;
					if(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() + ".competences.chest").equalsIgnoreCase("off")) {
						if(p.getTotalExperience() >= expChest) {
							p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
							p.sendMessage("§2[§aPet§2] Vous avez débloqué la compétence §aInventaire");
							Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.chest", "on");
							int TotalExp = p.getTotalExperience() - expChest;
							p.setTotalExperience(0);
							p.setLevel(0);
							p.setExp(0);
							p.giveExp(TotalExp);
							p.closeInventory();
							Main.getInstance().getPlayers().save();
						} else {
							p.sendMessage("§2[§aPet§2] §cVous n'avez pas assez d'expérience");
							p.closeInventory();
						}
					} else {
						p.sendMessage("§2[§aPet§2] Vous avez déjà la compétence §aInventaire");
						p.closeInventory();
					}
					break;
					
				case SADDLE:
					int expSaddle = 31000;
					if(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() + ".competences.ride").equalsIgnoreCase("off")) {
						if(p.getTotalExperience() >= expSaddle) {
							if(p.getInventory().contains(Material.SADDLE)) {
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
								p.sendMessage("§2[§aPet§2] Vous avez débloqué la compétence §aChevaucheur");
								Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.ride", "on");
								int TotalExp = p.getTotalExperience() - expSaddle;
								p.setTotalExperience(0);
								p.setLevel(0);
								p.setExp(0);
								p.giveExp(TotalExp);
								p.closeInventory();
								Main.getInstance().getPlayers().save();
							} else {
								p.sendMessage("§2[§aPet§2] §cVous n'avez pas assez de selle dans votre inventaire");
								p.closeInventory();
							}
						} else {
							p.sendMessage("§2[§aPet§2] §cVous n'avez pas assez d'expérience");
							p.closeInventory();
						}
					} else {
						p.sendMessage("§2[§aPet§2] Vous avez déjà la compétence §aChevaucheur");
						p.closeInventory();
					}
					break;
				case NETHERITE_SWORD:
					int expSword = 57200;
					if(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() + ".competences.attack").equalsIgnoreCase("off")) {
						if(p.getTotalExperience() >= expSword) {
							if(p.getInventory().contains(Material.NETHERITE_SWORD)) {
								p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
								p.sendMessage("§2[§aPet§2] Vous avez débloqué la compétence §aAttaquant");
								Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".competences.attack", "on");
								int TotalExp = p.getTotalExperience() - expSword;
								p.setTotalExperience(0);
								p.setLevel(0);
								p.setExp(0);
								p.giveExp(TotalExp);
								p.closeInventory();
								Main.getInstance().getPlayers().save();
							} else {
								p.sendMessage("§2[§aPet§2] §cVous n'avez pas assez d'épée en Netherite dans votre inventaire");
								p.closeInventory();
							}
						} else {
							p.sendMessage("§2[§aPet§2] §cVous n'avez pas assez d'expérience");
							p.closeInventory();
						}
					} else {
						p.sendMessage("§2[§aPet§2] Vous avez déjà la compétence §aAttaquant");
						p.closeInventory();
					}
					break;
				case BARRIER:
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 5, 10);
					p.openInventory(petMain.getInv(p));
					break;
					
				default:
					break;
			}
			
		} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "Choisir pet")) {
			e.setCancelled(true);
			switch (e.getSlot()) {
				case 0:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SKELETON_HORSE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 1:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ZOMBIE_HORSE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 2:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "GUARDIAN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 3:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ELDER_GUARDIAN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 4:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "PANDA");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 5:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "FOX");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 6:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "BEE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 7:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "BLAZE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 8:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "CAT");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 9:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "CHICKEN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 10:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "CAVE_SPIDER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 11:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "COW");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 12:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "CREEPER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 13:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "DOLPHIN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 14:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "DONKEY");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 15:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ENDERMAN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 16:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ENDERMITE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 17:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "HOGLIN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 18:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "HORSE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 19:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "IRON_GOLEM");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 20:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "MOOSHROOM");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 21:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "MULE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 22:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "OCELOT");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 23:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "LLAMA");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 24:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "HUSK");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 25:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "DROWNED");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 26:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "EVOKER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 27:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ILLUSIONER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;

				case 28:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "PILLAGER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 29:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "PIG");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 30:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "PIGLIN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 31:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "POLAR_BEAR");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 32:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "PUFFERFISH");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 33:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "RABBIT");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 34:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "RAVAGEUR");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 35:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SALMON");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 36:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SHEEP");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 37:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SILVERFISH");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 38:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SKELETON");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 39:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SPIDER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 40:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SNOW_GOLEM");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 41:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "SQUID");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 42:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "STRAY");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 43:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "STRIDER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 44:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "TRADER_LLAMA");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 45:
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 5, 10);
					p.openInventory(petMain.getInv(p));
					break;
				
				case 49:
					String typeName = Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.type");
					EntityTypes<? extends EntityCreature> entity = type.get(typeName);
					CustomPet pet = new CustomPet(p.getLocation(), p, (EntityTypes<EntityCreature>) entity);
					pet.setCustomName(new ChatComponentText(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() +".pet.name").replace('&', '§')));
					pet.setBaby(true);
					WorldServer world = ((CraftWorld) p.getWorld()).getHandle();
					world.addEntity(pet);
					map.put(p, pet);
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "on");
					p.sendMessage(ChatColor.DARK_GREEN + "§2[§aPet§2] Vous avez invoqué votre pet !");
					p.closeInventory();
					break;
					
				case 53:
					p.closeInventory();
					PetEmerald2 page2 = new PetEmerald2();
					p.openInventory(page2.getInv());
					break;
			}
			
		} else if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "PAGE 2")) {
			e.setCancelled(true);
			switch (e.getSlot()) {
				case 0:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "TURTLE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 1:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "VEX");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 2:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "VINDICATOR");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 3:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "WITCH");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 4:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "WOLF");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 5:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "WITHER_SKELETON");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 6:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ZOMBIE");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 7:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ZOGLIN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 8:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "ZOMBIFIED_PIGLIN");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 9:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "VILLAGER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
				
				case 10:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "WITHER");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;

				case 11:
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.type", "GIANT");
					Main.getInstance().getPlayers().save();
					p.sendMessage("§2[§aPet§2] Pet sélectionné");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 20);
					break;
					
				case 45:
					p.closeInventory();
					PetEmerald page1 = new PetEmerald();
					p.openInventory(page1.getInv());
					break;
					
				case 53:
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.BLOCK_CHEST_CLOSE, 5, 10);
					p.openInventory(petMain.getInv(p));
					break;
				
				case 49:
					String typeName = Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.type");
					EntityTypes<? extends EntityCreature> entity = type.get(typeName);
					CustomPet pet = new CustomPet(p.getLocation(), p, (EntityTypes<EntityCreature>) entity);
					pet.setCustomName(new ChatComponentText(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() +".pet.name").replace('&', '§')));
					if(pet.getEntityType() instanceof Ageable) {
						pet.setBaby(true);
					}
					WorldServer world = ((CraftWorld) p.getWorld()).getHandle();
					world.addEntity(pet);
					map.put(p, pet);
					Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "on");
					p.sendMessage(ChatColor.DARK_GREEN + "§2[§aPet§2] Vous avez invoqué votre pet !");
					p.closeInventory();
					break;
				default:
					break;
			}
		}
	}
	
//	@EventHandler
//	public void onOpenInv(InventoryOpenEvent e) {
//		Player p = (Player) e.getPlayer();
//		final String type = Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.type");
//		if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "Choisir pet") || e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN + "PAGE 2")) {
//			switch (type) {
//				case "WITHER":
//					
//					break;
//				default:
//					break;
//			
//			}
//		}
//	}
	
	
	
	public void kill(CustomPet pet) {
		pet.getBukkitEntity().setInvulnerable(false);
		pet.setHealth(0.0F);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String message = e.getMessage();
		if(newName.contains(p)) {
			e.setCancelled(true);
			e.setMessage(null);
			Main.getInstance().getPlayersConfig().set("players."+p.getDisplayName()+".pet.name", message.toString());
			Main.getInstance().getPlayers().save();
			p.sendMessage("§2[§aPet§2] Modification enregistré");
			CustomPet pet = map.get(p);
			pet.setCustomName(new ChatComponentText(Main.getInstance().getPlayersConfig().getString("players." + p.getDisplayName() +".pet.name").replace('&', '§')));
			map.replace(p, pet);
			newName.remove(p);
		}
	}
	
	@EventHandler
	public void onInteractEntity(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".pet.isOut").equalsIgnoreCase("on")) {
			if(e.getRightClicked() instanceof CustomPet) {
				p.openInventory(petMain.getInv(p));
			}
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e){
		String name = e.getEntityType().getName();
		if(Main.getInstance().type.containsKey(name)) {
			EntityDamageEvent ede = e.getEntity().getLastDamageCause();
		    DamageCause dc = ede.getCause();
		    if(dc == DamageCause.VOID) {
		    	e.getDrops().clear();
		    }
		}
	}
	
	@EventHandler
	public void PlayerHurtEntity(EntityDamageByEntityEvent e) {
		if(e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			//if(Main.getInstance().getPlayersConfig().getString("players."+p.getDisplayName()+".competences.attack").equalsIgnoreCase("on")) {
				if(map.containsKey(p)) {
					CustomPet pet = map.get(p);
					for(int i = 0; i<Main.getInstance().attackable.size(); i++) {
						if(e.getEntity().getClass() == Main.getInstance().attackable.get(i) && e.getEntity() instanceof EntityLiving) {
							EntityLiving ent = (EntityLiving) e.getEntity();
							pet.setGoalTarget(ent, TargetReason.OWNER_ATTACKED_TARGET, true);
							pet.attackEntity(ent);
						}
					}
				}
			//}
		}
	}
	
}
