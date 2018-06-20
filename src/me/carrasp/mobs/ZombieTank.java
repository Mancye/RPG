package me.carrasp.mobs;

import java.util.ArrayList;
import java.util.List;
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
import org.bukkit.util.Vector;

import com.illumenmc.rpg.Main;

public class ZombieTank implements Listener {
	
	private static Main plugin;
	
	public static int ztSpawnChance = 20;
	public static double ztHealth = 60D;
	
	private ItemStack dsword = new ItemStack(Material.DIAMOND_SWORD);
	
	public ZombieTank(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	@EventHandler
	private void spawnZT(EntitySpawnEvent event) {
		
		if (!(event.getEntity() instanceof Zombie)) return;
		
		Random fRan = new Random();
		int fChan = fRan.nextInt(100) + 1;
		
		if (fChan <= ztSpawnChance) {
			
			ItemMeta dswordmeta = dsword.getItemMeta();
			dswordmeta.setDisplayName("cmdsword");
			dsword.setItemMeta(dswordmeta);
			
			((Zombie) event.getEntity()).setMaxHealth(ztHealth);
			((Zombie) event.getEntity()).setHealth(ztHealth);
			((Zombie) event.getEntity()).setBaby(false);
			((Zombie) event.getEntity()).setCustomName(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Zombie Tank");
			((Zombie) event.getEntity()).setCustomNameVisible(true);
			((Zombie) event.getEntity()).getEquipment().clear();
			((Zombie) event.getEntity()).getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
			((Zombie) event.getEntity()).getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
			((Zombie) event.getEntity()).getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
			((Zombie) event.getEntity()).getEquipment().setHelmet(new ItemStack(Material.IRON_HELMET));
			((Zombie) event.getEntity()).getEquipment().setItemInMainHand(dsword);
			((Zombie) event.getEntity()).getEquipment().setItemInMainHandDropChance(0f);
			
			
		}
		
	}
	
	@EventHandler
	private void setDamageAmountZT(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Zombie)) return;
		
		
		
		if (!(event.getDamager().getCustomName() != null)) return;
		
		if (!(event.getDamager().getCustomName().equalsIgnoreCase("Zombie Tank"))) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().hasItemMeta()) return;
		
		if (!((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().hasDisplayName()) return;
		
		if (((Zombie) event.getDamager()).getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains("cmdsword")) {
			
			event.setDamage(8D);
			if (event.getEntity() instanceof Player) {
			launchAbility((Player) event.getEntity());
			}
			
			
		}
		
	}
	
	private void launchAbility(final Player p) {
		
		

			List<String> inAir = new ArrayList<String>();
					
			if (!(inAir.contains(p.getName()))) {
						
			p.setVelocity(new Vector(0, 3, 0));
						
			}
		
	}
	
}
