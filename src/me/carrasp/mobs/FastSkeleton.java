package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.rpg.Main;

public class FastSkeleton implements Listener {
	
	private Main plugin;
	
	private PotionEffect fast = new PotionEffect(PotionEffectType.SPEED, 20 * 9999999, 4);
	
	public static int fsSpawnChance = 20;
	public static double fsHealth = 40D;
	
	public FastSkeleton(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnFS(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Skeleton)) return;
		
		Random fsRan = new Random();
		int fsChan = fsRan.nextInt(100) + 1;
		
		if (fsChan <= fsSpawnChance) {
			
			((Skeleton) event.getEntity()).setMaxHealth(100);
			((Skeleton) event.getEntity()).setHealth(fsHealth);
			event.getEntity().setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Fast Skeleton");
			event.getEntity().setCustomNameVisible(true);
			((Skeleton) event.getEntity()).addPotionEffect(fast);
			((Skeleton) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			
		} else {
			
			return;
			
		}
		
	}
	

	
	@EventHandler
	private void dropsFS(EntityDeathEvent event) {
		
		if (!(event.getEntity() instanceof Skeleton)) return;
			if ((!event.getEntity().getCustomName().equals(null))) {
				
				if (event.getEntity().getCustomName().contains("Fast Skeleton")) {
					
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(Material.LEATHER_BOOTS));
					
			}
				
		}	
		
	}

}
