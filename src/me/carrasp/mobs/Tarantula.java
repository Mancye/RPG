package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Spider;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.rpg.Main;

public class Tarantula implements Listener {
	
	//Boss Mob
	
	private Main plugin;
	
	public static int tSpawnChance = 10;
	public static double tHealth = 80;
	
	
	public Tarantula(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnT(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Spider)) return;
		
		Random tRan = new Random();
		int tChan = tRan.nextInt(100) + 1;
		
		if (tChan <= tSpawnChance) {
			((Spider) event.getEntity()).setMaxHealth(100);
			((Spider) event.getEntity()).setHealth(tHealth);
			event.getEntity().setCustomName(ChatColor.DARK_RED + ChatColor.UNDERLINE.toString() + "TARANTULA");
			event.getEntity().setCustomNameVisible(true);
			
		} else {
			
			return;
			
		}
		
	}
	
	
	@EventHandler
	private void setDamageT(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Spider)) return;
		
		if (event.getDamager().isCustomNameVisible() == false) return;
		
		if (!(event.getDamager().getCustomName() != null)) return;
		
		if (!(event.getDamager().getCustomName().contains("TARANTULA"))) return;
		
		event.setDamage(10);
		if (event.getEntity() instanceof Player) {
			
			((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 2));
			((Player) event.getEntity()).sendMessage(ChatColor.RED + "You Have Been Bitten By The Tarantula!");
		}
		
	}

}
