package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

import com.illumenmc.rpg.Main;

public class ZombieSoldier implements Listener {
	
	private Main plugin;
	
	public static double zsHealth = 60D;
	public static int zsSpawnChance = 20;
	
	public ZombieSoldier(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnZS(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
		Random zsRan = new Random();
		int zsChan = zsRan.nextInt(100) + 1;
		
		if (zsChan <= zsSpawnChance) {
			
			((Zombie) event.getEntity()).setMaxHealth(zsHealth);
			((Zombie) event.getEntity()).setHealth(zsHealth);
			((Zombie) event.getEntity()).setBaby(false);
			((Zombie) event.getEntity()).setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Zombie Soldier");
			((Zombie) event.getEntity()).setCustomNameVisible(true);
			((Zombie) event.getEntity()).getEquipment().clear();
			((Zombie) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
			((Zombie) event.getEntity()).getEquipment().setItemInMainHand(new ItemStack(Material.IRON_SWORD));
			
		}
		
	}
	
	@EventHandler
	private void setDamageZS(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Zombie)) return;
		
		if (event.getDamager().isCustomNameVisible() == false) return;
		
		if (!(event.getDamager().getCustomName() != null)) return;
		
		if (!(event.getDamager().getCustomName().contains("Zombie Soldier"))) return;
		
		event.setDamage(8D);
		
	}

}
