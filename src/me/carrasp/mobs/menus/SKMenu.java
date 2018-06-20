package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import me.carrasp.mobs.SkeletonKing;

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

public class SKMenu implements Listener {
	
	private Main plugin;
	
	public SKMenu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static Inventory getSKMenu() {
		
		Inventory skMenu = Bukkit.createInventory(null, 36, ChatColor.RED + "SK Configuration");
		
		ItemStack increasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta increasespawnchancemeta = increasespawnchance.getItemMeta();
		List<String> increasespawnchancelore = new ArrayList<String>();
		
		increasespawnchancemeta.setDisplayName(ChatColor.RED + "Increase Spawn Chance");
		increasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + SkeletonKing.skSpawnChance);
		increasespawnchancemeta.setLore(increasespawnchancelore);
		increasespawnchance.setItemMeta(increasespawnchancemeta);
		
		skMenu.setItem(0, increasespawnchance);
		
		ItemStack decreasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta decreasespawnchancemeta = decreasespawnchance.getItemMeta();
		List<String> decreasespawnchancelore = new ArrayList<String>();
		
		decreasespawnchancemeta.setDisplayName(ChatColor.RED + "Decrease Spawn Chance");
		decreasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + SkeletonKing.skSpawnChance);
		decreasespawnchancemeta.setLore(decreasespawnchancelore);
		decreasespawnchance.setItemMeta(decreasespawnchancemeta);
		
		skMenu.setItem(9, decreasespawnchance);
		
		ItemStack increasehealth = new ItemStack(Material.PAPER);
		ItemMeta increasehealthmeta = increasehealth.getItemMeta();
		List<String> increasehealthlore = new ArrayList<String>();
		
		increasehealthmeta.setDisplayName(ChatColor.RED + "Increase Health");
		increasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + SkeletonKing.skHealth);
		increasehealthmeta.setLore(increasehealthlore);
		increasehealth.setItemMeta(increasehealthmeta);
		
		skMenu.setItem(1, increasehealth);
		
		ItemStack decreasehealth = new ItemStack(Material.PAPER);
		ItemMeta decreasehealthmeta = decreasehealth.getItemMeta();
		List<String> decreasehealthlore = new ArrayList<String>();
		
		decreasehealthmeta.setDisplayName(ChatColor.RED + "Decrease Health");
		decreasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + SkeletonKing.skHealth);
		decreasehealthmeta.setLore(decreasehealthlore);
		decreasehealth.setItemMeta(decreasehealthmeta);
		
		skMenu.setItem(10, decreasehealth);
		
		return skMenu;
		
	}
	
	@EventHandler
	private void handleClicks(InventoryClickEvent event) {
		
		if (!(event.getInventory().getName().contains("SK Configuration"))) return;
		
		event.setCancelled(true);
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: if (event.getClick().isLeftClick()) {
			
			SkeletonKing.skSpawnChance++;
			p.openInventory(getSKMenu());
			
		} else {
			
			SkeletonKing.skSpawnChance = SkeletonKing.skSpawnChance + 5;
			p.openInventory(getSKMenu());
		}
		break;
		
		case 9: if (event.getClick().isLeftClick()) {
			
			SkeletonKing.skSpawnChance--;
			p.openInventory(getSKMenu());
			
		} else {
			
			SkeletonKing.skSpawnChance = SkeletonKing.skSpawnChance - 5;
			p.openInventory(getSKMenu());
			
		}
		break;
		
		case 1: if (event.getClick().isLeftClick()) {
			
			SkeletonKing.skHealth++;
			p.openInventory(getSKMenu());
			
		} else {
			
			SkeletonKing.skHealth = SkeletonKing.skHealth + 5;
			p.openInventory(getSKMenu());
		}
		break;
		
		case 10: if (event.getClick().isLeftClick()) {
			
			SkeletonKing.skHealth--;
			p.openInventory(getSKMenu());
			
		} else {
			
			SkeletonKing.skHealth = SkeletonKing.skHealth - 5;
			p.openInventory(getSKMenu());
		}
		break;
		
		}
		
	}

}
