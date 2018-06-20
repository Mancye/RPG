package me.carrasp.spells;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import gyurix.spigotlib.Main;

public class Heal implements Listener {
	
	private Main plugin;
	
	private String name;
	
	private int level;
	
	private ItemStack icon;
	
	private int slot;
	
	private Spell heal;
	
	public Heal(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		name = "Heal";
		
		level = 5;
		
		icon = new ItemStack(Material.POTION);
		
		slot = 0;
		
		heal  = new Spell(name, icon, level, slot);
		
	}
	
	
	

}
