package me.carrasp.spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;

public class SpellBar implements Listener {

	private Map<UUID, PlayerInventory> inventories = new HashMap<UUID, PlayerInventory>();
	
	private Map<UUID, Boolean> isInSpellBar = new HashMap<UUID, Boolean>();
	
	/*public static Inventory getSpellBar() {
		
		//Inventory spellBar = Bukkit.createInventory(null, null)
		
	}*/
	
	@EventHandler
	private void openBar(InventoryOpenEvent event) {
		
		if (!(event.getPlayer() instanceof Player)) return;
		
		Player p = (Player) event.getPlayer();
		
		if (!(p.isSneaking())) return;
		
		PlayerInventory inv = p.getInventory();
		
		inventories.put(p.getUniqueId(), inv);
		
		p.getInventory().clear();
		
		
		
	}
	
	@EventHandler
	private void toggleSpellMenu(PlayerInteractEvent event) {
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			if (event.getPlayer().isSneaking()) {
				
				Player p = event.getPlayer();
				
				if (!isInSpellBar.containsKey(p.getUniqueId())) {
					
					isInSpellBar.put(p.getUniqueId(), true);
					
				}
				
				if (isInSpellBar.get(p.getUniqueId()) == true) {
					isInSpellBar.put(p.getUniqueId(), false);
					p.getInventory().clear();
					
				} else {
					isInSpellBar.put(p.getUniqueId(), true);
				}
				
			} else {
				return;
			}
			
		} else {
			return;
		}
		
	}
	
}
