package fr.nemesis07.survival;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftBat;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftBee;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftBlaze;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCat;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCaveSpider;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftChicken;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCow;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftCreeper;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftDolphin;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftDonkey;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftDrowned;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftElderGuardian;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEnderman;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEndermite;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEvoker;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftFox;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftGhast;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftGiant;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftGuardian;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftHoglin;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftHorse;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftHusk;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftIllusioner;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftIronGolem;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftLlama;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftMule;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftOcelot;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPanda;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPhantom;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPig;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPiglin;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPiglinBrute;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPillager;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPolarBear;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPufferFish;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftRabbit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSalmon;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSheep;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftShulker;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSilverfish;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSkeleton;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSkeletonHorse;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSnowman;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSpider;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftSquid;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftStray;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftStrider;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTraderLlama;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTropicalFish;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftTurtle;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVex;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVillager;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftVindicator;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftWitch;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftWither;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftWitherSkeleton;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftWolf;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftZoglin;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftZombie;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftZombieHorse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nemesis07.survival.backpack.BackpackManager;
import fr.nemesis07.survival.commands.BackpackCmd;
import fr.nemesis07.survival.commands.BcrlCmd;
import fr.nemesis07.survival.commands.EnderchestCmd;
import fr.nemesis07.survival.commands.InvseeCmd;
import fr.nemesis07.survival.commands.PetCmd;
import fr.nemesis07.survival.commands.TpaCmd;
import fr.nemesis07.survival.commands.TpacceptCmd;
import fr.nemesis07.survival.events.PlayerListeners;
import fr.nemesis07.survival.mobs.CustomPet;
import fr.nemesis07.survival.utils.Config;
import fr.nemesis07.survival.utils.Players;
import net.minecraft.server.v1_16_R3.EntityCreature;
import net.minecraft.server.v1_16_R3.EntityTypes;

public class Main extends JavaPlugin {
	/*
	 TODO DANS LE MENU UN SLOT POUR CHOISIR SONT PET
	 TODO DANS LE MENU UN SWITCHER ON/OFF POUR LE MODE BABY DU PET
	 
	 TODO Transformer EntityCreature en EntityLiving pour avoir ghast bat etc
	 TODO Ajouté item pour on/off l'attack
	*/
	public final String prefixBackpack = "§6[§cBackpack§6]";
	public final String prefixTpa = "§e[Téléportation] ";
	private static Main instance;
	
	private BackpackManager backpackManager;
	private Config config;
	private Players players;
	
	public ArrayList<Player> inviteLess1Hour = new ArrayList<>();
	public Map<Player, Player> hasInvite = new HashMap<>();
	
	public Map<Player, CustomPet> map = new HashMap<>();
	public Map<String, EntityTypes<? extends EntityCreature>> type = new HashMap<>();
	@SuppressWarnings("rawtypes")
	public List<Class> attackable = new ArrayList<>();
	//public List<Player> hasAttackCpt = new ArrayList<Player>();
	
	public void onLoad() {
	    instance = this;
	    this.config = new Config();
	    this.players = new Players();
	}
	
