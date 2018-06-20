package me.carrasp.quests;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class GatherTenLogs implements Listener {
	//This is a test comment commit
	private Main plugin;
	
	private int coinReward;
	private String questName;
	private double xpReward;
	private int levelRequirement;
	private String questObjective;
	
	Map<Player, Integer> amtOfLogsGathered = new HashMap<Player, Integer>();
	public static Quest gatherTenLogs;
	
	public GatherTenLogs(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		xpReward = 10;
		
		coinReward = 20;
		
		questName = "Lumber Collection";
		
		levelRequirement = 1;
		
		questObjective = "Chop Down 10 Logs";
		
		gatherTenLogs = new Quest(questName, coinReward, xpReward, levelRequirement, questObjective);
	}
	
	private void endQuest(Player p) {
		gatherTenLogs.removePlayerFromQuest(p);
		WeaponShop.addCoins(p, coinReward);
		Levels.addExp(p, xpReward);
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Completed Quest: " + ChatColor.RED + ChatColor.UNDERLINE.toString() + questName);
		
	}
	
	@EventHandler
	private void onLogGather(BlockBreakEvent event) {
		
		Player p = event.getPlayer();
		Block blockBroken = event.getBlock();
		
		if (!(QuestManager.playersQuest.containsKey(p.getName()))) return;
		if (!(Quest.playersInQuests.contains(p.getUniqueId()))) return;
		
		if (gatherTenLogs == null) return;
		
		if (Quest.getPlayersQuest(p).equals(gatherTenLogs)) {
			
			if (blockBroken.getType().equals(Material.LOG) || blockBroken.getType().equals(Material.LOG_2)) {
				
				
				
				if (amtOfLogsGathered.containsKey(p)) {
					if (amtOfLogsGathered.get(p) <=  8) {
						amtOfLogsGathered.put(p, amtOfLogsGathered.get(p) + 1);
					} else if (amtOfLogsGathered.get(p) >= 9) {
						amtOfLogsGathered.remove(p);
						endQuest(p);
					}
				
				
				} else {
					amtOfLogsGathered.put(p, 1);
					
				}
				
			} else {
				return;
			}
			
		} else {
			return;
		}
		
	}
	
}
