package com.illumenmc.weapons;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;

public class ThrowingAxe implements Listener {
	
	private Main plugin;
	
	public ThrowingAxe(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static ItemStack getThrowingAxe() {
		
		ItemStack axe = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta axemeta = axe.getItemMeta();
		List<String> axelore = new ArrayList<String>();
		
		axemeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Throwing Axe");
		
		axelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: 3");
		axelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: 3");
		axelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Info: Right Click To Throw");
		
		axemeta.setLore(axelore);
		axe.setItemMeta(axemeta);
		
		return axe;
		
	}
	
	@EventHandler
	private void onThrow(PlayerInteractEvent event) {
		
		Player p = event.getPlayer();
		
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			ItemStack axe = p.getInventory().getItemInMainHand();
			
			if (axe.getType().equals(Material.DIAMOND_AXE)) {
				
				if (axe.hasItemMeta()) {
					
					if (axe.getItemMeta().hasDisplayName()) {
						
						if (axe.getItemMeta().getDisplayName().contains("Throwing Axe")) {
							
							if (Levels.getLevel(p) >= 3) {
							
							Item item = p.getWorld().dropItem(p.getLocation().add(new Vector(0, 0, 0)), axe);
							
							item.setVelocity(p.getEyeLocation().getDirection().multiply(2));
							item.setPickupDelay(2);
							
							p.getInventory().getItemInMainHand().setType(Material.AIR);;
							
							} else {
								p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Must Be At Least Level 3 To Use A Throwing Axe!");
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
			
		} else {
			return;
		}
		
	}
	
	@EventHandler
	private void onHit(PlayerPickupItemEvent event) {
		
		Player p = event.getPlayer();
		ItemStack i = event.getItem().getItemStack();
		
		if (i.getType().equals(Material.DIAMOND_AXE) && i.hasItemMeta() && i.getItemMeta().getDisplayName().contains("Throwing Axe")) {
			
			Item item = event.getItem();
			
			if (item.getVelocity().length() < 1) {
				
				event.setCancelled(false);
				
			} else if (item.getVelocity().length() > 1) {
				
				event.setCancelled(true);
				p.damage(4);
				item.remove();
				
			}
			
		} else {
			return;
		}
		
	}

}
