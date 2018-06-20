package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import me.carrasp.mobs.Tarantula;

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


public class TMenu implements Listener {

private Main plugin;

public TMenu(Main main) {
	
	plugin = main;
	plugin.getServer().getPluginManager().registerEvents(this, plugin);
	
}

public static Inventory getTMenu() {
	
	Inventory TMenu = Bukkit.createInventory(null, 36, ChatColor.RED + "T Configuration");
	
	ItemStack increasespawnchance = new ItemStack(Material.PAPER);
	ItemMeta increasespawnchancemeta = increasespawnchance.getItemMeta();
	List<String> increasespawnchancelore = new ArrayList<String>();
	
	increasespawnchancemeta.setDisplayName(ChatColor.RED + "Increase Spawn Chance");
	increasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + Tarantula.tSpawnChance);
	increasespawnchancemeta.setLore(increasespawnchancelore);
	increasespawnchance.setItemMeta(increasespawnchancemeta);
	
	TMenu.setItem(0, increasespawnchance);
	
	ItemStack decreasespawnchance = new ItemStack(Material.PAPER);
	ItemMeta decreasespawnchancemeta = decreasespawnchance.getItemMeta();
	List<String> decreasespawnchancelore = new ArrayList<String>();
	
	decreasespawnchancemeta.setDisplayName(ChatColor.RED + "Decrease Spawn Chance");
	decreasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + Tarantula.tSpawnChance);
	decreasespawnchancemeta.setLore(decreasespawnchancelore);
	decreasespawnchance.setItemMeta(decreasespawnchancemeta);
	
	TMenu.setItem(9, decreasespawnchance);
	
	ItemStack increasehealth = new ItemStack(Material.PAPER);
	ItemMeta increasehealthmeta = increasehealth.getItemMeta();
	List<String> increasehealthlore = new ArrayList<String>();
	
	increasehealthmeta.setDisplayName(ChatColor.RED + "Increase Health");
	increasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + Tarantula.tHealth);
	increasehealthmeta.setLore(increasehealthlore);
	increasehealth.setItemMeta(increasehealthmeta);
	
	TMenu.setItem(1, increasehealth);
	
	ItemStack decreasehealth = new ItemStack(Material.PAPER);
	ItemMeta decreasehealthmeta = decreasehealth.getItemMeta();
	List<String> decreasehealthlore = new ArrayList<String>();
	
	decreasehealthmeta.setDisplayName(ChatColor.RED + "Decrease Health");
	decreasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + Tarantula.tHealth);
	decreasehealthmeta.setLore(decreasehealthlore);
	decreasehealth.setItemMeta(decreasehealthmeta);
	
	TMenu.setItem(10, decreasehealth);
	
	return TMenu;
	
}

@EventHandler
private void handleClicks(InventoryClickEvent event) {
	
	if (!(event.getInventory().getName().contains("T Configuration"))) return;
	
	event.setCancelled(true);
	
	if (!(event.getWhoClicked() instanceof Player)) return;
	
	Player p = (Player) event.getWhoClicked();
	
	int slot = event.getSlot();
	
	switch (slot) {
	
	case 0: if (event.getClick().isLeftClick()) {
		
		Tarantula.tSpawnChance++;
		p.openInventory(getTMenu());
		
	} else {
		
		Tarantula.tSpawnChance = Tarantula.tSpawnChance + 5;
		p.openInventory(getTMenu());
	}
	break;
	
	case 9: if (event.getClick().isLeftClick()) {
		
		Tarantula.tSpawnChance--;
		p.openInventory(getTMenu());
		
	} else {
		
		Tarantula.tSpawnChance = Tarantula.tSpawnChance - 5;
		p.openInventory(getTMenu());
		
	}
	break;
	
	case 1: if (event.getClick().isLeftClick()) {
		
		Tarantula.tHealth++;
		p.openInventory(getTMenu());
		
	} else {
		
		Tarantula.tHealth = Tarantula.tHealth + 5;
		p.openInventory(getTMenu());
	}
	break;
	
	case 10: if (event.getClick().isLeftClick()) {
		
		Tarantula.tHealth--;
		p.openInventory(getTMenu());
		
	} else {
		
		Tarantula.tHealth = Tarantula.tHealth - 5;
		p.openInventory(getTMenu());
	}
	break;
	
	}
	
}
	
}
