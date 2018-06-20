package com.illumenmc.rpg;

import me.carrasp.rpg.events.BoardChangeEvent;
import me.carrasp.rpg.events.LevelUpEvent;
import me.carrasp.skills.SkillMenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.illumenmc.weapons.WeaponShop;

public class Unlocks implements Listener {
	
	private Main plugin;
	
	public Unlocks(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void onLvlUp(LevelUpEvent event) {
		
		Player p = event.getPlayer();
		
		int coinGain = event.getLevel() * 50;
		int skillGain = event.getLevel();
		
		WeaponShop.addCoins(p, coinGain);
		SkillMenu.skillPoints.put(p.getUniqueId(), SkillMenu.skillPoints.get(p.getUniqueId()) + skillGain);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Recieved " + coinGain + " Coins For Leveling Up!");
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Recieved " + skillGain + " Skill Points For Leveling Up!");
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
		
	}
	
}
