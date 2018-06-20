package me.carrasp.rpg.professions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class Hunting implements Listener, Runnable {

	private static Main plugin;
	private int huntingExpGain = 3;
	private int huntingCoinGain = 2;
	public static Map<UUID, Double> huntingXP = new HashMap<UUID, Double>();
	private static Map<Integer, Double> levelRequirement = new HashMap<Integer, Double>();
	public static Map<UUID, Integer> huntingLevel = new HashMap<UUID, Integer>();
	
	public Hunting(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void setSkill(PlayerJoinEvent event) {
		if (!(huntingXP.containsKey(event.getPlayer().getUniqueId()))) {
			huntingXP.put(event.getPlayer().getUniqueId(), 0.0);
		} 
		
		if (!(huntingLevel.containsKey(event.getPlayer().getUniqueId()))) {
			huntingLevel.put(event.getPlayer().getUniqueId(), 1);
		}
		
	}
	
	public static void checkHuntingLevelUp() {

		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!(huntingLevel.containsKey(p.getUniqueId()))) return;
			if (!(huntingXP.containsKey(p.getUniqueId()))) return;
			if (!(levelRequirement.containsKey(huntingLevel.get(p.getUniqueId()) + 1))) return;
			
			if (getHuntingXP(p) >= levelRequirement.get(getHuntingLevel(p) + 1)) {
				huntingLevel.put(p.getUniqueId(), getHuntingLevel(p) + 1);
				huntingXP.put(p.getUniqueId(), 0.0);
				p.sendMessage("Hunting Leveled up!");
			}
			
		}	

	}
	
	public static void setRequirement() {
		
		for (int i = 0; i < 100; i++) {
			
			levelRequirement.put(i, i * 5.0);
			
		}
	}
	
	@EventHandler
	private void onKill(EntityDeathEvent event) {
		try {
		if (event.getEntity() instanceof Player) return;
		
		if (!(event.getEntity().getKiller() instanceof Player)) return;
		
		Player killer = (Player) event.getEntity().getKiller();
		
		if (Levels.levels.containsKey(killer.getUniqueId())) {
					
					if (Levels.exp.containsKey(killer.getUniqueId())) {
						
						WeaponShop.addCoins(killer, huntingCoinGain);
						double expGain = getHuntingLevel(killer) * .25;
						Levels.addExp(killer, expGain * huntingExpGain);
						huntingXP.put(killer.getUniqueId(), huntingXP.get(killer.getUniqueId()) + 1);
						checkHuntingLevelUp();
					} else {
						return;
					}
			
			} else {
				return;
			}
		} catch (Exception e) {
			
		}
		}
	
	private static Integer getHuntingLevel(Player p) {
				
		return huntingLevel.get(p.getUniqueId());
	}
	
	private static Double getHuntingXP(Player p) {
		
		return huntingXP.get(p.getUniqueId());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
