package me.carrasp.skills;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.rpg.Main;

public class SkillMenu implements Listener, CommandExecutor, Runnable {

	private String title;
	
	public static Map<UUID, Integer> skillPoints = new HashMap<UUID, Integer>();
	
	private Main plugin;
	
	private Player p;
	
	public SkillMenu(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	private Inventory getSkillMenu() {
		p.closeInventory();
		title = ChatColor.RED + "Skills: " + skillPoints.get(p.getUniqueId()).toString();
		
		Inventory skillMenu = Bukkit.createInventory(null, 9, title);
	
		if (p == null) return null;
	
		ItemStack strength = new ItemStack(Material.BOOK);
		ItemMeta sm = strength.getItemMeta();
		List<String> sl = new ArrayList<String>();
		sm.setDisplayName(ChatColor.RED + ">> " + ChatColor.RED + "Strength");
		sl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Current Strength Skill: " + Strength.strengthSkill.get(p.getUniqueId()));
		sl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Left Click To Spend 1 Skill Point");
		sl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Right Click To Spend 5 Skill Point");
		sm.setLore(sl);
		strength.setItemMeta(sm);
		
		skillMenu.addItem(strength);
		
		ItemStack defense = new ItemStack(Material.BOOK);
		ItemMeta dm = defense.getItemMeta();
		List<String> dl = new ArrayList<String>();
		dm.setDisplayName(ChatColor.RED + ">> " + ChatColor.RED + "Defense");
		dl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Current defense Skill: " + Defense.defenseSkill.get(p.getUniqueId()));
		dl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Left Click To Spend 1 Skill Point");
		dl.add(ChatColor.RED + "► " + ChatColor.GRAY + "Right Click To Spend 5 Skill Points");
		dm.setLore(dl);
		defense.setItemMeta(dm);
		
		skillMenu.addItem(defense);
		
		return skillMenu;
		
	}
		
		@EventHandler
		private void handleClicks(InventoryClickEvent event) {
			
			if (!(event.getInventory().getName().contains("Skills"))) return;
			
			if (!(event.getWhoClicked() instanceof Player)) return;
			
			BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
			
			event.setCancelled(true);
			
			final Player p = (Player) event.getWhoClicked();
			
			int slot = event.getSlot();
			
			switch (slot) {
			
			case 0: if (event.getClick().isLeftClick()) {
				
				if (SkillMenu.skillPoints.get(p.getUniqueId()) >= 1) {
					
					SkillMenu.skillPoints.put(p.getUniqueId(), SkillMenu.skillPoints.get(p.getUniqueId()) - 1);
					
					Strength.strengthSkill.put(p.getUniqueId(), Strength.strengthSkill.get(p.getUniqueId()) + 1);
					
					p.closeInventory();
					p.openInventory(getSkillMenu());
					
				} else {
					scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Enough Skill Points!");
							return;
							
						}
						
					}, 20L);
					
				}
				
			} else {
				
				if (SkillMenu.skillPoints.get(p.getUniqueId()) >= 5) {
					
					SkillMenu.skillPoints.put(p.getUniqueId(), SkillMenu.skillPoints.get(p.getUniqueId()) - 5);
					
					Strength.strengthSkill.put(p.getUniqueId(), Strength.strengthSkill.get(p.getUniqueId()) + 5);
					
					p.closeInventory();
					p.openInventory(getSkillMenu());
					
				} else {
					scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Enough Skill Points!");
							return;
							
						}
						
					}, 20L);
				}
				
			}
			break;
			
			case 1: if (event.getClick().isLeftClick()) {
				
				if (SkillMenu.skillPoints.get(p.getUniqueId()) >= 1) {
					
					SkillMenu.skillPoints.put(p.getUniqueId(), SkillMenu.skillPoints.get(p.getUniqueId()) - 1);
					
					Defense.defenseSkill.put(p.getUniqueId(), Defense.defenseSkill.get(p.getUniqueId()) + 1);
					
					p.closeInventory();
					p.openInventory(getSkillMenu());
					
				} else {
					scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Enough Skill Points!");
							return;
							
						}
						
					}, 20L);
				}
				
			} else {
				
				if (SkillMenu.skillPoints.get(p.getUniqueId()) >= 5) {
					
					SkillMenu.skillPoints.put(p.getUniqueId(), SkillMenu.skillPoints.get(p.getUniqueId()) - 5);
					
					Defense.defenseSkill.put(p.getUniqueId(), Defense.defenseSkill.get(p.getUniqueId()) + 5);
					
					p.closeInventory();
					p.openInventory(getSkillMenu());
					
				} else {
					scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

						@Override
						public void run() {
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Enough Skill Points!");
							return;
							
						}
						
					}, 20L);
				}
				
			}
			break;
			
			case 2: if (event.getClick().isLeftClick()) {
				
			} else {
				
			}
			break;
			
			}
			
		}

		@Override
		public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
			
			if (!(sender instanceof Player)) return false;
			
			Player p = (Player) sender;
			
			if (command.getName().equalsIgnoreCase("skill")) {
				this.p = p;
				p.openInventory(getSkillMenu());
				
			}
			
			return false;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
		
		
	
	
}
