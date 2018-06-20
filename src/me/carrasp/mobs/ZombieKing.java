package me.carrasp.mobs;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.rpg.Main;

public class ZombieKing implements Listener {
	
	private Main plugin;
	
	public static int zkSpawnChance = 10;
	public static double zkHealth = 80D;
	
	private ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
	
	public ZombieKing(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	private void spawnZK(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
		Random fRan = new Random();
		int fChan = fRan.nextInt(100) + 1;
		
		if (fChan <= zkSpawnChance) {
			
			ItemMeta dswordmeta = dsword.getItemMeta();
			dswordmeta.setDisplayName("cmdsword");
			dsword.setItemMeta(dswordmeta);
			
			((Zombie) event.getEntity()).setMaxHealth(zkHealth);
			((Zombie) event.getEntity()).setHealth(zkHealth);
			((Zombie) event.getEntity()).setBaby(false);
			((Zombie) event.getEntity()).setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Zombie Tank");
			((Zombie) event.getEntity()).setCustomNameVisible(true);
			((Zombie) event.getEntity()).getEquipment().clear();
			((Zombie) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS));
			((Zombie) event.getEntity()).getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS));
			((Zombie) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE));
			((Zombie) event.getEntity()).getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET));
			((Zombie) event.getEntity()).getEquipment().setItemInMainHand(dsword);
			((Zombie) event.getEntity()).getEquipment().setItemInMainHandDropChance(0f);
			
			
		}
		
	}
	
	@EventHandler
	private void setDamageAmountZK(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Zombie)) return;
		
		if (event.getDamager().getCustomName() == null) return;
		
		if (!(event.getEntity().getCustomName().contains("Zombie Tank"))) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().hasItemMeta()) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		
		if (((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("cmdsword")) {
			
			event.setDamage(8D);
			
			if (!(event.getEntity() instanceof Player)) return;
			
			((Player) event.getEntity()).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20 * 3, 2));
			
		}
		
	}
	
}
