package me.carrasp.unlocks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;

import me.carrasp.rpg.events.ArmorEquipEvent;

public class ArmorUnlocks implements Listener {
	
	private Main plugin;
	
	public ArmorUnlocks(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void onEquip(ArmorEquipEvent event) {
		
		Player p = event.getPlayer();
		
		int level = Levels.getLevel(p);
		
		if (level  >= 1 && level <= 9) {
		
		 if (!(event.getNewArmorPiece().getType().equals(Material.LEATHER_BOOTS) || 
					event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) ||
					event.getNewArmorPiece().getType().equals(Material.LEATHER_CHESTPLATE) ||
					event.getNewArmorPiece().getType().equals(Material.LEATHER_HELMET))) {
			event.getNewArmorPiece().setType(Material.AIR);
			event.setCancelled(true);
			p.sendMessage("Level 1 check");
		}
		
		} else if (level >= 10 && level <= 19) {
		
		 if (!(event.getNewArmorPiece().getType().equals(Material.LEATHER_BOOTS) || 
				event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) ||
				event.getNewArmorPiece().getType().equals(Material.LEATHER_CHESTPLATE) ||
				event.getNewArmorPiece().getType().equals(Material.LEATHER_HELMET) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_BOOTS) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_LEGGINGS) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_CHESTPLATE) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_HELMET))) {
		event.getNewArmorPiece().setType(Material.AIR);
		event.setCancelled(true);
		p.sendMessage("Level 10 check");
		
	}
		} else if (level >=  20 && level <= 29) {
	
		 if (!(event.getNewArmorPiece().getType().equals(Material.LEATHER_BOOTS) || 
				event.getNewArmorPiece().getType().equals(Material.LEATHER_LEGGINGS) ||
				event.getNewArmorPiece().getType().equals(Material.LEATHER_CHESTPLATE) ||
				event.getNewArmorPiece().getType().equals(Material.LEATHER_HELMET) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_BOOTS) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_LEGGINGS) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_CHESTPLATE) ||
				event.getNewArmorPiece().getType().equals(Material.IRON_HELMET) ||
				event.getNewArmorPiece().getType().equals(Material.GOLD_BOOTS) ||
				event.getNewArmorPiece().getType().equals(Material.GOLD_LEGGINGS) ||
				event.getNewArmorPiece().getType().equals(Material.GOLD_CHESTPLATE) ||
				event.getNewArmorPiece().getType().equals(Material.GOLD_CHESTPLATE))) {
		event.getNewArmorPiece().setType(Material.AIR);
		event.setCancelled(true);
		p.sendMessage("Level 20 check");
	}
		} else if (level >= 30) {
			p.sendMessage("Level 30 check");
			return;
		}
	
		
		
		
		
		
	}

}
