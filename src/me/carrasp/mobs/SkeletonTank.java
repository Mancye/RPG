package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import com.illumenmc.rpg.Main;

public class SkeletonTank implements Listener {
	
	private Main plugin;
	
	public static int stSpawnChance = 20;
	public static double stHealth = 60D;
	
	public SkeletonTank(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnFS(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Skeleton)) return;
		
		Random stRan = new Random();
		int stChan = stRan.nextInt(100) + 1;
		
		if (stChan <= stSpawnChance) {
			
			((Skeleton) event.getEntity()).setMaxHealth(100);
			((Skeleton) event.getEntity()).setHealth(stHealth);
			event.getEntity().setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Skeleton Tank");
			event.getEntity().setCustomNameVisible(true);
			((Skeleton) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
			((Skeleton) event.getEntity()).getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			((Skeleton) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			((Skeleton) event.getEntity()).getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
			
		} else {
			
			return;
			
		}
		
	}
	
	
	@EventHandler
	private void setDamageST(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Skeleton)) return;
		if (!(event.getDamager().getCustomName() != null)) return;
		if (!(event.getDamager().getCustomName().contains("Skeleton Tank"))) return;
		
		event.setDamage(8D);
		
	}
	

}
