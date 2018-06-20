package com.illumenmc.weapons;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;

public class Spear implements Listener {
	
	private Main plugin;
	
	private List<UUID> alreadyChangedVel = new ArrayList<UUID>();
	
	public Spear(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	public static ItemStack getSpear() {
		
		ItemStack spear = new ItemStack(Material.BLAZE_ROD);
		ItemMeta spearmeta = spear.getItemMeta();
		List<String> spearlore = new ArrayList<String>();
	
		spearmeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Spear");
		
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: 10");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: 5");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Info: Heavy Weight Weapon That Does High Damage");
		
		spearmeta.setLore(spearlore);
		spear.setItemMeta(spearmeta);
		
		return spear;
		
	}
	
	@EventHandler
	private void setVel(PlayerMoveEvent event) {
		
		if (event.getPlayer() == null) return;
		
		if (event.getPlayer().getInventory().getItemInMainHand() == null) return;
		
		if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
				
				if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains(">> Spear")) {
					
					if  (!(alreadyChangedVel.contains(event.getPlayer().getUniqueId()))) {
						
						event.getPlayer().getVelocity().setZ(event.getPlayer().getVelocity().getZ() - .1);
						alreadyChangedVel.add(event.getPlayer().getUniqueId());
					}
					
				} else {
					return;
				}
				
			} else {
				
				return;
				
			}
			
		}
		
	}
	
	@EventHandler
	private void setDmg(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Player)) return;
		
		Player p = (Player) event.getDamager();
		if (p.getInventory().getItemInMainHand().hasItemMeta()) {
			
			if (p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName()) {
				
				if (p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Spear")) {
					
					if (Levels.getLevel(p) >= 5) {
					event.setDamage(20);
					} else {
						p.sendMessage(ChatColor.RED + "You Must Be Atleast Level 5 To Use A Spear");
						return;
					}
					
				} else {
					return;
				}
				
			} else {
				return;
			}
			
		} else {
			return;
		}
		
	}

}
