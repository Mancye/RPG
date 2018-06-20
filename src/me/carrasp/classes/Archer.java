package me.carrasp.classes;

import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.illumenmc.rpg.Main;

public class Archer implements Listener {
	
	private Main plugin;
	
	public Archer(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	private void setDamage(EntityDamageByEntityEvent event) {
	
		
		if (!(event.getDamager() instanceof Arrow)) return;
		
		Arrow a = (Arrow) event.getDamager();
		
		if (!(a.getShooter() instanceof Player)) return;
		
		Player p = (Player) a.getShooter();
		
		if (!(Classes.classes.containsKey(p.getUniqueId()))) return;
		
		if (!(Classes.classes.get(p.getUniqueId()) == 2)) return;
		
		if (!(p.getInventory().getItemInMainHand().equals((new ItemStack(Material.BOW))))) return;
	
		
		event.setDamage(event.getDamage() * 1.5);

	}
	
}
