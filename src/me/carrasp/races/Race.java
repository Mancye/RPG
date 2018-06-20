package me.carrasp.races;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;

import me.carrasp.classes.Classes;
import me.carrasp.rpg.events.BoardChangeEvent;

public class Race implements Listener, Runnable {
	
	public static Map<UUID, Integer> races = new HashMap<UUID, Integer>();
	
	private Main plugin;
	
	public Race(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static Inventory getRaceGUI() {
		
		Inventory raceGUI = Bukkit.createInventory(null, 9, ChatColor.RED + "Race Selection Screen");
		
		ItemStack human = new ItemStack(Material.BOOK);
		ItemMeta humanMeta = human.getItemMeta();
		
		humanMeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Choose HUMAN Race");
		human.setItemMeta(humanMeta);
		
		ItemStack elf = new ItemStack(Material.BOOK);
		ItemMeta elfMeta = elf.getItemMeta();
		
		elfMeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Choose ELF Race");
		elf.setItemMeta(elfMeta);
		
		ItemStack orc = new ItemStack(Material.BOOK);
		ItemMeta orcMeta = orc.getItemMeta();
		
		orcMeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Choose ORC Race");
		orc.setItemMeta(orcMeta);
		
		raceGUI.addItem(human);
		raceGUI.addItem(elf);
		raceGUI.addItem(orc);
		
		return raceGUI;
		
	}
	
	@EventHandler
	private void openRaceMenu(final PlayerJoinEvent event) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				if (!(races.containsKey(event.getPlayer().getUniqueId()))) {
					event.getPlayer().openInventory(getRaceGUI());
					event.getPlayer().sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Choose A Race");
				} else {
					return;
				}
				
			}
			
		}, 40L);
		
	}
	
	@EventHandler
	private void onRaceChoose(InventoryClickEvent event) {
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		final Player player = (Player) event.getWhoClicked();
		
		if (event.getInventory().getName().contains("Race Selection Screen")) {
		
		event.setCancelled(true);
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		switch (event.getSlot()) {
		
		case 0: if (!(races.containsKey(player.getUniqueId()))) {
				races.put(player.getUniqueId(), 1); // 1 = HUMAN

				player.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Chosen The HUMAN Race!");
				player.closeInventory();
				
				Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(player));
				scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						//player.closeInventory();
						player.openInventory(Classes.getClassMenu());
						
					}
					
				}, 40L);
			
				
			break;
			
		
			
		} else {
			return;
		}
		
		case 1: if (!(races.containsKey(player.getUniqueId()))) {
			races.put(player.getUniqueId(), 2);// 2 = ELF;
			
				
				Levels.levels.put(player.getUniqueId(), 1);
				player.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Chosen The ELF Race!");
				player.closeInventory();
				Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(player));
				scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						//player.closeInventory();
						player.openInventory(Classes.getClassMenu());
						
					}
					
				}, 40L);
			 
		
			break;
			
			
		} else {
			return;
		}
		
		case 2: if (!(races.containsKey(player.getUniqueId()))) {
			races.put(player.getUniqueId(), 3); // 3 = ORC;
			
				
				Levels.levels.put(player.getUniqueId(), 1);
				player.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Chosen The ORC Race!");
				player.closeInventory();
				Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(player));
				scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

					@Override
					public void run() {
						//player.closeInventory();
						player.openInventory(Classes.getClassMenu());
						
					}
					
				}, 40L);
			 
	
			break;
			
			
		} else {
			return;
		}
		
		}
		
		} else {
			return;
		}
		
	}
	
	public static String asName(Integer person) {
		
		if (person == 1) {
			
			return "Human";
			
		} else {
			if (person == 2) {
				
				return "Elf";
				
			} else {
				
				if (person == 3) {
					
					return "Orc";
					
				}
				
			}
		}
		
		return "Exception with asName() Method";
		
	}
	
	public static String getPlayersRace(Player p) {
		
		if (!Race.races.containsKey(p.getUniqueId())) {
			return null;
		} else {
			return asName(Race.races.get(p.getUniqueId()));
		}
		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
