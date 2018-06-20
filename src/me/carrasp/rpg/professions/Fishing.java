package me.carrasp.rpg.professions;



import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class Fishing implements Listener, Runnable {

	private static Main plugin;
	private int fishingExpGain = 5;
	private int fishingCoinGain = 2;
	
	public static Map<UUID, Double> fishingXP = new HashMap<UUID, Double>();	
	public static Map<UUID, Integer> fishingLevel = new HashMap<UUID, Integer>();	
	public static Map<Integer, Double> levelRequirement = new HashMap<Integer, Double>();
	
	public Fishing(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	private void setDefault(PlayerJoinEvent event) {
		
		if (!(fishingXP.containsKey(event.getPlayer().getUniqueId()))) {
			fishingXP.put(event.getPlayer().getUniqueId(), 0.0);
		}
		
		if (!(fishingLevel.containsKey(event.getPlayer().getUniqueId()))) {
			fishingLevel.put(event.getPlayer().getUniqueId(), 1);
		}
		
	}
	
	public static void checkFishingLevelUp() {

		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!(fishingLevel.containsKey(p.getUniqueId()))) return;
			if (!(fishingXP.containsKey(p.getUniqueId()))) return;
			if (!(levelRequirement.containsKey(fishingLevel.get(p.getUniqueId()) + 1))) return;
			
			if (getFishingXP(p) >= levelRequirement.get(getFishingLevel(p) + 1)) {
				fishingLevel.put(p.getUniqueId(), getFishingLevel(p) + 1);
				fishingXP.put(p.getUniqueId(), 0.0);
			}
			
		}	

	}
	
	private static int getFishingLevel(Player p) {
		
		if (fishingLevel.containsKey(p.getUniqueId())) {
			
			return fishingLevel.get(p.getUniqueId());
			
		} else {
			return -1;
		}
		
	}
	
	private static int getFishingXP(Player p) {
		
		if (fishingLevel.containsKey(p.getUniqueId())) {
			
			return fishingLevel.get(p.getUniqueId());
			
		} else {
			return -1;
		}
		
	}
	
	
	@EventHandler
	private void onFish(PlayerFishEvent event) {
			try {
			if (event.getCaught() == null) return;
			
			Player p = (Player) event.getPlayer();
			
				if (Levels.exp.containsKey(p.getUniqueId())) {
					if (!(WeaponShop.coins.containsKey(p.getUniqueId()))) return;
					if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
					WeaponShop.addCoins(p, fishingCoinGain);
					double expGain = getFishingLevel(p) * .25;
					Levels.addExp(p, expGain * fishingExpGain);
					fishingXP.put(p.getUniqueId(), fishingXP.get(p.getUniqueId()) + 3);
					checkFishingLevelUp();
					
					} else {
						return;
					}
				} else {
					return;
				}
			} catch (Exception e) {
				
			}
			}
	
	public static void setRequirement() {
		
		for (int i = 0; i < 100; i++) {
			
			levelRequirement.put(i, i * 5.0);
			
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
