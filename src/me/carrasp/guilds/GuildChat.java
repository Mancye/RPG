package me.carrasp.guilds;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.illumenmc.rpg.Main;

public class GuildChat implements Listener {

	public static Map<UUID, Boolean> isInGuildChat = new HashMap<UUID, Boolean>();
	
	private Main plugin;
	
	public GuildChat(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void handleChat(AsyncPlayerChatEvent event) {
		
		if (!(GuildManager.playersGuilds.containsKey(event.getPlayer().getUniqueId()))) return;
		
		if (isInGuildChat.containsKey(event.getPlayer().getUniqueId())) {
			
			if (isInGuildChat.get(event.getPlayer().getUniqueId()) == true) {
				
				Player p = event.getPlayer();
				event.setCancelled(true);
			
				
				for (Player online : Bukkit.getServer().getOnlinePlayers()) {
					
					if (GuildManager.getPlayersGuild(p).hasMember(online)) {
						
						online.sendMessage(ChatColor.RED + "[Guild Chat] >> " + p.getDisplayName() + ChatColor.GRAY + ">> " + ChatColor.AQUA + event.getMessage());
						event.setCancelled(true);
					} else {
						event.setCancelled(true);
					}
					
				}
				
			} else {
				return;
			}
			
		} else {
			isInGuildChat.put(event.getPlayer().getUniqueId(), false);
		}
		
	}
	
}
