package com.illumenmc.rpg;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.weapons.Dagger;
import com.illumenmc.weapons.Spear;
import com.illumenmc.weapons.ThrowingAxe;
import com.illumenmc.weapons.WeaponShop;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.EffectType;
import de.slikey.effectlib.effect.BleedEffect;
import de.slikey.effectlib.util.DynamicLocation;
import me.carrasp.classes.Archer;
import me.carrasp.classes.Classes;
import me.carrasp.classes.Medic;
import me.carrasp.classes.Tank;
import me.carrasp.guilds.CreateGuild;
import me.carrasp.guilds.Guild;
import me.carrasp.guilds.GuildChat;
import me.carrasp.guilds.GuildManager;
import me.carrasp.mobs.FastSkeleton;
import me.carrasp.mobs.FastZombie;
import me.carrasp.mobs.SkeletonKing;
import me.carrasp.mobs.SkeletonSwordsman;
import me.carrasp.mobs.SkeletonTank;
import me.carrasp.mobs.Tarantula;
import me.carrasp.mobs.ZombieKing;
import me.carrasp.mobs.ZombieSoldier;
import me.carrasp.mobs.ZombieSwordsman;
import me.carrasp.mobs.ZombieTank;
import me.carrasp.mobs.menus.FSMenu;
import me.carrasp.mobs.menus.FZMenu;
import me.carrasp.mobs.menus.Menu;
import me.carrasp.mobs.menus.SKMenu;
import me.carrasp.mobs.menus.SSMenu;
import me.carrasp.mobs.menus.STMenu;
import me.carrasp.mobs.menus.ZKMenu;
import me.carrasp.mobs.menus.ZSLMenu;
import me.carrasp.mobs.menus.ZSMenu;
import me.carrasp.mobs.menus.ZTMenu;
import me.carrasp.party.Group;
import me.carrasp.party.Party;
import me.carrasp.quests.GatherTenLogs;
import me.carrasp.quests.KillSkeletonKing;
import me.carrasp.quests.QuestManager;
import me.carrasp.quests.QuestSign;
import me.carrasp.races.Race;
import me.carrasp.rpg.events.ArmorListener;
import me.carrasp.rpg.events.BoardChangeEvent;
import me.carrasp.rpg.professions.Farming;
import me.carrasp.rpg.professions.Fishing;
import me.carrasp.rpg.professions.Hunting;
import me.carrasp.rpg.professions.Mining;
import me.carrasp.skills.Defense;
import me.carrasp.skills.SkillMenu;
import me.carrasp.skills.Strength;
import me.carrasp.unlocks.ArmorUnlocks;
import mkremins.fanciful.FancyMessage;

public class Main extends JavaPlugin implements Listener {
	
	private EffectManager effectManager;
	
