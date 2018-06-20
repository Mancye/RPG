package me.carrasp.rpg.professions;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.illumenmc.rpg.Levels;
import com.illumenmc.rpg.Main;
import com.illumenmc.weapons.WeaponShop;

public class Mining implements Listener {
	
	private static Main plugin;
	private double miningExpGain = 1;
	
	public static Map<UUID, Double> miningXP = new HashMap<UUID, Double>();
	public static Map<UUID, Integer> miningLevel = new HashMap<UUID, Integer>();
	private static Map<Integer, Double> levelRequirement = new HashMap<Integer, Double>();
	
	public Mining(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	private void setDefault(PlayerJoinEvent event) {
		
		if (!(miningXP.containsKey(event.getPlayer().getUniqueId()))) { 
			
			miningXP.put(event.getPlayer().getUniqueId(), 0.0);
			
		} else {
			
			return;
			
		}
		if (!(miningLevel.containsKey(event.getPlayer().getUniqueId()))) {
		
			miningLevel.put(event.getPlayer().getUniqueId(), 1);
			
		} else {
			
			return;
			
		}
		
	}
	
	public static void setRequirement() {
		
		for (int i = 0; i < 100; i++) {
			
			levelRequirement.put(i, i * 5.0);
			
		}
	}
	
	public static void checkMiningLevelUp() {

		
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			if (!(miningLevel.containsKey(p.getUniqueId()))) return;
			if (!(miningXP.containsKey(p.getUniqueId()))) return;
			if (!(levelRequirement.containsKey(miningLevel.get(p.getUniqueId()) + 1))) return;
			
			if (getMiningXP(p) >= levelRequirement.get(getMiningLevel(p) + 1)) {
				miningLevel.put(p.getUniqueId(), getMiningLevel(p) + 1);
				miningXP.put(p.getUniqueId(), 0.0);
			}
			
		}	

	}
	
	private static Integer getMiningLevel(Player p) {
		return miningLevel.get(p.getUniqueId());
	}
	
	private static Double getMiningXP(Player p) {
		return miningXP.get(p.getUniqueId());
	}
	
	
	@EventHandler
	private void placedBlock(BlockPlaceEvent event) {
		
		Block b = event.getBlock();
		b.setMetadata("PLACED", new FixedMetadataValue(plugin, "something"));
		
	}
	
	@EventHandler
	private void onBreak(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		Block b = event.getBlock();
		try {
		if (event.getBlock().getType().equals(Material.STONE)) {
			if (b.hasMetadata("PLACED")) {
				return;
			} else {
				Levels.addExp(player, 1);
				WeaponShop.addCoins(player, 1);
				double expGain = getMiningLevel(player) * .25;
				Levels.addExp(player, expGain * miningExpGain);
				miningXP.put(player.getUniqueId(), miningXP.get(player.getUniqueId()) + 1);
				checkMiningLevelUp();
				
			}
			
		}
		
		if (event.getBlock().getType().equals(Material.COAL_ORE)) {
			
			if (b.hasMetadata("PLACED")) {
				return;
			} else {
				Levels.addExp(player, 2);
				WeaponShop.addCoins(player, 2);
				double expGain = getMiningLevel(player) * .25;
				Levels.addExp(player, expGain * miningExpGain);
				miningXP.put(player.getUniqueId(), miningXP.get(player.getUniqueId()) + 1);
				checkMiningLevelUp();
			}
			
		}
		
		if (event.getBlock().getType().equals(Material.IRON_ORE)) {
			
			if (b.hasMetadata("PLACED")) {
				return;
			} else {
				Levels.addExp(player, 3);
				WeaponShop.addCoins(player, 3);
				double expGain = getMiningLevel(player) * .25;
				Levels.addExp(player, expGain * miningExpGain);
				miningXP.put(player.getUniqueId(), miningXP.get(player.getUniqueId()) + 2);
				checkMiningLevelUp();
			}
			
		}
		
		if (event.getBlock().getType().equals(Material.GOLD_ORE)) {
			
			if (b.hasMetadata("PLACED")) {
			return;
			} else {
				Levels.addExp(player, 4);
				WeaponShop.addCoins(player, 5);
				double expGain = getMiningLevel(player) * .25;
				Levels.addExp(player, expGain * miningExpGain);
				miningXP.put(player.getUniqueId(), miningXP.get(player.getUniqueId()) + 3);
				checkMiningLevelUp();
			}
			
		}
		
		if (event.getBlock().getType().equals(Material.DIAMOND_ORE)) {
			
			if (b.hasMetadata("PLACED")) {
			return;
			} else {
				Levels.addExp(player, 7);
				WeaponShop.addCoins(player, 7);
				double expGain = getMiningLevel(player) * .25;
				Levels.addExp(player, expGain * miningExpGain);
				miningXP.put(player.getUniqueId(), miningXP.get(player.getUniqueId()) + 5);
				checkMiningLevelUp();
			}
			
		}
		} catch (Exception e) {
			
		}
	}

}
