package com.illumenmc.rpg;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.illumenmc.weapons.WeaponShop;

import me.carrasp.classes.Classes;
import me.carrasp.guilds.GuildManager;
import me.carrasp.races.Race;


public class ActionMenu implements Listener, Runnable {

	Hologram menu;
	
	//Plan: Shift + Right Click on player -> Spawns touch hologram -> Options: Request duel, View Profile
	
	private Main plugin;
	
	public ActionMenu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void spawnMenu(PlayerInteractEntityEvent event) {
		
		if (event.getPlayer().isSneaking()) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		if (!(event.getRightClicked() instanceof Player)) return;
		
		Player clicked = (Player) event.getRightClicked();
		
		//TODO
		//SPAWN MENU WITH OPTIONS
		
		Location holoLoc = clicked.getLocation().add(0, 2, 0);
		menu = HologramsAPI.createHologram(plugin, holoLoc);
		
		int level = Levels.getLevel(clicked);
		int coins = WeaponShop.getCoins(clicked);
		String guild = GuildManager.getPlayersGuild(clicked).getGuildName();
		String pClass = Classes.getPlayersClass(clicked);
		String race = Race.getPlayersRace(clicked);
		
		
		menu.appendTextLine(ChatColor.RED + "Level >> " + ChatColor.GRAY + level);
		menu.appendTextLine(ChatColor.RED + "Guild >> " + ChatColor.GRAY + guild);
		menu.appendTextLine(ChatColor.RED + "Class >> " + ChatColor.GRAY + pClass);
		menu.appendTextLine(ChatColor.RED + "Race >> " + ChatColor.GRAY + race);
		menu.appendTextLine(ChatColor.RED + "Coins >> " + ChatColor.GRAY + coins);
		
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				menu.delete();
				
			}
			
		} , 100L);
		} else {
			
			Player clicked = (Player) event.getRightClicked();

		
			
		}
	}
	
	public static Inventory getPlayerInteractMenu() {

		Inventory menu = Bukkit.createInventory(null, 9, ChatColor.RED + "Interact Menu");
		
		ItemStack addFriend = new ItemStack(Material.NAME_TAG);
		ItemMeta addFriendMeta = addFriend.getItemMeta();
		
		addFriendMeta.setDisplayName(ChatColor.RED + "Send Friend Request");
		addFriend.setItemMeta(addFriendMeta);
		
		menu.setItem(0, addFriend);
		
		ItemStack duel = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta duelMeta = duel.getItemMeta();
		
		duelMeta.setDisplayName(ChatColor.RED + "Send Duel Request");
		duel.setItemMeta(duelMeta);
		
		menu.setItem(0, duel);
		
		return menu;
	}
	
	@EventHandler
	public void cancelInteractClick(InventoryClickEvent event) {
		
		if (event.getInventory().getName().contains("Interact Menu")) {
			
			event.setCancelled(true);
			
			if (!(event.getWhoClicked() instanceof Player)) return;
			
			Player p = (Player) event.getWhoClicked();
			
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
