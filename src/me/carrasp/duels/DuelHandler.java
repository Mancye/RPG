package me.carrasp.duels;

import org.bukkit.event.Listener;

import com.illumenmc.rpg.Main;

public class DuelHandler implements Listener {
	
	private Main plugin;
	
	public DuelHandler(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
}
