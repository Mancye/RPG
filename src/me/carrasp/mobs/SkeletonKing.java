package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.rpg.Main;

public class SkeletonKing implements Listener {
	
	private Main plugin;
	
	public static int skSpawnChance = 10;
	public static double skHealth = 80;
	
	private PotionEffect glow = new PotionEffect(PotionEffectType.GLOWING, 999999999, 4, false, false, Color.PURPLE);
	
	public SkeletonKing(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	
	@EventHandler
	private void spawnSK(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Skeleton)) return;
		
		Random skRan = new Random();
		int skChan = skRan.nextInt(100) + 1;
		
		if (skChan <= skSpawnChance) {
			
			((Skeleton) event.getEntity()).setMaxHealth(100);
			((Skeleton) event.getEntity()).setHealth(skHealth);
			event.getEntity().setCustomName(ChatColor.DARK_RED + ChatColor.UNDERLINE.toString() + "SKELETON KING");
			event.getEntity().setCustomNameVisible(true);
			((Skeleton) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
			((Skeleton) event.getEntity()).getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
			((Skeleton) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
			((Skeleton) event.getEntity()).getEquipment().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
			((Skeleton) event.getEntity()).addPotionEffect(glow);
			
		} else {
			
			return;
			
		}
		
	}
	
	@EventHandler
	private void setDamageSK(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Skeleton)) return;
		if (!(event.getDamager().getCustomName() != null)) return;
		if (!(event.getDamager().getCustomName().contains("SKELETON KING"))) return;
		
		event.setDamage(9);
		
		if (!(event.getEntity() instanceof Player)) return;
		
		((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 2));
		
	}
	

}