	@Override
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(this, this);
		
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new ArmorListener(getConfig().getStringList("blocked")), this);
		
		if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
	        getLogger().severe("*** HolographicDisplays is not installed or not enabled. ***");
	        getLogger().severe("*** This plugin will be disabled. ***");
	        this.setEnabled(false);
	        return;
	    }
		
		new Race(this);
		new Levels(this);
		new Fishing(this);
		new Hunting(this);
		new Mining(this);
		new DisplayStats(this);
		new ThrowingAxe(this);
		new Unlocks(this);
		new Dagger(this);
		new Spear(this);
		new WeaponShop(this);
		new Party(this);
		new GatherTenLogs(this);
		new Bones(this);
		new Group(this);
		new Classes(this);
		new Archer(this);
		new Tank(this);
		new Medic(this);
		new CreateGuild(this);
		new GuildChat(this);
		
		new KillSkeletonKing(this);
		
		new ActionMenu(this);
		
		//Skills
		new Strength(this);
		//End Of Skills
		
		//Mobs
		new FastZombie(this);
		new FastSkeleton(this);
		new SkeletonSwordsman(this);
		new ZombieSwordsman(this);
		new ZombieTank(this);
		new SkeletonTank(this);
		new Tarantula(this);
		new ZombieKing(this);
		new SkeletonKing(this);
		new ZombieSoldier(this);
		new ZSLMenu(this);
		new STMenu(this);
		new ZTMenu(this);
		new SSMenu(this);
		new FZMenu(this);
		new FSMenu(this);
		new ZSMenu(this);
		new SKMenu(this);
		new ZKMenu(this);
		new Menu(this);
		new QuestSign(this);
		new SkillMenu(this);
		
		//End of mobs
		
		new Farming(this);
		
		new Defense(this);
		
		new ArmorUnlocks(this);
		
		Bones.setUpBandage();
		DisplayStats.sendBoard();
		Levels.setRequirement();
		Mining.setRequirement();
		Fishing.setRequirement();
		Hunting.setRequirement();
		
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[" + ChatColor.RED + "ILLUMENMC RPG ENABLED" + ChatColor.GREEN + "]");
		
		getCommand("party").setExecutor(new Group(this));
		getCommand("accept").setExecutor(new Group(this));
		getCommand("testlist").setExecutor(new Group(this));
		getCommand("questlog").setExecutor(new QuestManager(this));
		getCommand("questtest").setExecutor(new QuestManager(this));
		getCommand("bandage").setExecutor(new Bones(this));
		getCommand("guild").setExecutor(new CreateGuild(this));
		getCommand("guildlist").setExecutor(new CreateGuild(this));
		getCommand("skill").setExecutor(new SkillMenu(this));
		
		GuildManager.setUpStuff();
		
		/*@SuppressWarnings("unchecked")
		List<Guild> guild = (List<Guild>) getConfig().getList("guilds");
		
		for (Guild g : guild) {
			
			GuildManager.guilds.add(g);
			
		}
		getConfig().set("guilds", guild);
		saveConfig();*/
		
		//TODO FIX THIS
		
		
		List<String> lvl = getConfig().getStringList("levels");
		
		for (String level : lvl) {
			
			String[] words = level.split(":");
			Levels.levels.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
			
			
		}
		getConfig().set("levels", lvl);
		saveConfig();
		
		List<String> races = getConfig().getStringList("race");
		
		for (String raceP : races) {
			
			String[] words = raceP.split(":");
			Race.races.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
			
		}
		getConfig().set("race", races);
		saveConfig();
		
		List<String> classes = getConfig().getStringList("class");
		
		for (String classP : classes) {
			
			String[] words = classP.split(":");
			Classes.classes.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
			
		}
		getConfig().set("class", classes);
		saveConfig();
	
		List<String> xp = getConfig().getStringList("XP");
		
		for (String xpP : xp) {
			
			String[] words = xpP.split(":");
			Levels.exp.put(UUID.fromString(words[0]), Double.parseDouble(words[1]));
			
		}
		getConfig().set("XP", xp);
		saveConfig();
		
		List<String> coins = getConfig().getStringList("Coins");
		
		for (String coinsP : coins) {
			
			String[] words = coinsP.split(":");
			WeaponShop.coins.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
			
		}
		getConfig().set("Coins", coins);
		saveConfig();
		
		List<String> fishingLevel = getConfig().getStringList("FishingLevel");
		
		for (String fLevel : fishingLevel) {
			String[] words = fLevel.split(":");
			Fishing.fishingLevel.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("FishingLevel", fishingLevel);
		saveConfig();
		
		List<String> fishingXP = getConfig().getStringList("FishingXP");
		
		for (String fXP : fishingXP) {
			String[] words = fXP.split(":");
			Fishing.fishingXP.put(UUID.fromString(words[0]), Double.parseDouble(words[1]));
		}
		getConfig().set("FishingXP", fishingXP);
		saveConfig();	
		
		List<String> huntingLevel = getConfig().getStringList("HuntingLevel");
		
		for (String hLevel : huntingLevel) {
			String[] words = hLevel.split(":");
			Hunting.huntingLevel.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("HuntingLevel", huntingLevel);
		saveConfig();
		
		List<String> huntingXP = getConfig().getStringList("HuntingXP");
		
		for (String hXP : huntingXP) {
			String[] words = hXP.split(":");
			Hunting.huntingXP.put(UUID.fromString(words[0]), Double.parseDouble(words[1]));
		}
		getConfig().set("HuntingXP", huntingXP);
		saveConfig();	
		
		List<String> miningLevel = getConfig().getStringList("MiningLevel");
		
		for (String mLevel : miningLevel) {
			String[] words = mLevel.split(":");
			Mining.miningLevel.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("MiningLevel", miningLevel);
		saveConfig();
		
		List<String> miningXP = getConfig().getStringList("MiningXP");
		
		for (String hXP : miningXP) {
			String[] words = hXP.split(":");
			Mining.miningXP.put(UUID.fromString(words[0]), Double.parseDouble(words[1]));
		}
		getConfig().set("MiningXP", miningXP);
		saveConfig();
		
		List<String> strength = getConfig().getStringList("StrengthSkill");
		
		for (String str : strength) {
			String[] words = str.split(":");
			Strength.strengthSkill.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("StrengthSkill", strength);
		saveConfig();
		
		List<String> defense = getConfig().getStringList("DefenseSkill");
		
		for (String str : defense) {
			String[] words = str.split(":");
			Defense.defenseSkill.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("DefenseSkill", defense);
		saveConfig();
		
		List<String> skill = getConfig().getStringList("Skill");
		
		for (String str : skill) {
			String[] words = str.split(":");
			SkillMenu.skillPoints.put(UUID.fromString(words[0]), Integer.parseInt(words[1]));
		}
		getConfig().set("Skill", skill);
		saveConfig();
		
		
		//Mobs
		
		FastZombie.fzSpawnChance = getConfig().getInt("fzSpawnChance");
		FastZombie.fzHealth = getConfig().getDouble("fzHealth");
		
		FastSkeleton.fsSpawnChance = getConfig().getInt("fsSpawnChance");
		FastSkeleton.fsHealth = getConfig().getDouble("fsHealth");
		
		SkeletonSwordsman.ssSpawnChance = getConfig().getInt("ssSpawnChance");
		SkeletonSwordsman.ssHealth = getConfig().getDouble("ssHealth");
		
		ZombieSwordsman.zsSpawnChance = getConfig().getInt("zsSpawnChance");
		ZombieSwordsman.zsHealth = getConfig().getDouble("zsHealth");
		
		ZombieTank.ztSpawnChance = getConfig().getInt("ztSpawnChance");
		ZombieTank.ztHealth = getConfig().getDouble("ztHealth");
		
		SkeletonTank.stSpawnChance = getConfig().getInt("stSpawnChance");
		SkeletonTank.stHealth = getConfig().getDouble("stHealth");
		
		Tarantula.tSpawnChance = getConfig().getInt("tSpawnChance");
		Tarantula.tHealth = getConfig().getDouble("tHealth");
		
		SkeletonKing.skSpawnChance = getConfig().getInt("skSpawnChance");
		SkeletonKing.skHealth = getConfig().getDouble("skHealth");
		
		ZombieKing.zkSpawnChance = getConfig().getInt("zkSpawnChance");
		ZombieKing.zkHealth = getConfig().getDouble("zkHealth");
		
		
		ZombieSoldier.zsSpawnChance = getConfig().getInt("zslSpawnChance");
		ZombieSoldier.zsHealth = getConfig().getDouble("zslHealth");
	
		effectManager = new EffectManager(this);
	
	}

	@Override
	public void onDisable() {
		
		
		List<String> lvl = getConfig().getStringList("levels");
		
		for (UUID levelP : Levels.levels.keySet()) {
			
			if (!(lvl.contains(levelP.toString()))) {
			lvl.add(levelP.toString() + ":" +  Levels.levels.get(levelP) );
			} else {
				lvl.remove(levelP.toString());
				lvl.add(levelP.toString() + ":" + Levels.levels.get(levelP));
			}
			
		}
		getConfig().set("levels", lvl);
		saveConfig();
		
		List<String> races = getConfig().getStringList("race");
		
		for (UUID raceP : Race.races.keySet()) {
			
			if (!(races.contains(raceP))) {
			races.add(raceP.toString() + ":" + Race.races.get(raceP));
			} else {
				races.remove(raceP.toString());
				races.add(raceP.toString() + ":" + Race.races.get(raceP));
			}
			
		}
		getConfig().set("race", races);
		saveConfig();
		
		List<String> classes = getConfig().getStringList("class");
		
		for (UUID classP : Classes.classes.keySet()) {
			
			if (!(classes.contains(classP))) {
			classes.add(classP.toString() + ":" + Classes.classes.get(classP));
			} else {
				classes.remove(classP.toString());
				classes.add(classP.toString() + ":" + Classes.classes.get(classP));
			}
			
		}
		getConfig().set("class", classes);
		saveConfig();
		
		List<String> xp = getConfig().getStringList("XP");
		
		for (UUID xpP : Levels.exp.keySet()) {
			
			xp.add(xpP.toString() + ":" + Levels.exp.get(xpP));
			
		}
		getConfig().set("XP", xp);
		saveConfig();
		
		List<String> coins = getConfig().getStringList("Coins");
		
		for (UUID coinsP : WeaponShop.coins.keySet()) {
			
			coins.add(coinsP.toString() + ":" + WeaponShop.coins.get(coinsP));
			
		}
		getConfig().set("Coins", coins);
		saveConfig();
		
		List<String> fishingLevel = getConfig().getStringList("FishingLevel");
		
		for (UUID fLevel : Fishing.fishingLevel.keySet()) {
			
			fishingLevel.add(fLevel.toString() + ":" + Fishing.fishingLevel.get(fLevel));
			
		}
		getConfig().set("FishingLevel", fishingLevel);
		saveConfig();
		
		List<String> fishingXP = getConfig().getStringList("FishingXP");
		
		for (UUID fXP : Fishing.fishingXP.keySet()) {
			
			fishingXP.add(fXP.toString() + ":" + Fishing.fishingXP.get(fXP));
			
		}
		getConfig().set("FishingXP", fishingXP);
		saveConfig();
		
		List<String> huntingLevel = getConfig().getStringList("HuntingLevel");
		
		for (UUID hLevel : Hunting.huntingLevel.keySet()) {
			
			huntingLevel.add(hLevel.toString() + ":" + Hunting.huntingLevel.get(hLevel));
			
		}
		getConfig().set("HuntingLevel", huntingLevel);
		saveConfig();
		
		List<String> huntingXP = getConfig().getStringList("HuntingXP");
		
		for (UUID hXP : Hunting.huntingXP.keySet()) {
			
			huntingXP.add(hXP.toString() + ":" + Hunting.huntingXP.get(hXP));
			
		}
		getConfig().set("HuntingXP", huntingXP);
		saveConfig();
		
		List<String> miningLevel = getConfig().getStringList("MiningLevel");
		
		for (UUID mLevel : Mining.miningLevel.keySet()) {
			
			miningLevel.add(mLevel.toString() + ":" + Mining.miningLevel.get(mLevel));
			
		}
		getConfig().set("MiningLevel", miningLevel);
		saveConfig();
		
		List<String> miningXP = getConfig().getStringList("MiningXP");
		
		for (UUID hXP : Mining.miningXP.keySet()) {
			
			miningXP.add(hXP.toString() + ":" + Mining.miningXP.get(hXP));
			
		}
		getConfig().set("MiningXP", miningXP);
		saveConfig();
		
		List<String> skill = getConfig().getStringList("Skill");
		
		for (UUID str : SkillMenu.skillPoints.keySet()) {
			
			skill.add(str.toString() + ":" + SkillMenu.skillPoints.get(str));
			
		}
		getConfig().set("Skill", skill);
		saveConfig();
		
		List<String> strength = getConfig().getStringList("StrengthSkill");
		
		for (UUID str : Strength.strengthSkill.keySet()) {
			
			strength.add(str.toString() + ":" + Strength.strengthSkill.get(str));
			
		}
		getConfig().set("StrengthSkill", strength);
		saveConfig();
		
		List<String> defense = getConfig().getStringList("DefenseSkill");
		
		for (UUID str : Defense.defenseSkill.keySet()) {
			
			defense.add(str.toString() + ":" + Defense.defenseSkill.get(str));
			
		}
		getConfig().set("DefenseSkill", defense);
		saveConfig();
		

		
		
		//Mobs
		
		getConfig().set("fzSpawnChance", FastZombie.fzSpawnChance);
		saveConfig();
		
		getConfig().set("fzHealth", FastZombie.fzHealth);
		saveConfig();
		
		getConfig().set("fsSpawnChance", FastSkeleton.fsSpawnChance);
		saveConfig();
		
		getConfig().set("fsHealth", FastSkeleton.fsHealth);
		saveConfig();
		
		getConfig().set("ssSpawnChance", SkeletonSwordsman.ssSpawnChance);
		saveConfig();
		
		getConfig().set("ssHealth", SkeletonSwordsman.ssHealth);
		saveConfig();
		
		getConfig().set("zsSpawnChance", ZombieSwordsman.zsSpawnChance);
		saveConfig();
		
		getConfig().set("zsHealth", ZombieSwordsman.zsHealth);
		saveConfig();
		
		getConfig().set("ztSpawnChance", ZombieTank.ztSpawnChance);
		saveConfig();
		
		getConfig().set("ztHealth", ZombieTank.ztHealth);
		saveConfig();
		
		getConfig().set("stSpawnChance", SkeletonTank.stSpawnChance);
		saveConfig();
		
		getConfig().set("stHealth", SkeletonTank.stHealth);
		saveConfig();
		
		getConfig().set("tSpawnChance", Tarantula.tSpawnChance);
		saveConfig();
		
		getConfig().set("tHealth", Tarantula.tHealth);
		saveConfig();
		
		getConfig().set("skSpawnChance", SkeletonKing.skSpawnChance);
		saveConfig();
		
		getConfig().set("skHealth", SkeletonKing.skHealth);
		saveConfig();
		
		getConfig().set("zkSpawnChance", ZombieKing.zkSpawnChance);
		saveConfig();
		
		getConfig().set("zkHealth", ZombieKing.zkHealth);
		saveConfig();
		
		getConfig().set("zslSpawnChance", ZombieSoldier.zsSpawnChance);
		saveConfig();
		
		getConfig().set("zslHealth", ZombieSoldier.zsHealth);
		saveConfig();
		
		getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[" + ChatColor.DARK_RED + "ILLUMENMC RPG DISABLED" + ChatColor.DARK_RED+ "]");
		
		HandlerList.unregisterAll((Listener) this);
	
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("race")) {
			if (!(Race.races.containsKey(p.getName()))) {
				return false;
			} else {
			
			p.openInventory(Race.getRaceGUI());
			return true;
			}
		}
		
		if (command.getName().equalsIgnoreCase("admin")) {
			if (!(p.getName().equalsIgnoreCase("mancee"))) return false;
			WeaponShop.setCoins(p, 1000);
			Levels.levels.put(p.getUniqueId(), 20);
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("cm")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Arguments: /cm menu");
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("menu")) {
					p.openInventory(Menu.getCMMenu());
					return true;
					
				} else {
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Arguments: /cm menu");
					return false;
				}
			} else {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Arguments: /cm menu");
				return false;
			}
		}
		
		if (command.getName().equalsIgnoreCase("weaponshop")) {
			p.openInventory(WeaponShop.getWeaponShop());
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Weapon Shop Opened!");
			return true;
		}
		
		if (command.getName().equalsIgnoreCase("cc")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Do /cc (1 = Global, 2 = Race Chat, 3 = Class Chat, 4 = Near Chat(100 Block Radius)");
				return false;
			} else {
				
				if (args.length == 1) {
				
					if (args[0].equalsIgnoreCase("1")) {
						Party.setChannel(p, 1);
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Chat Channel Set To: Global");
						
					} else if (args[0].equalsIgnoreCase("2")) {
						Party.setChannel(p, 2);
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Chat Channel Set To: Race Chat");
						
					} else if (args[0].equalsIgnoreCase("3")) {
						Party.setChannel(p, 3);
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Chat Channel Set To: Near Chat");
						
					} else if (args[0].equalsIgnoreCase("4")) {
						Party.setChannel(p, 4);
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Chat Channel Set To: Party Chat");
					}
					
				} else {
					p.sendMessage(ChatColor.RED + ">> ERROR << " + ChatColor.GRAY + "Invalid Arguments, Do /cc For Help");
				}
				
			}
		}
		
		if (command.getName().equalsIgnoreCase("group")) {
			
			if (args.length == 0) {
				
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "To Create A New Group: " + ChatColor.RED + "/group create (groupnam)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "To Join An Existing Group: " + ChatColor.RED + "/group join (groupname)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "To Leave A Group: " + ChatColor.RED + "/group leave");
				
			} else {
				
				if (args.length == 1) {
					
					
					
				}
				
			}
			
		}
		
		if (command.getName().equalsIgnoreCase("gm")) {
			
			if (p.hasPermission("illumenmc.gm")) {
				
				if (p.getGameMode().equals(GameMode.CREATIVE)) {
					p.setGameMode(GameMode.SURVIVAL);
					return true;
				} else {
					p.setGameMode(GameMode.CREATIVE);
					return true;
				}
				
			} else {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You May Not Do That Command...");
				return false;
			}
			
		}
		
		if (command.getName().equalsIgnoreCase("profile")) {
			
			if (args.length == 0) {
				
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Your Profile:");
				if (Levels.levels.containsKey(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Level: " + Levels.levels.get(p.getUniqueId()));
				} else {
					return false;
				}
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Current XP: " + Levels.getExp(p));
				if (Race.races.containsKey(p.getUniqueId())) {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Your Race: " + Race.asName(Race.races.get(p.getUniqueId())));
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Coins: " + WeaponShop.getCoins(p));
				} else {
					return false;
				}
				
				
			} else {
				
				if (args.length == 1) {
					
					if (Bukkit.getServer().getPlayer(args[0]) != null) {
						
						Player profPlayer = Bukkit.getServer().getPlayer(args[0]);
						
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + profPlayer.getName() + "'s Profile:");
						if (Levels.levels.containsKey(profPlayer.getUniqueId())) {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Level: " + Levels.levels.get(profPlayer.getUniqueId()));
						} else {
							return false;
						}
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Current XP: " + Levels.getExp(profPlayer));
						if (Race.races.containsKey(p.getUniqueId())) {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + profPlayer.getName() + "'s Race: " + Race.asName(Race.races.get(profPlayer.getUniqueId())));
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + profPlayer.getName() + "'s Coins: " + WeaponShop.coins.get(profPlayer.getUniqueId()));
						} else {
							return false;
						}
						
					} else {
						p.sendMessage(ChatColor.RED + ">>ERROR>> " + ChatColor.GRAY + "Player Not Found!");
						return false;
					}
					
				} else {
					p.sendMessage(ChatColor.RED + ">>ERROR>> " + ChatColor.GRAY + "Invalid Arguments, do /profile or /profile (playername)");
					return false;
				}
				
			}
			
		}
		
		return false;
		
	}

	@EventHandler
	private void setChatTooltips(AsyncPlayerChatEvent event) {
		
		Player p = event.getPlayer();
		
		String message = event.getMessage();
		
		StringBuilder sb = new StringBuilder();
		
		if (GuildChat.isInGuildChat.containsKey(p.getUniqueId())) return;
		
		event.setCancelled(true);
		
		sb.append(message);
		for (Player online : Bukkit.getServer().getOnlinePlayers()) {
			if (!(p.getName().equalsIgnoreCase("mancee"))) {
			new FancyMessage(p.getDisplayName() + ChatColor.RED + " >> " + ChatColor.RESET + sb.toString())
			.tooltip("Click To See Player's Profile!")
			.color(ChatColor.RED)
			.command("/profile " + p.getName())
			.send(online);
			} else {
				new FancyMessage(p.getDisplayName() + ChatColor.GREEN + ChatColor.BOLD + " >> " + ChatColor.RESET + sb.toString())
				.tooltip("Click To See Player's Profile!")
				.color(ChatColor.RED)
				.command("/profile " + p.getName())
				.send(online);
			}
			
		
		
		}
		
	}
	
	@EventHandler
	private void noVanish(final PlayerJoinEvent event) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(this, new Runnable() {

			@Override
			public void run() {

				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					
					DisplayStats.sendBoard();
					
					p.sendMessage("test");
					
					if (!(p.canSee(event.getPlayer()))) {
						
						p.showPlayer(event.getPlayer());
						
					}
				}
				
				if (event.getPlayer().hasPotionEffect(PotionEffectType.INVISIBILITY)) {
					event.getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
					
				}
				
			}
			
		}, 80L);
	}
	
	@EventHandler
	private void damageBlood(EntityDamageByEntityEvent event) {
		
		Location loc = event.getEntity().getLocation().add(0, 1, 0);
		DynamicLocation dLoc = new DynamicLocation(loc, event.getEntity());
		BleedEffect blood = new BleedEffect(effectManager);
		blood.setDynamicOrigin(dLoc);
		blood.period = 0.2;
		blood.speed = 10f;
		blood.type = EffectType.REPEATING;
		blood.iterations = 1;
		blood.setTargetLocation(event.getEntity().getLocation());
		blood.setDynamicTarget(dLoc);
		blood.visibleRange = 10f;
		blood.start();
	}
	
	@EventHandler
	private void scoreboardUpdate(BoardChangeEvent event) {
		
		DisplayStats.sendBoard();
		
	}
	
}
