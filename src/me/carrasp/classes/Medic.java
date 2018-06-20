package me.carrasp.classes;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.rpg.Main;

public class Medic implements Listener, Runnable {
	
	private Main plugin;
	
	public Medic(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	public static ItemStack getBandage() {
		ItemStack bandage = new ItemStack(Material.PAPER);
		
		ItemMeta bandageMeta = bandage.getItemMeta();
		
		bandageMeta.setDisplayName(ChatColor.RED + ">> " + ChatColor.GRAY + "Bandage");
		
		bandage.setItemMeta(bandageMeta);
		
		return bandage;
	}
	
	@EventHandler
	private void giveBandage(final PlayerJoinEvent event) {
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				if (!(Classes.classes.containsKey(event.getPlayer().getUniqueId()))) return;
				
				if (!(Classes.classes.get(event.getPlayer().getUniqueId()) == 3)) return;
				
				event.getPlayer().getInventory().addItem(getBandage());
				
			}
			
		}, 60L);
		
	}
	
	@EventHandler
	private void bandageUse(PlayerInteractEntityEvent event) {
		
		if (!(Classes.classes.containsKey(event.getPlayer().getUniqueId()))) return;
		
		if (!(Classes.classes.get(event.getPlayer().getUniqueId()) == 3)) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName())) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bandage"))) return;
		
		if (!(event.getRightClicked() instanceof Player)) return;
		
		Player rightClicked = (Player) event.getRightClicked();
		
		rightClicked.setHealth(10000);
		
	}
	
	@EventHandler
	private void selfBandageUse(PlayerInteractEvent event) {
		
		if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
		
		if (!(Classes.classes.containsKey(event.getPlayer().getUniqueId()))) return;
		
		if (!(Classes.classes.get(event.getPlayer().getUniqueId()) == 3)) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().hasItemMeta())) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasDisplayName())) return;
		
		if (!(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Bandage"))) return;
		event.getPlayer().sendMessage("bandge");
		event.getPlayer().setHealth(event.getPlayer().getMaxHealth());
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
