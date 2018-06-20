package com.illumenmc.rpg;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.illumenmc.weapons.WeaponShop;

public class Bones implements Listener, CommandExecutor {
	
	private Main plugin;
	
	private PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, 9999, 4);
	
	private Map<UUID, Boolean> hasBrokenLegMild = new HashMap<UUID, Boolean>();
	private Map<UUID, Boolean> hasBrokenLegSevere = new HashMap<UUID, Boolean>();
	private Map<UUID, Integer> amtOfBandagesUsed = new HashMap<UUID, Integer>();
	
	private static ItemStack bandage;
	
	public Bones(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	public static void setUpBandage() {
		
		bandage = new ItemStack(Material.PAPER);
		ItemMeta bandagemeta = bandage.getItemMeta();
		bandagemeta.setDisplayName(ChatColor.RED + "BANDAGE");
		bandage.setItemMeta(bandagemeta);
		
	}
	
	
	
	@EventHandler
	private void onFall(EntityDamageEvent event) {
		
		if (!(event.getEntity() instanceof Player)) return;
		
		Player p = (Player) event.getEntity();
		
		if (!(event.getCause().equals(DamageCause.FALL))) return;
		
		if (p.getFallDistance() >= 6) {
			
			Random fRan = new Random();
			int fChan = fRan.nextInt(100) + 1;
			
			if (fChan >= 10) {
				
				Random lRan= new Random();
				int lChan = lRan.nextInt(100) + 1;
				if (lChan >= 90) {
				hasBrokenLegMild.put(p.getUniqueId(), true);
				p.addPotionEffect(slowness);
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Broken Your Leg! Use 1 Bandages To Heal It!");
				} else if (lChan <= 25) {
					hasBrokenLegSevere.put(p.getUniqueId(), true);
					amtOfBandagesUsed.put(p.getUniqueId(), 0);
					p.addPotionEffect(slowness);
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Severely Broken Your Leg! Use 2 Bandages To Heal It!");
				}
				 
			}
			
		}
		
	}
	
	@EventHandler
	private void useBandageMild(PlayerInteractEvent event) {
		
		Player p = event.getPlayer();
		
		if (p.getInventory().getItemInMainHand().getItemMeta() != null) {
			if (p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("BANDAGE")) {
				if (hasBrokenLegMild.containsKey(p.getUniqueId())) {
					
					hasBrokenLegMild.remove(p.getUniqueId());
					
					if (p.hasPotionEffect(PotionEffectType.SLOW)) {
						p.removePotionEffect(PotionEffectType.SLOW);
					} 
					
					p.sendMessage(ChatColor.RED + "Leg Healed!");
					
					if (bandage != null) {
					p.getInventory().remove(bandage);
					}
					
				}
			}
		} 
		
	}
	
	//Added severe breaks
	
	@EventHandler
	private void useBandageSevere(PlayerInteractEvent event) {
		
		Player p = event.getPlayer();
		
		if (p.getInventory().getItemInMainHand().getItemMeta() != null) {
			if (p.getInventory().getItemInMainHand().getItemMeta().hasDisplayName() && p.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("BANDAGE")){
				if (hasBrokenLegSevere.containsKey(p.getUniqueId())) {
					
					if (amtOfBandagesUsed.get(p.getUniqueId()) <= 0) {
						amtOfBandagesUsed.put(p.getUniqueId(), amtOfBandagesUsed.get(p.getUniqueId()) + 1);
						p.getInventory().remove(p.getInventory().getItemInMainHand());
					} else if (amtOfBandagesUsed.get(p.getUniqueId()) == 1) {
						
						amtOfBandagesUsed.remove(p.getUniqueId());
						hasBrokenLegSevere.remove(p.getUniqueId());
						
						if (p.hasPotionEffect(PotionEffectType.SLOW)) {
							p.removePotionEffect(PotionEffectType.SLOW);
						} 
						
						p.sendMessage(ChatColor.RED + "Leg Healed!");
						if (bandage != null) {
						p.getInventory().remove(bandage);
						}
						
					}
					
				}
			}
		}
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("bandage")) {
			
			if (WeaponShop.getCoins(p) >= 20) {
				
				if (bandage != null) {
					
				p.getInventory().addItem(bandage);
				WeaponShop.removeCoins(p, 20);
				
				return true;
				} else {
					p.sendMessage(ChatColor.RED + "Shit got fucked up");
					return false;
				}
				
			} else {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Enough Coins. Bandage Costs 20 Coins!");
				return false;
			}
			
		}
		
		return false;
	}

}
