package me.carrasp.quests;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class KillSkeletonKing implements Listener {

	private Main plugin;
	
	private String questName;
	private int coinReward;
	private double xpReward;
	private String questObjective;
	private int levelRequirement;
	
	public static Quest killSkeletonKing;
	
	public KillSkeletonKing(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		questName = "Skeleton King";
		
		coinReward = 200;
		
		xpReward = 100;
		
		levelRequirement = 15;
		
		questObjective = "Find And Kill A Skeleton King";
		
		killSkeletonKing = new Quest(questName, coinReward, xpReward, levelRequirement, questObjective);
		
	}
	
	@EventHandler
	private void onKill(EntityDeathEvent event) {
		
		if (!(Quest.getPlayersQuest(event.getEntity().getKiller()).equals(killSkeletonKing))) return;
		
		if (!(event.getEntity() instanceof Skeleton)) return;
		
		if (!(event.getEntity().getName().contains("SKELETON KING"))) return;
		
		if (!(event.getEntity().getKiller() instanceof Player)) return;
		
		Player p = (Player) event.getEntity().getKiller();
		
		endQuest(p);
		
	}
	
	private void endQuest(Player p) {
		
		killSkeletonKing.removePlayerFromQuest(p);
		
		WeaponShop.addCoins(p, coinReward);
		
		Levels.addExp(p, xpReward);
		
		p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Completed Quest: " + ChatColor.RED + ChatColor.UNDERLINE.toString() + questName);
		
	}
	
	
	
	
}
