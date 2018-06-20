package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.rpg.Main;

public class FastZombie implements Listener {
	
	private Main plugin;
	
	private PotionEffect fast = new PotionEffect(PotionEffectType.SPEED, 20 * 9999999, 4);
	
	public static int fzSpawnChance = 20;
	public static double fzHealth = 30D;
	
	public FastZombie(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnFZ(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
		Random fRan = new Random();
		int fChan = fRan.nextInt(100) + 1;
		
		if (fChan <= fzSpawnChance) {
			
			((Zombie) event.getEntity()).setMaxHealth(100);
			((Zombie) event.getEntity()).setHealth(fzHealth);
			((Zombie) event.getEntity()).setBaby(false);
			((Zombie) event.getEntity()).setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Fast Zombie");
			((Zombie) event.getEntity()).setCustomNameVisible(true);
			((Zombie) event.getEntity()).getEquipment().clear();
			((Zombie) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
			((Zombie) event.getEntity()).addPotionEffect(fast);
			
		}
		
	}
	
	@EventHandler
	private void dropsFZ(EntityDeathEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
			
			if (!event.getEntity().getCustomName().equals(null)) {
				
				if (event.getEntity().getCustomName().contains("Fast Zombie")) {
					
					event.getDrops().clear();
					event.getDrops().add(new ItemStack(Material.LEATHER_BOOTS));
					
				}
				
			}
			
		
	}

}
