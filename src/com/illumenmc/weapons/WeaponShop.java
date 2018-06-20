package com.illumenmc.weapons;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import me.carrasp.rpg.events.BoardChangeEvent;

public class WeaponShop implements Listener, Runnable {
	
	public static Map<UUID, Integer> coins = new HashMap<UUID, Integer>();
	
	String buyMessage = ChatColor.RED + ">> " + ChatColor.GRAY + "Transaction Completed Successfully!";
	String notEnoughMessage = ChatColor.RED + ">> " + ChatColor.GRAY + "You Do Not Have Enough Coins Or You Do Not Meet The Required Level.";
	String invFullMessage = ChatColor.RED + ">> " + ChatColor.GRAY + "Your Inventory Is Full! All Coins Restored To Account.";
	
	private Main plugin;
	
	public WeaponShop(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	public static Integer getCoins(Player p) {
		
		if (!(coins.containsKey(p.getUniqueId()))) return null;
		
		return coins.get(p.getUniqueId());
		
	}
	
	public static void addCoins(Player p, int amount) {
		
		if (!(coins.containsKey(p.getUniqueId()))) return;
		
		coins.put(p.getUniqueId(), coins.get(p.getUniqueId()) + amount);
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
	}
	
	public static void removeCoins(Player p, int amount) {
		
		if (!(coins.containsKey(p.getUniqueId()))) return;
		
		coins.put(p.getUniqueId(), coins.get(p.getUniqueId()) - amount);
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
	}
	
	public static void setCoins(Player p, int amount) {
		
		if (!(coins.containsKey(p.getUniqueId()))) return;
		
		coins.put(p.getUniqueId(), amount);
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
	}
	
	@EventHandler
	private void defaultCoins(final PlayerJoinEvent event) {
		
		if (!(coins.containsKey(event.getPlayer().getUniqueId()))) {
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

				@Override
				public void run() {
					coins.put(event.getPlayer().getUniqueId(), 0);
					Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(event.getPlayer()));
				}
				
			}, 20L);
			
		} else {
			return;
		}
		
		
	}
	
	public static Inventory getWeaponShop() {
		
		Inventory weaponshop = Bukkit.createInventory(null, 54, ChatColor.RED + ">> " + ChatColor.GRAY + "Weapon Shop " + ChatColor.RED + "<<");
		
		ItemStack throwingaxe = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta throwingaxemeta = throwingaxe.getItemMeta();
		List<String> throwingaxelore = new ArrayList<String>();
		throwingaxemeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Throwing Axe");
		throwingaxelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Price: " + ChatColor.RED + "100 Coins");
		throwingaxelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Type: " + ChatColor.RED + "Ranged");
		throwingaxelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: " + ChatColor.RED + "3 Hearts");
		throwingaxelore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: " + ChatColor.RED + "3");
		throwingaxemeta.setLore(throwingaxelore);
		throwingaxe.setItemMeta(throwingaxemeta);
		weaponshop.addItem(throwingaxe);
		
		ItemStack dagger = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta daggermeta = dagger.getItemMeta();
		List<String> daggerlore = new ArrayList<String>();
		
		daggermeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Dagger");
		daggerlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Price: " + ChatColor.RED + "150 Coins");
		daggerlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Type: " + ChatColor.RED + "Melee");
		daggerlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: " + ChatColor.RED + "5 Hearts From Within 1.5 Blocks, 2 Hearts From Any Farther");
		daggerlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: " + ChatColor.RED + "3");
		daggermeta.setLore(daggerlore);
		dagger.setItemMeta(daggermeta);
		weaponshop.addItem(dagger);
		
		ItemStack spear = new ItemStack(Material.BLAZE_ROD);
		ItemMeta spearmeta = spear.getItemMeta();
		List<String> spearlore = new ArrayList<String>();
		
		spearmeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Spear");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Price: " + ChatColor.RED + "1000");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Type: " + ChatColor.RED + "Melee");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Damage: " + ChatColor.RED + "10 Hearts");
		spearlore.add(ChatColor.RED + ">> " + ChatColor.GRAY + "Required Level: " + ChatColor.RED + "15");
		spearmeta.setLore(spearlore);
		spear.setItemMeta(spearmeta);
		weaponshop.addItem(spear);
		
		return weaponshop;
	}
	
	@EventHandler
	private void onBuy(InventoryClickEvent event) {
		
		if (event.getInventory().getName().contains("Weapon Shop")) {
			
			event.setCancelled(true);
			
			if (!(event.getWhoClicked() instanceof Player)) return;
			
			Player p = (Player) event.getWhoClicked();
			
			int slot = event.getSlot();
			
			switch (slot) {
			
			case 0: if (getCoins(p) >= 100 && Levels.getLevel(p) >= 3) {
				
				p.getInventory().addItem(ThrowingAxe.getThrowingAxe());
				if (p.getInventory().contains(ThrowingAxe.getThrowingAxe())) {
					
					p.sendMessage(buyMessage);
					removeCoins(p, 100);
					Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
				} else {
					
					p.sendMessage(invFullMessage);
					
				}
				
			} else {
				
				p.sendMessage(notEnoughMessage);
				
			}
			break;
			
			case 1: if (getCoins(p) >= 150 && Levels.getLevel(p) >= 3) {
				
				p.getInventory().addItem(Dagger.getDagger());
				if (p.getInventory().contains(Dagger.getDagger())) {
					
					p.sendMessage(buyMessage);
					removeCoins(p, 150);
					Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
				} else {
					
					p.sendMessage(invFullMessage);
					
				}
				
			} else {
				
				p.sendMessage(notEnoughMessage);
				
			}
			break;
			
			case 2: if (getCoins(p) >= 1000 && Levels.getLevel(p) >= 15) {
				
				p.getInventory().addItem(Spear.getSpear());
				
				if (p.getInventory().contains(Spear.getSpear())) {
					
					
					
				} else {
					
					p.sendMessage(invFullMessage);
					return;
					
				}
				
			} else {
				
				p.sendMessage(notEnoughMessage);
				return;
				
			}
			
			}
			
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
