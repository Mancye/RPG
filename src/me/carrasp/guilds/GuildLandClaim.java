package me.carrasp.guilds;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.illumenmc.rpg.Main;

public class GuildLandClaim implements Listener {

	private Main plugin;
	
	public GuildLandClaim(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	@EventHandler
	private void claimBlock(BlockPlaceEvent event) {
		
		Player p = event.getPlayer();
		
		Block b = event.getBlock();
		
		b.setMetadata(p.getUniqueId().toString(), new FixedMetadataValue(plugin, "metadats"));
		
	}
	
	
	@EventHandler
	private void noBlockHit(PlayerInteractEvent event) {
		
		if (!(event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
		
		if (event.getClickedBlock().hasMetadata(event.getPlayer().getUniqueId().toString())) {
			
			return;
			
		} else {
			
			event.setCancelled(true);
			
		}
		
	}
	
	@EventHandler
	private void noBreak(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		
		Block b = event.getBlock();
		
		if (b.hasMetadata(p.getUniqueId().toString())) {
			
			return;
			
		} else {
			event.setCancelled(true);
		}
		
	}

}
