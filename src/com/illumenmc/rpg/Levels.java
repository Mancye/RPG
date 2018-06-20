package com.illumenmc.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import me.carrasp.races.Race;
import me.carrasp.rpg.events.BoardChangeEvent;
import me.carrasp.rpg.events.LevelUpEvent;
import me.carrasp.rpg.events.XPGainEvent;

public class Levels implements Listener, Runnable {

	public static Map<UUID, Integer> levels = new HashMap<UUID, Integer>();
	public static Map<UUID, Double> exp = new HashMap<UUID, Double>();
	public static Map<Integer, Integer> levelRequirement = new HashMap<Integer, Integer>();
	
	private static Main plugin;
	
	public Levels(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void setLevelDefault(PlayerJoinEvent event) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		final Player p = event.getPlayer();
		
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
			
			@Override
			public void run() {
		
		if (!(levels.containsKey(p.getUniqueId()))) {
			
			
					levels.put(p.getUniqueId(), 1);
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Welcome, You Are Level " + levels.get(p.getUniqueId()));
					if (!(exp.containsKey(p.getUniqueId()))) {
						exp.put(p.getUniqueId(), (double) 1);
					} else {
						return;
					}

		} else {
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Welcome Back, You're Level: " + levels.get(p.getUniqueId()));
			if (Race.races.containsKey(p.getUniqueId())) {
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You're Race: " + Race.asName(Race.races.get(p.getUniqueId())));
			} else {
				return;
			}
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Welcome Back, You're EXP: " + exp.get(p.getUniqueId()));
			return;
		}
		
		}
			
	}, 40L);
	}
	
	public static void setRequirement() {
		
		for (int i = 0; i < 100; i++) {
			
			levelRequirement.put(i, i * 50);
			
		}
	}
	
	public static void checkLevelUp() {

				
				for (Player p : Bukkit.getServer().getOnlinePlayers()) {
					if (!(levels.containsKey(p.getUniqueId()))) return;
					if (!(exp.containsKey(p.getUniqueId()))) return;
					if (!(levelRequirement.containsKey(getLevel(p) + 1))) return;
					
					if (getExp(p) >= levelRequirement.get(getLevel(p) + 1)) {
						levels.put(p.getUniqueId(), levels.get(p.getUniqueId()) + 1);
						setExp(p, (double) 0);
						Bukkit.getServer().getPluginManager().callEvent(new LevelUpEvent(p));
						Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
					}
					
				}	
	
	}
	
	public static void levelUp(Player p) {
		
		levels.put(p.getUniqueId(), levels.get(p.getUniqueId()) + 1);
		Bukkit.getServer().getPluginManager().callEvent(new LevelUpEvent(p));
	}
	
	public static void addExp(Player p, double d) {
		
		exp.put(p.getUniqueId(), exp.get(p.getUniqueId()) + d);
		Bukkit.getServer().getPluginManager().callEvent(new XPGainEvent(p));
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
		
	}
	
	public static Double getExp(Player p) {
		
		if (!(exp.containsKey(p.getUniqueId()))) return 0.0;
		
		return exp.get(p.getUniqueId());
		
	}
	
	public static void setExp(Player p, Double amount) {
		
		if (!(exp.containsKey(p.getUniqueId()))) return;
		if (amount < 0) return;
		
		exp.put(p.getUniqueId(), amount);
		
	}
	
	public static int getLevel(Player p) {
		
		if (!(levels.containsKey(p.getUniqueId()))) return -1;
		
		return levels.get(p.getUniqueId());
		
	}
	
	@EventHandler
	private void onLevelUp(LevelUpEvent event) {
		
		Player p = event.getPlayer();
		
		p.getWorld().playEffect(p.getLocation().add(new Vector(0, 1, 0)), Effect.MOBSPAWNER_FLAMES, 30);
		p.playSound(p.getLocation(), Sound.ENTITY_FIREWORK_LAUNCH, 30F, 30F);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Leveled Up! You Are Now A Level: " + levels.get(p.getUniqueId()));
		
	}
	
	@EventHandler
	private void onXPGain(XPGainEvent event) {
		checkLevelUp();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
