package me.carrasp.guilds;

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

import com.illumenmc.rpg.Main;

public class CreateGuild implements CommandExecutor {

	private List<UUID> hasPendingInvite;
	
	private Map<UUID, Guild> guildInvitedTo;
	
	public CreateGuild(Main main) {
		hasPendingInvite = new ArrayList<UUID>();
		guildInvitedTo = new HashMap<UUID, Guild>();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		
		Player p = (Player) sender;
		
		if (command.getName().equalsIgnoreCase("guild")) {
			
			if (args.length == 0) {
				
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Guild Help" + ChatColor.RED + " <<");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Create A Guild: " + ChatColor.RED + "/guild create (guild name)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invite A Player To A Guild: " + ChatColor.RED + "/guild invite (player)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Remove A Player From A Guild: " + ChatColor.RED + "/guild remove (player)");
				p.sendMessage("");
				p.sendMessage("");
				
			} else if (args.length == 1 || args[0].equalsIgnoreCase("help")) {
				if (!(args[0].equalsIgnoreCase("accept") || args[0].equalsIgnoreCase("chat"))) {
				p.sendMessage("");
				p.sendMessage("");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Guild Help" + ChatColor.RED + " <<");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Create A Guild: " + ChatColor.RED + "/guild create (guild name)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invite A Player To A Guild: " + ChatColor.RED + "/guild invite (player)");
				p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Remove A Player From A Guild: " + ChatColor.RED + "/guild remove (player)");
				p.sendMessage("");
				p.sendMessage("");
				}
				if (args[0].equalsIgnoreCase("accept")) {
					
					if (GuildManager.getPlayersGuild(p) != null) {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Already In Guild, Do " + ChatColor.RED + "/guild leave");
						return false;
					}
					
					if (hasPendingInvite.contains(p.getUniqueId())) {
						
						hasPendingInvite.remove(p.getUniqueId());
						
						if (guildInvitedTo.containsKey(p.getUniqueId())) {
							
							if (guildInvitedTo.get(p.getUniqueId()) != null) {
								
								Guild guild = guildInvitedTo.get(p.getUniqueId());
								
								guild.addMember(p);
								
								if (guild.hasMember(p)) {
									p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Joined Guild: " + ChatColor.RED + guild.getGuildName());
								} else {
									p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Not Added To Guild, Contact A Developer If You See This!");
								}
								
							} else {
								return false;
							}
							
						} else {
							return false;
						}
						
					} else {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "No Pending Invites Avaliable To Accept");
						return false;
					}
					
				} else {
					if (args[0].equalsIgnoreCase("chat")) {
						
						if (!(GuildManager.playersGuilds.containsKey(p.getUniqueId()))) return false;
						
						
						if (GuildChat.isInGuildChat.containsKey(p.getUniqueId())) {
							
							if (GuildChat.isInGuildChat.get(p.getUniqueId()) == true) {
								GuildChat.isInGuildChat.put(p.getUniqueId(), false);
								p.sendMessage(ChatColor.RED + ">> " + ChatColor.RED + "Guild Chat Off");
							} else {
								GuildChat.isInGuildChat.put(p.getUniqueId(), true);
								p.sendMessage(ChatColor.RED + ">> " + ChatColor.RED + "Guild Chat On");
							}
							
						} else {
							GuildChat.isInGuildChat.put(p.getUniqueId(), true);
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.RED + "Guild Chat On");
						}
						
					}
				}
				
			} else if (args.length == 2) {
				
				if (args[0].equalsIgnoreCase("create")) {
					
					String name = args[1];
					List<UUID> guildMembers = new ArrayList<UUID>();
					guildMembers.add(p.getUniqueId());
					Guild guild = new Guild(name, p, guildMembers);
					p.sendMessage("");
					p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Guild Created, Guild Name: "  + ChatColor.RED + ChatColor.UNDERLINE.toString() + guild.getGuildName());
					p.sendMessage("");
					
				} else if (args[0].equalsIgnoreCase("invite")) {
					
					if (Bukkit.getServer().getPlayer(args[1]) != null) {
						
						Guild guild = GuildManager.getPlayersGuild(p);
						
						if (Guild.isPlayerGuildOwner(p, guild)) {
							
							Player invited = Bukkit.getServer().getPlayer(args[1]);
							
							guildInvitedTo.put(invited.getUniqueId(), guild);
							
							hasPendingInvite.add(invited.getUniqueId());
							
							if (hasPendingInvite.contains(invited.getUniqueId())) {
								invited.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Have Been Invited To Join A Guild, Do " + ChatColor.RED + "/guild accept" + ChatColor.GRAY + " To Join!");
								p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invited " + ChatColor.RED + invited.getName() + " To Your Guild");
							} else {
								p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Failed To Send The Invite, Contact A Developer If You See This!");
							}
							
						} else {
							p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "You Must Be The Owner Of The Guild To Invite Others");
							return false;
						}
						
					} else {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Player Name");
						return false;
					}
					
				} else if (args[0].equalsIgnoreCase("remove")) {
					
					if (Bukkit.getServer().getPlayer(args[1]) != null) {
						
					} else {
						p.sendMessage(ChatColor.RED + ">> " + ChatColor.GRAY + "Invalid Player Name");
						return false;
					}
					
				} else {
					return false;
				}
				
			}
			
		}
		
		if (command.getName().equalsIgnoreCase("guildlist")) {
			
			if (GuildManager.guilds == null) {
				p.sendMessage("List no exist");
			}
			
			for (Guild g : GuildManager.guilds) {
				
				p.sendMessage(g.getGuildName());
				
			}
			
		}
		
		return false;
		
	}

}
