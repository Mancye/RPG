package me.carrasp.quests;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.illumenmc.rpg.Main;

import me.carrasp.rpg.events.BoardChangeEvent;

public class QuestSign implements Listener {
	
	private Main plugin;
	
	public QuestSign(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	private void signPlace(SignChangeEvent event) {
		
		Player p = event.getPlayer();
		
		if (p.hasPermission("rpg.admin")) {
			
			if (event.getLine(0).equalsIgnoreCase("Join Quest")) {
				
				event.setLine(0, ChatColor.BLUE + "Join Quest");
				
			}
			
		}
		
	}

	@EventHandler
	private void signClick(PlayerInteractEvent event) {
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			Block block = event.getClickedBlock();
			
			if (block.getType().equals(Material.WALL_SIGN) || block.getType().equals(Material.SIGN) || block.getType().equals(Material.SIGN_POST)) {
				
				Sign sign = (Sign) block.getState();
				
				if (sign.getLine(0).contains("Join Quest")) {
					
					if (Quest.getQuestFromName(sign.getLine(1)) != null) {
						
						Quest quest = Quest.getQuestFromName(sign.getLine(1));
						
						quest.addPlayerToQuest(event.getPlayer(), quest);
						
						if (quest.isPlayerInQuest(event.getPlayer())) {
							event.getPlayer().sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Quest Joined! View Active Quest With " + ChatColor.RED + "/questlog");
							Bukkit.getServer().getPluginManager().callEvent(new BoardChangeEvent(event.getPlayer()));
						} else {
							event.getPlayer().sendMessage("Shit got fucked!");
						}
						
					}
					
				} else {
					return;
				}
				
			} else {
				return;
			}
			
		} else {
			return;
		}
		
	}

}
