package me.carrasp.party;

import java.util.HashMap;
import java.util.Map;

import me.carrasp.races.Race;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitScheduler;

import com.illumenmc.rpg.Main;

public class Party implements Listener, Runnable {

	public static Map<String, Integer> chatChannel = new HashMap<String, Integer>();
	//1 = Global
	//2 = Race Chat
	//3 = Class Chat
	//4 = Near Chat(Radius of 100 Blocks)
	
	private Main plugin;
	
	public Party(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static String getChannel(Player p) {
		
		if (chatChannel.containsKey(p.getName())) {
			
			if (chatChannel.get(p.getName()) == 1) {
				return "Global";
			} else if (chatChannel.get(p.getName()) == 2) {
				return "Race Chat";
			} else if (chatChannel.get(p.getName()) == 3 ){
				return "Near Chat";
			} else if (chatChannel.get(p.getName()) == 4) {
				return "Party Chat";
				
			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
	}
	
	public static void setChannel(Player p, int channelNum) {
		
		if (chatChannel.containsKey(p.getName())) {
			
			chatChannel.remove(p.getName());
			chatChannel.put(p.getName(), channelNum);
			
		} else {
			chatChannel.put(p.getName(), channelNum);
		}
		
	}
	
	@EventHandler
	private void defaultChannel(PlayerJoinEvent event) {
	
		final Player p = event.getPlayer();
		
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		
		scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {

			@Override
			public void run() {
				
				if (!(chatChannel.containsKey(p.getName()))) {
					
					chatChannel.put(p.getName(), 1);
					
				} else {
					
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Current Chat Channel: " + getChannel(p) + "");
					
				}
				
			}
			
		});
		
	}
	
	@EventHandler
	private void setChat(AsyncPlayerChatEvent event) {
		
		Player p = event.getPlayer();
		
		if (getChannel(p) == null) return;
		
		if (getChannel(p).equalsIgnoreCase("Race Chat")) {
			
			for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				
				if (Race.races.get(p.getName()).equals(Race.races.get(online.getName()))) {
					
					online.sendMessage(ChatColor.RED + "[Race Chat] " + ChatColor.GRAY + "" + p.getName() + " >>" + ChatColor.RED + " " + event.getMessage() + "");
					event.setCancelled(true);
				} else {
					event.setCancelled(true);
				}
				
			}
			
		} else if (getChannel(p).equalsIgnoreCase("Near Chat")) {
			
			for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				
				double x1 = p.getLocation().getX();
				double x2 = online.getLocation().getX();
				double distance = x2 - x1;
				if (distance <= 100) {
					online.sendMessage(ChatColor.RED + "[Near Chat] " + ChatColor.GRAY + "" + p.getName() + " >>" + ChatColor.RED + " " + event.getMessage() + "");
					event.setCancelled(true);
				} else {
					event.setCancelled(true);
				}
				
			}
			
		} else {
			return;
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
