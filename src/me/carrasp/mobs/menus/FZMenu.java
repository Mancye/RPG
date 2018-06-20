package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import me.carrasp.mobs.FastZombie;

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

public class FZMenu implements Listener {
	
	private Main plugin;
	
	public FZMenu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static Inventory getFZMenu() {
		
		Inventory fzMenu = Bukkit.createInventory(null, 18, ChatColor.RED + "FZ Configuration");
		
		ItemStack increasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta increasespawnchancemeta = increasespawnchance.getItemMeta();
		List<String> increasespawnchancelore = new ArrayList<String>();
		
		increasespawnchancemeta.setDisplayName(ChatColor.RED + "Increase Spawn Chance");
		increasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + FastZombie.fzSpawnChance);
		increasespawnchancemeta.setLore(increasespawnchancelore);
		increasespawnchance.setItemMeta(increasespawnchancemeta);
		
		fzMenu.setItem(0, increasespawnchance);
		
		ItemStack decreasespawnchance = new ItemStack(Material.PAPER);
		ItemMeta decreasespawnchancemeta = decreasespawnchance.getItemMeta();
		List<String> decreasespawnchancelore = new ArrayList<String>();
		
		decreasespawnchancemeta.setDisplayName(ChatColor.RED + "Decrease Spawn Chance");
		decreasespawnchancelore.add(ChatColor.GRAY + "Current Spawn Chance: " + ChatColor.RED + FastZombie.fzSpawnChance);
		decreasespawnchancemeta.setLore(decreasespawnchancelore);
		decreasespawnchance.setItemMeta(decreasespawnchancemeta);
		
		fzMenu.setItem(9, decreasespawnchance);
		
		ItemStack increasehealth = new ItemStack(Material.PAPER);
		ItemMeta increasehealthmeta = increasehealth.getItemMeta();
		List<String> increasehealthlore = new ArrayList<String>();
		
		increasehealthmeta.setDisplayName(ChatColor.RED + "Increase Health");
		increasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + FastZombie.fzHealth);
		increasehealthmeta.setLore(increasehealthlore);
		increasehealth.setItemMeta(increasehealthmeta);
		
		fzMenu.setItem(1, increasehealth);
		
		ItemStack decreasehealth = new ItemStack(Material.PAPER);
		ItemMeta decreasehealthmeta = decreasehealth.getItemMeta();
		List<String> decreasehealthlore = new ArrayList<String>();
		
		decreasehealthmeta.setDisplayName(ChatColor.RED + "Decrease Health");
		decreasehealthlore.add(ChatColor.GRAY + "Current Health: " + ChatColor.RED + FastZombie.fzHealth);
		decreasehealthmeta.setLore(decreasehealthlore);
		decreasehealth.setItemMeta(decreasehealthmeta);
		
		fzMenu.setItem(10, decreasehealth);
		
		return fzMenu;
		
	}
	
	@EventHandler
	private void handleClicks(InventoryClickEvent event) {
		
		if (!(event.getInventory().getName().contains("FZ Configuration"))) return;
		
		event.setCancelled(true);
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: if (event.getClick().isLeftClick()) {
			
			FastZombie.fzSpawnChance++;
			p.openInventory(getFZMenu());
			
		} else {
			
			FastZombie.fzSpawnChance = FastZombie.fzSpawnChance + 5;
			p.openInventory(getFZMenu());
		}
		break;
		
		case 9: if (event.getClick().isLeftClick()) {
			
			FastZombie.fzSpawnChance--;
			p.openInventory(getFZMenu());
			
		} else {
			
			FastZombie.fzSpawnChance = FastZombie.fzSpawnChance - 5;
			p.openInventory(getFZMenu());
			
		}
		break;
		
		case 1: if (event.getClick().isLeftClick()) {
			
			FastZombie.fzHealth++;
			p.openInventory(getFZMenu());
			
		} else {
			
			FastZombie.fzHealth = FastZombie.fzHealth + 5;
			p.openInventory(getFZMenu());
		}
		break;
		
		case 10: if (event.getClick().isLeftClick()) {
			
			FastZombie.fzHealth--;
			p.openInventory(getFZMenu());
			
		} else {
			
			FastZombie.fzHealth = FastZombie.fzHealth - 5;
			p.openInventory(getFZMenu());
		}
		break;
		
		}
		
	}

}
