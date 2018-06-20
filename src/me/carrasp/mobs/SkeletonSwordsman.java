package me.carrasp.mobs;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Main;

public class SkeletonSwordsman implements Listener {

	private Main plugin;
	
	private Map<UUID, Double> health = new HashMap<UUID, Double>();
	
	private ItemStack isword = new ItemStack(Material.IRON_SWORD);
	
	public static int ssSpawnChance = 20;
	public static double ssHealth = 40D;
	
	public SkeletonSwordsman(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnSS(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Skeleton)) return;
		
		Random ssRan = new Random();
		int ssChan = ssRan.nextInt(100) + 1;
		
		if (ssChan <= ssSpawnChance) {
			
			ItemMeta iswordmeta = isword.getItemMeta();
			iswordmeta.setDisplayName("cmironsword");
			isword.setItemMeta(iswordmeta);
			((Skeleton) event.getEntity()).setMaxHealth(100);
			((Skeleton)  event.getEntity()).setHealth(ssHealth);
			event.getEntity().setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Skeleton Swordsman");
			event.getEntity().setCustomNameVisible(true);
			((Skeleton) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
			((Skeleton) event.getEntity()).getEquipment().setItemInMainHand(isword);
			((Skeleton) event.getEntity()).getEquipment().setItemInMainHandDropChance(0f);
			
		} else {
			
			return;
			
		}
		
	}
	
	@EventHandler
	private void setDamageAmountSS(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Skeleton)) return;
		if (!(event.getDamager().getCustomName() != null)) return;
		if (!(event.getDamager().getCustomName().equalsIgnoreCase("Skeleton Swordsman"))) return;
		if (!(health.containsKey(event.getDamager().getUniqueId()))) return;
		
		if (!((Skeleton) event.getDamager()).getEquipment().getItemInMainHand().hasItemMeta()) return;
		
		if (!((Skeleton) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		
		
		if (((Skeleton) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("cmironsword")) {
			
			event.setDamage(7);
			
		}
		
	}
	
}
