package me.carrasp.classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.illumenmc.rpg.Main;

public class Tank implements Listener {
	
	private Main plugin;
	
	public Tank(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void setDamage(EntityDamageByEntityEvent event) {
		
		if (!(event.getDamager() instanceof Player)) return;
		
		Player p = (Player) event.getDamager();
		
		if (!(Classes.classes.containsKey(p.getUniqueId()))) return;
		
		if (!(Classes.classes.get(p.getUniqueId()) == 1)) return;
		
		event.setDamage(event.getDamage() * .9);
		
	}
	
	@EventHandler
	private void useBow(PlayerInteractEvent event) {
		 if (!(Classes.classes.containsKey(event.getPlayer().getUniqueId()))) return;
		 if (!(Classes.classes.get(event.getPlayer().getUniqueId()) == 1)) return;
		 
		if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
					if(event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.BOW))
					{
						event.setCancelled(true);
					}
		
		}
	}
	
}
