package com.illumenmc.rpg;

import me.carrasp.classes.Classes;
import me.carrasp.quests.Quest;
import me.carrasp.quests.QuestManager;
import me.carrasp.races.Race;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.illumenmc.weapons.WeaponShop;

public class DisplayStats implements Runnable {
	
	static Main plugin;
	
	
	static SimpleScoreboard stats;
	
	public DisplayStats(Main main) {
		plugin = main;
	}
	
	private static void sendBoard(Player player) {
		
		DisplayStats.stats = new SimpleScoreboard(ChatColor.RED + ">> " + ChatColor.GRAY + "Your Profile" + ChatColor.RED + " <<");
		if (Levels.levels.containsKey(player.getUniqueId())) {
		stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Level: " + ChatColor.RED + Levels.levels.get(player.getUniqueId()));
		} else {
			stats.blankLine();
		}
		stats.blankLine();
		if (Levels.exp.containsKey(player.getUniqueId())) {
		stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "XP: " + ChatColor.RED + Levels.getExp(player));
		} else {
			return;
		}
		
		stats.blankLine();
		
		if (Race.races.containsKey(player.getUniqueId())) {
		stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Race: " + ChatColor.RED.toString() + Race.asName(Race.races.get(player.getUniqueId())) + "");
		} else {
			stats.blankLine();
		}
		stats.blankLine();
		
		if (Classes.classes.containsKey(player.getUniqueId())) {
			stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Class: " + ChatColor.RED.toString() + Classes.asName(Classes.classes.get(player.getUniqueId())) + "");
			} else {
				stats.blankLine();
			}
			stats.blankLine();
		
		if (WeaponShop.coins.containsKey(player.getUniqueId())) {
			
			stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Coins: " + ChatColor.RED.toString() + WeaponShop.getCoins(player) + "");
			
		} else {
			stats.blankLine();
		}
		
		stats.blankLine();
		
		if (QuestManager.playersQuest.containsKey(player.getName())) {
			
			stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Quest: " + ChatColor.RED.toString() + Quest.getPlayersQuest(player).questName + "");
			
		} else {
			stats.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Quest: " + ChatColor.RED.toString() + "None!");
		}
		
		stats.blankLine();
		
		stats.build();
		stats.send(player);
		
	}
	
	public static void sendBoard() {
		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			
			sendBoard(p);
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