	@Override
	public void onEnable() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerListeners(), instance);
		pm.registerEvents(new InvseeCmd(), instance);
		getCommand("backpack").setExecutor(new BackpackCmd());
		getCommand("invsee").setExecutor(new InvseeCmd());
		getCommand("enderchest").setExecutor(new EnderchestCmd());
		getCommand("bp").setExecutor(new BackpackCmd());
		getCommand("tpa").setExecutor(new TpaCmd());
		getCommand("tpaccept").setExecutor(new TpacceptCmd(this));
		getCommand("pet").setExecutor(new PetCmd());
		getCommand("bcrl").setExecutor(new BcrlCmd());
		this.backpackManager = new BackpackManager();
		initType();
		initTypeAttackable();
	}
	
	@Override
	public void onDisable() {
		stop();
		this.backpackManager.save();
		this.config.save();
		this.players.save();
	}
	
	private void stop() {
		for(Player p: Bukkit.getOnlinePlayers()) {
			if(map.isEmpty() == false) {
				CustomPet pet = map.get(p);
				PlayerListeners pl = new PlayerListeners();
				pl.kill(pet);
			}
			getPlayersConfig().set("players."+p.getDisplayName()+".pet.isOut", "off");
		}
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	public BackpackManager getBackpackManager() {
	    return this.backpackManager;
	}
	
	public Config getConfiguration() {
	    return this.config;
	}

	public Players getPlayers() {
		return this.players;
	}

	public YamlConfiguration getPlayersConfig() {
		YamlConfiguration playersConfig = players.getPlayersConfig();
		return playersConfig;
	}
	
	private void initType() {
		type.put("GIANT", EntityTypes.GIANT);
		type.put("WITHER", EntityTypes.WITHER);
		type.put("VILLAGER", EntityTypes.VILLAGER);
		type.put("SKELETON_HORSE", EntityTypes.SKELETON_HORSE);
		type.put("ZOMBIE_HORSE", EntityTypes.ZOMBIE_HORSE);
		type.put("GUARDIAN", EntityTypes.GUARDIAN);
		type.put("ELDER_GUARDIAN", EntityTypes.ELDER_GUARDIAN);
		type.put("PANDA", EntityTypes.PANDA);
		type.put("FOX", EntityTypes.FOX);
		type.put("BEE", EntityTypes.BEE);
		type.put("BLAZE", EntityTypes.BLAZE);
		type.put("CAT", EntityTypes.CAT);
		type.put("CHICKEN", EntityTypes.CHICKEN);
		type.put("CAVE_SPIDER", EntityTypes.CAVE_SPIDER);
		type.put("COW", EntityTypes.COW);
		type.put("CREEPER", EntityTypes.CREEPER);
		type.put("DOLPHIN", EntityTypes.DOLPHIN);
		type.put("DONKEY", EntityTypes.DONKEY);
		type.put("ENDERMAN", EntityTypes.ENDERMAN);
		type.put("ENDERMITE", EntityTypes.ENDERMITE);
		type.put("HOGLIN", EntityTypes.HOGLIN);
		type.put("HORSE", EntityTypes.HORSE);
		type.put("IRON_GOLEM", EntityTypes.IRON_GOLEM);
		type.put("MOOSHROOM", EntityTypes.MOOSHROOM);
		type.put("MULE", EntityTypes.MULE);
		type.put("OCELOT", EntityTypes.OCELOT);
		type.put("LLAMA", EntityTypes.LLAMA);
		type.put("HUSK", EntityTypes.HUSK);
		type.put("DROWNED", EntityTypes.DROWNED);
		type.put("EVOKER", EntityTypes.EVOKER);
		type.put("ILLUSIONER", EntityTypes.ILLUSIONER);
		type.put("PILLAGER", EntityTypes.PILLAGER);
		type.put("PIG", EntityTypes.PIG);
		type.put("PIGLIN", EntityTypes.PIGLIN);
		type.put("POLAR_BEAR", EntityTypes.POLAR_BEAR);
		type.put("PUFFERFISH", EntityTypes.PUFFERFISH);
		type.put("RABBIT", EntityTypes.RABBIT);
		type.put("SALMON", EntityTypes.SALMON);
		type.put("SHEEP", EntityTypes.SHEEP);
		type.put("SHULKER", EntityTypes.SHULKER);
		type.put("SILVERFISH", EntityTypes.SILVERFISH);
		type.put("SKELETON", EntityTypes.SKELETON);
		type.put("SPIDER", EntityTypes.SPIDER);
		type.put("SNOW_GOLEM", EntityTypes.SNOW_GOLEM);
		type.put("SQUID", EntityTypes.SQUID);
		type.put("STRAY", EntityTypes.STRAY);
		type.put("STRIDER", EntityTypes.STRIDER);
		type.put("TRADER_LLAMA", EntityTypes.TRADER_LLAMA);
		type.put("TROPICAL_FISH", EntityTypes.TROPICAL_FISH);
		type.put("TURTLE", EntityTypes.TURTLE);
		type.put("VEX", EntityTypes.VEX);
		type.put("VINDICATOR", EntityTypes.VINDICATOR);
		type.put("WITCH", EntityTypes.WITCH);
		type.put("WITHER_SKELETON", EntityTypes.WITHER_SKELETON);
		type.put("WOLF", EntityTypes.WOLF);
		type.put("ZOGLIN", EntityTypes.ZOGLIN);
		type.put("ZOMBIE", EntityTypes.ZOMBIE);
		type.put("ZOMBIFIED_PIGLIN", EntityTypes.ZOMBIFIED_PIGLIN);
	}
	
	private void initTypeAttackable() {
		attackable.add(CraftGiant.class);
		attackable.add(CraftEnderDragon.class);
		attackable.add(CraftGhast.class);
		attackable.add(CraftBat.class);
		attackable.add(CraftPlayer.class);
		attackable.add(CraftWither.class);
		attackable.add(CraftVillager.class);
		attackable.add(CraftSkeletonHorse.class);
		attackable.add(CraftZombieHorse.class);
		attackable.add(CraftGuardian.class);
		attackable.add(CraftElderGuardian.class);
		attackable.add(CraftPanda.class);
		attackable.add(CraftFox.class);
		attackable.add(CraftBee.class);
		attackable.add(CraftBlaze.class);
		attackable.add(CraftCat.class);
		attackable.add(CraftChicken.class);
		attackable.add(CraftCaveSpider.class);
		attackable.add(CraftCow.class);
		attackable.add(CraftCreeper.class);
		attackable.add(CraftDolphin.class);
		attackable.add(CraftDonkey.class);
		attackable.add(CraftEnderman.class);
		attackable.add(CraftEndermite.class);
		attackable.add(CraftHoglin.class);
		attackable.add(CraftHorse.class);
		attackable.add(CraftIronGolem.class);
		attackable.add(CraftMule.class);
		attackable.add(CraftOcelot.class);
		attackable.add(CraftLlama.class);
		attackable.add(CraftHusk.class);
		attackable.add(CraftDrowned.class);
		attackable.add(CraftEvoker.class);
		attackable.add(CraftIllusioner.class);
		attackable.add(CraftPillager.class);
		attackable.add(CraftPig.class);
		attackable.add(CraftPiglin.class);
		attackable.add(CraftPolarBear.class);
		attackable.add(CraftPufferFish.class);
		attackable.add(CraftRabbit.class);
		attackable.add(CraftSalmon.class);
		attackable.add(CraftSheep.class);
		attackable.add(CraftShulker.class);
		attackable.add(CraftSilverfish.class);
		attackable.add(CraftSkeleton.class);
		attackable.add(CraftSpider.class);
		attackable.add(CraftSquid.class);
		attackable.add(CraftStray.class);
		attackable.add(CraftStrider.class);
		attackable.add(CraftTraderLlama.class);
		attackable.add(CraftTropicalFish.class);
		attackable.add(CraftTurtle.class);
		attackable.add(CraftVex.class);
		attackable.add(CraftVindicator.class);
		attackable.add(CraftWitch.class);
		attackable.add(CraftWitherSkeleton.class);
		attackable.add(CraftWolf.class);
		attackable.add(CraftZoglin.class);
		attackable.add(CraftZombie.class);
		attackable.add(CraftPhantom.class);
		attackable.add(CraftShulker.class);
		attackable.add(CraftSnowman.class);
		attackable.add(CraftPiglinBrute.class);
	}
	
}
