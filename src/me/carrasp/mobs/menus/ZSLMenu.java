package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import me.carrasp.mobs.ZombieSoldier;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Main;

public class ZSLMenu implements Listener {
	
	private Main plugin; 
	
	public ZSLMenu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static Inventory getZSLMenu() {
		
		Inventory zslMenu = Bukkit.createInventory(null, 36, ChatColor.RED + "ZSL Configuration");
		
		ItemStack increasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta increasespawnchancemeta = increasespawnchance.getItemMeta();
		List<String> increasespawnchancelore = new ArrayList<String>();
		
		increasespawnchancemeta.setDisplayName(ChatColor.RED + "Increase Spawn Chance");
		increasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + ZombieSoldier.zsSpawnChance);
		increasespawnchancemeta.setLore(increasespawnchancelore);
		increasespawnchance.setItemMeta(increasespawnchancemeta);
		
		zslMenu.setItem(0, increasespawnchance);
		
		ItemStack decreasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta decreasespawnchancemeta = decreasespawnchance.getItemMeta();
		List<String> decreasespawnchancelore = new ArrayList<String>();
		
		decreasespawnchancemeta.setDisplayName(ChatColor.RED + "Decrease Spawn Chance");
		decreasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + ZombieSoldier.zsSpawnChance);
		decreasespawnchancemeta.setLore(decreasespawnchancelore);
		decreasespawnchance.setItemMeta(decreasespawnchancemeta);
		
		zslMenu.setItem(9, decreasespawnchance);
		
		ItemStack increasehealth = new ItemStack(Material.PAPER);
		ItemMeta increasehealthmeta = increasehealth.getItemMeta();
		List<String> increasehealthlore = new ArrayList<String>();
		
		increasehealthmeta.setDisplayName(ChatColor.RED + "Increase Health");
		increasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + ZombieSoldier.zsHealth);
		increasehealthmeta.setLore(increasehealthlore);
		increasehealth.setItemMeta(increasehealthmeta);
		
		zslMenu.setItem(1, increasehealth);
		
		ItemStack decreasehealth = new ItemStack(Material.PAPER);
		ItemMeta decreasehealthmeta = decreasehealth.getItemMeta();
		List<String> decreasehealthlore = new ArrayList<String>();
		
		decreasehealthmeta.setDisplayName(ChatColor.RED + "Decrease Health");
		decreasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + ZombieSoldier.zsHealth);
		decreasehealthmeta.setLore(decreasehealthlore);
		decreasehealth.setItemMeta(decreasehealthmeta);
		
		zslMenu.setItem(10, decreasehealth);
		
		return zslMenu;
		
	}
	
	@EventHandler
	private void handleClicks(InventoryClickEvent event) {
		
		if (!(event.getInventory().getName().contains("ZSL Configuration"))) return;
		
		event.setCancelled(true);
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: if (event.getClick().isLeftClick()) {
			
			ZombieSoldier.zsSpawnChance++;
			p.openInventory(getZSLMenu());
			
		} else {
			
			ZombieSoldier.zsSpawnChance = ZombieSoldier.zsSpawnChance + 5;
			p.openInventory(getZSLMenu());
		}
		break;
		
		case 9: if (event.getClick().isLeftClick()) {
			
			ZombieSoldier.zsSpawnChance--;
			p.openInventory(getZSLMenu());
			
		} else {
			
			ZombieSoldier.zsSpawnChance = ZombieSoldier.zsSpawnChance - 5;
			p.openInventory(getZSLMenu());
			
		}
		break;
		
		case 1: if (event.getClick().isLeftClick()) {
			
			ZombieSoldier.zsHealth++;
			p.openInventory(getZSLMenu());
			
		} else {
			
			ZombieSoldier.zsHealth = ZombieSoldier.zsHealth + 5;
			p.openInventory(getZSLMenu());
		}
		break;
		
		case 10: if (event.getClick().isLeftClick()) {
			
			ZombieSoldier.zsHealth--;
			p.openInventory(getZSLMenu());
			
		} else {
			
			ZombieSoldier.zsHealth = ZombieSoldier.zsHealth - 5;
			p.openInventory(getZSLMenu());
		}
		break;
		
		}
		
	}
	

}
