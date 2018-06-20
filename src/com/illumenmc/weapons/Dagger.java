package com.illumenmc.weapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;

public class Dagger implements Listener {
	
	private Main plugin;
	
	public Dagger(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	public static ItemStack getDagger() {
		
		ItemStack dagger = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta daggerMeta = dagger.getItemMeta();
		List<String> daggerLore = new ArrayList<String>();
		
		daggerMeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Dagger");
		
		daggerLore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: 5(From Within 1.5 Blocks), 2(Any Farther Than A Block)");
		daggerLore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: 3");
		daggerLore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Info: Close Ranged Weapon, Does Signifcant Damage If You Are Close");
		
		daggerMeta.setLore(daggerLore);
		dagger.setItemMeta(daggerMeta);
		
		return dagger;
	}
	
	@EventHandler
	private void setDamage(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Player)) return;
		
		Player p = (Player) event.getDamager();
		
		ItemStack axe = p.getInventory().getItemInMainHand();
		
		if (axe.hasItemMeta() && axe.getItemMeta().getDisplayName().contains("Dagger")) {
		
			if (p.getNearbyEntities(1.5, 1.5, 1.5).contains(event.getEntity())) {
			event.setDamage(10);
			} else {
				
				event.setDamage(4);
				
			}
			
		if (Levels.getLevel(p) >= 3) {
			
		} else {
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Must Be At Least Level 3 To Use This Weapon!");
			event.setCancelled(true);
			return;
		}
		
		} else {
			return;
		}
		
	}
	
}
