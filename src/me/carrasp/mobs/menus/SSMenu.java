package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import me.carrasp.mobs.SkeletonSwordsman;

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

public class SSMenu implements Listener {
	
	private Main plugin;
	
	public SSMenu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static Inventory getSSMenu() {
		
		Inventory ssMenu = Bukkit.createInventory(null, 36, ChatColor.RED + "SS Configuration");
		
		ItemStack increasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta increasespawnchancemeta = increasespawnchance.getItemMeta();
		List<String> increasespawnchancelore = new ArrayList<String>();
		
		increasespawnchancemeta.setDisplayName(ChatColor.RED + "Increase Spawn Chance");
		increasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + SkeletonSwordsman.ssSpawnChance);
		increasespawnchancemeta.setLore(increasespawnchancelore);
		increasespawnchance.setItemMeta(increasespawnchancemeta);
		
		ssMenu.setItem(0, increasespawnchance);
		
		ItemStack decreasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta decreasespawnchancemeta = decreasespawnchance.getItemMeta();
		List<String> decreasespawnchancelore = new ArrayList<String>();
		
		decreasespawnchancemeta.setDisplayName(ChatColor.RED + "Decrease Spawn Chance");
		decreasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + SkeletonSwordsman.ssSpawnChance);
		decreasespawnchancemeta.setLore(decreasespawnchancelore);
		decreasespawnchance.setItemMeta(decreasespawnchancemeta);
		
		ssMenu.setItem(9, decreasespawnchance);
		
		ItemStack increasehealth = new ItemStack(Material.PAPER);
		ItemMeta increasehealthmeta = increasehealth.getItemMeta();
		List<String> increasehealthlore = new ArrayList<String>();
		
		increasehealthmeta.setDisplayName(ChatColor.RED + "Increase Health");
		increasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + SkeletonSwordsman.ssHealth);
		increasehealthmeta.setLore(increasehealthlore);
		increasehealth.setItemMeta(increasehealthmeta);
		
		ssMenu.setItem(1, increasehealth);
		
		ItemStack decreasehealth = new ItemStack(Material.PAPER);
		ItemMeta decreasehealthmeta = decreasehealth.getItemMeta();
		List<String> decreasehealthlore = new ArrayList<String>();
		
		decreasehealthmeta.setDisplayName(ChatColor.RED + "Decrease Health");
		decreasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + SkeletonSwordsman.ssHealth);
		decreasehealthmeta.setLore(decreasehealthlore);
		decreasehealth.setItemMeta(decreasehealthmeta);
		
		ssMenu.setItem(10, decreasehealth);
		
		return ssMenu;
		
	}
	
	@EventHandler
	private void handleClicks(InventoryClickEvent event) {
		
		if (!(event.getInventory().getName().contains("SS Configuration"))) return;
		
		event.setCancelled(true);
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: if (event.getClick().isLeftClick()) {
			
			SkeletonSwordsman.ssSpawnChance++;
			p.openInventory(getSSMenu());
			
		} else {
			
			SkeletonSwordsman.ssSpawnChance = SkeletonSwordsman.ssSpawnChance + 5;
			p.openInventory(getSSMenu());
		}
		break;
		
		case 9: if (event.getClick().isLeftClick()) {
			
			SkeletonSwordsman.ssSpawnChance--;
			p.openInventory(getSSMenu());
			
		} else {
			
			SkeletonSwordsman.ssSpawnChance = SkeletonSwordsman.ssSpawnChance - 5;
			p.openInventory(getSSMenu());
			
		}
		break;
		
		case 1: if (event.getClick().isLeftClick()) {
			
			SkeletonSwordsman.ssHealth++;
			p.openInventory(getSSMenu());
			
		} else {
			
			SkeletonSwordsman.ssHealth = SkeletonSwordsman.ssHealth + 5;
			p.openInventory(getSSMenu());
		}
		break;
		
		case 10: if (event.getClick().isLeftClick()) {
			
			SkeletonSwordsman.ssHealth--;
			p.openInventory(getSSMenu());
			
		} else {
			
			SkeletonSwordsman.ssHealth = SkeletonSwordsman.ssHealth - 5;
			p.openInventory(getSSMenu());
		}
		break;
		
		}
		
	}

}
