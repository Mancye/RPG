package me.carrasp.rpg.professions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class Farming implements Listener, Runnable {
	
	private int farmingCoinGain = 5;
	private int farmingXPGain = 2;

	private static Map<UUID, Double> farmingXP = new HashMap<UUID, Double>();	
	private static Map<UUID, Integer> farmingLevel = new HashMap<UUID, Integer>();	
	private static Map<Integer, Double> levelRequirement = new HashMap<Integer, Double>();
	
	private Main plugin;
	
	public Farming(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	private void setDefault(PlayerJoinEvent event) {
		
		if (!(farmingXP.containsKey(event.getPlayer().getUniqueId()))) {
			farmingXP.put(event.getPlayer().getUniqueId(), 0.0);
		}
		
		if (!(farmingLevel.containsKey(event.getPlayer().getUniqueId()))) {
			farmingLevel.put(event.getPlayer().getUniqueId(), 1);
		}
		
	}
	
	public static void checkFarmingLevelUp() {

		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!(farmingLevel.containsKey(p.getUniqueId()))) return;
			if (!(farmingXP.containsKey(p.getUniqueId()))) return;
			if (!(levelRequirement.containsKey(farmingLevel.get(p.getUniqueId()) + 1))) return;
			
			if (getFarmingXP(p) >= levelRequirement.get(getFarmingLevel(p) + 1)) {
				farmingLevel.put(p.getUniqueId(), getFarmingLevel(p) + 1);
				farmingXP.put(p.getUniqueId(), 0.0);
			}
			
		}	

	}
	
	private static int getFarmingLevel(Player p) {
		
		if (farmingLevel.containsKey(p.getUniqueId())) {
			
			return farmingLevel.get(p.getUniqueId());
			
		} else {
			return -1;
		}
		
	}
	
	private static int getFarmingXP(Player p) {
		
		if (farmingLevel.containsKey(p.getUniqueId())) {
			
			return farmingLevel.get(p.getUniqueId());
			
		} else {
			return -1;
		}
		
	}
	
	
	@EventHandler
	private void onHarvest(BlockBreakEvent event) {
			try {
			
			Player p = (Player) event.getPlayer();
			
			if (event.getBlock().getType().equals(Material.CROPS)) {
	
				if (Levels.exp.containsKey(p.getUniqueId())) {
				if (!(WeaponShop.coins.containsKey(p.getUniqueId()))) return;
					
					WeaponShop.addCoins(p, farmingCoinGain);
					double expGain = getFarmingLevel(p) * .25;
					Levels.addExp(p, expGain * farmingXPGain);
					farmingXP.put(p.getUniqueId(), farmingXP.get(p.getUniqueId()) + 3);
					checkFarmingLevelUp();
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
