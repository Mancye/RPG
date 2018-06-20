package me.carrasp.skills;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.rpg.Main;

public class Defense implements Listener {
	
	public static Map<UUID, Integer> defenseSkill = new HashMap<UUID, Integer>();

	private Main plugin;
	
	public Defense(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void setDefaults(final PlayerJoinEvent event) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				if (defenseSkill.containsKey(event.getPlayer().getUniqueId())) return;
				
				defenseSkill.put(event.getPlayer().getUniqueId(), 0);
				
				if (SkillMenu.skillPoints.containsKey(event.getPlayer().getUniqueId()));
				
				SkillMenu.skillPoints.put(event.getPlayer().getUniqueId(), 0);
				
			}
			
			
		}, 20 * 2);
		
	}
	
	@EventHandler
	private void damageHandle(EntityDamageByEntityEvent event) {
		 
		
		if (!(event.getEntity() instanceof Player)) return;
		
		if (!(defenseSkill.containsKey(event.getEntity().getUniqueId()))) return;
		
		Player p = (Player) event.getEntity();
		
		p.sendMessage("before shit damage done = " + event.getDamage());
		
		int skill = defenseSkill.get(p.getUniqueId());
		
		double damage = event.getDamage();
		
		double minusDamage = ((skill * 0.2) / 2);
		
		if (minusDamage == 0) {
			return;
		} else {
			
			event.setDamage(damage - minusDamage);
			p.sendMessage("damage done = " + event.getDamage());
		}
		
	}
	
}
