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
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Main;

public class ZombieSwordsman implements Listener {
	
	private Main plugin;
	
	public static int zsSpawnChance = 20;
	public static double zsHealth = 40D;
	
	private ItemStack isword = new ItemStack(Material.IRON_SWORD);
	
	
	public ZombieSwordsman(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnZS(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
		Random fsRan = new Random();
		int fsChan = fsRan.nextInt(100) + 1;
		
		if (fsChan <= zsSpawnChance) {
			
			ItemMeta iswordmeta = isword.getItemMeta();
			iswordmeta.setDisplayName("cmironsword");
			isword.setItemMeta(iswordmeta);
			
			((Zombie) event.getEntity()).setMaxHealth(zsHealth);
			((Zombie) event.getEntity()).setHealth(zsHealth);
			event.getEntity().setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Zombie Swordsman");
			event.getEntity().setCustomNameVisible(true);
			((Zombie) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			((Zombie) event.getEntity()).getEquipment().setItemInMainHand(isword);
			((Zombie) event.getEntity()).getEquipment().setItemInMainHandDropChance(0f);
			
			
		} else {
			
			return;
			
		}
		
	}
	
	@EventHandler
	private void setDamageAmountZS(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Zombie)) return;
		
		if (event.getDamager().isCustomNameVisible() == false) return;
		
		if (!(event.getDamager().getCustomName() != null)) return;
		
		if (!(event.getDamager().getCustomName().equalsIgnoreCase("Zombie Swordsman"))) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().hasItemMeta()) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		
		if (((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("cmironsword")) {
			
			event.setDamage(7);
			
		}
		
	}

}
