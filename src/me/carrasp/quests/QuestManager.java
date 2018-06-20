package me.carrasp.quests;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.illumenmc.rpg.Main;

import net.citizensnpcs.api.npc.NPC;

public class QuestManager implements CommandExecutor, Listener {

	private Main plugin;
	
	public static Map<NPC, Quest> questGiversQuest = new HashMap<NPC, Quest>();
	
	public static Map<String, Quest> playersQuest = new HashMap<String, Quest>();
	
	public static Map<String, Quest> questNames = new HashMap<String, Quest>();
	
	public QuestManager(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("questlog")) {
			
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Quest Log " + ChatColor.RED + "<<");
			if (Quest.getPlayersQuest(p) != null) {
			p.sendMessage(ChatColor.RED + "Active Quest: " + ChatColor.GRAY + Quest.getPlayersQuest(p).questName + "");
			
			p.sendMessage(ChatColor.RED + "Quest Objective: " + ChatColor.GRAY + Quest.getPlayersQuest(p).getQuestObjective() + "");
			
			p.sendMessage(ChatColor.RED + "Quest Reward: " + ChatColor.GRAY + "Coins: " + ChatColor.RED + Quest.getPlayersQuest(p).getCoinReward() 
				+	" "+ ChatColor.GRAY + "XP: " + ChatColor.RED + Quest.getPlayersQuest(p).getXPReward());
			} else {
				p.sendMessage(ChatColor.RED + "Active Quest: " + ChatColor.GRAY + "None!");
			}
			return true;
		}
		
		return false;
	}
}
