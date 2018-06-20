package me.carrasp.classes;

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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Main;

import me.carrasp.rpg.events.BoardChangeEvent;

public class Classes implements Listener {
	
	private Main plugin;
	
	public static Map<UUID, Integer> classes = new HashMap<UUID, Integer>();
	
	public Classes(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static Inventory getClassMenu() {
		
		Inventory classMenu = Bukkit.createInventory(null, 9, ChatColor.RED + "Choose Class");
		//1
		ItemStack tank = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta tankMeta = tank.getItemMeta();
		List<String> tankLore = new ArrayList<String>();
		
		tankMeta.setDisplayName(ChatColor.RED + "Tank Class");
		
		tankLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Good For Close Combat With Melee Weapons");
		tankLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Extra Health");
		tankLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Does  10% Less Damage");
		tankLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Can't Use Bow & Arrow");
		
		tankMeta.setLore(tankLore);
		tank.setItemMeta(tankMeta);
		classMenu.addItem(tank);
		
		//2
		ItemStack archer = new ItemStack(Material.BOW);
		ItemMeta archerMeta = archer.getItemMeta();
		List<String> archerLore = new ArrayList<String>();
		
		archerMeta.setDisplayName(ChatColor.RED + "Archer Class");
		
		archerLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Best For Attacking From Distance");
		archerLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " 25% More Damage With Bow & Arrow");
		archerLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Reduced Health");
		
		archerMeta.setLore(archerLore);
		archer.setItemMeta(archerMeta);
		classMenu.addItem(archer);
		
		//3
		ItemStack medic = new ItemStack(Material.POTION);
		ItemMeta medicMeta = medic.getItemMeta();
		List<String> medicLore = new ArrayList<String>();
		
		medicMeta.setDisplayName(ChatColor.RED + "Medic Class");
		
		medicLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Essential To The Survival Of Any Group");
		medicLore.add(ChatColor.RED + "►" + ChatColor.GRAY + " Can Use Bandages On Other Players");
		
		medicMeta.setLore(medicLore);
		medic.setItemMeta(medicMeta);
		classMenu.addItem(medic);
		
		return classMenu;
		
	}
	
	@EventHandler
	private void handleClassClick(InventoryClickEvent event) {
		if (!(event.getInventory().getName().contains("Choose Class"))) return;
		
		event.setCancelled(true);
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		if (classes.containsKey(p.getUniqueId())) {
		p.closeInventory();
		return;
		}
		
		switch (slot) {
		
		case 0: classes.put(p.getUniqueId(), 1);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Are Now A Tank!");
		p.closeInventory();
		p.setMaxHealth(p.getMaxHealth() * 2);
		p.setHealth(p.getMaxHealth());
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
		break;
		
		case 1: classes.put(p.getUniqueId(), 2);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Are Now An Archer!");
		p.closeInventory();
		p.setMaxHealth(p.getMaxHealth() * .7);
		p.setHealth(p.getMaxHealth());
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
		break;
		
		case 2: classes.put(p.getUniqueId(), 3);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Are Now A Medic!");
		p.closeInventory();
		p.getInventory().addItem(Medic.getBandage());
		Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(p));
		break;
			
		}
	}
	
	public static String asName(Integer person) {
		
		if (person == 1) {
			
			return "Tank";
			
		} else {
			if (person == 2) {
				
				return "Archer";
				
			} else {
				
				if (person == 3) {
					
					return "Medic";
					
				}
				
			}
		}
		
		return "Exception with asName() Method";
		
	}
	
	public static String getPlayersClass(Player p) {
		
		if (!(classes.containsKey(p.getUniqueId()))) {
			return null;
		} else if (classes.containsKey(p.getUniqueId())) {
			
			return asName(classes.get(p.getUniqueId()));
			
		} else {
			return null;
		}
		
		
		
	}

}
