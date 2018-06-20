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

public class Strength implements Listener, Runnable {

	private Main plugin;

	public static Map<UUID, Integer> strengthSkill = new HashMap<UUID, Integer>();
	
	public Strength(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	private void setDefaults(final PlayerJoinEvent event) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				if (strengthSkill.containsKey(event.getPlayer().getUniqueId())) return;
				
				strengthSkill.put(event.getPlayer().getUniqueId(), 0);
				
			}
			
		}, 20 * 2);
		
	}
	
	@EventHandler
	private void strengthDamageHandle(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Player)) return;
		
		Player p = (Player) event.getDamager();
		
		if (!(strengthSkill.containsKey(p.getUniqueId()))) return;
		
		double damageBonus = 1 + ((strengthSkill.get(p.getUniqueId()) * 0.01) * 5);
		
		double damage = event.getDamage() * damageBonus;
		
		event.setDamage(damage);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
