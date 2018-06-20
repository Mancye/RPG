package me.carrasp.party;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.illumenmc.rpg.Main;

public class Group implements Listener, CommandExecutor {

	private Main plugin;
	//private HashMap<String, List<UUID>> parties = new HashMap<String, List<UUID>>();
	private List<UUID> hasPendingInvite = new ArrayList<UUID>();
	private Map<String, PartyBase> parties;
	private PartyBase party;
	private Player inviter;
	//private List<UUID> partyMembers;
	Player invited;
	
	public Group(Main main) {
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		parties = new HashMap<String, PartyBase>();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		
		if (command.getName().equalsIgnoreCase("accept")) {
			
			p.sendMessage("accept run");
			invited = p;
			if (invited != null) {
				invited.sendMessage("invited not == null, sendmessage");
			}
			
			if (inviter != null) {
				invited.sendMessage("inviter not == null, sendmessage");
			}
			
			if (hasPendingInvite == null) {
				p.sendMessage("List is null");
			}
					
					for (PartyBase party : parties.values())  {
						
						if (party.getCreator().getUniqueId() == inviter.getUniqueId()) {
							
							party.addMember(invited);
							invited.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Joined Party: " + party.getPartyName());
							hasPendingInvite.remove(p.getUniqueId());
							return true;
							
						} else {
							return false;
						}
						
					}
					
				
		
		}
		
		if (command.getName().equalsIgnoreCase("party")) {
			if (args.length == 0) {
			
			p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "/party create (nameofparty)");
			return false;
			
			} else if (args.length == 1) {
				
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "/party create (nameofparty)");
				return false;
				
			} else if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("create")) {
					
					if (p.hasPermission("party.createparty")) {
					party = new PartyBase(args[1], p);
					parties.put(args[0], party);
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Party Created!");
					return true;
					
					} else {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Are Not Allowed To Create A Party!");
						return false;
					}
					
				} else if (args[0].equalsIgnoreCase("invite")) {
					
					invited = Bukkit.getServer().getPlayer(args[1]);
					
					if (invited != null) {
						inviter = p;
						invited.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invited To Join Party!, Type " + ChatColor.RED + "/accept" + ChatColor.GRAY + " To Join The Party!");
						return true;
					} else {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Player!");
						return false;
					}
					
				} else {
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Arguments!");
					return false;
				}
				
			} else {
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Arguments!");
				return false;
			}
		}
		
		return false;
		
	}
	
	@EventHandler
	private void handlePartyChat(AsyncPlayerChatEvent event) {
		
		Player p = event.getPlayer();
		
		
		if (Party.getChannel(p) == "Party Chat") {
			
			for (Player online : Bukkit.getServer().getOnlinePlayers()) {
				
				if (party.getMembersUUID().contains(online.getUniqueId())) {
					online.sendMessage(ChatColor.RED + "[Party Chat] >> " + ChatColor.GRAY + event.getMessage());
					event.setCancelled(true);
				} else {
					event.setCancelled(true);
				}
				
			}
			
		}
		
	}
	
}
