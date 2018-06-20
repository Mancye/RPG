package me.carrasp.guilds;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Guild {
	
	private String aGuildName;
	
	private Player theCreator;
	
	public Guild(String guildName, Player creator, List<UUID> guildMembers) {
		
		aGuildName = guildName;
		theCreator = creator;
		GuildManager.members.put(aGuildName, guildMembers);
		GuildManager.playersGuilds.put(creator.getUniqueId(), aGuildName);
		GuildManager.guilds.add(this);
	
	}
	
	public String getGuildName() {
		
		return aGuildName;
		
	}
	
	public List<Player> getGuildPlayers() {
		
		List<Player> players = new ArrayList<Player>();
		
		for (UUID id : GuildManager.members.get(this.getGuildName())) {
			
			Player p = Bukkit.getServer().getPlayer(id);
			players.add(p);
			
		}
		
		return players;
		
	}
	
	public Player getCreator() {
		return theCreator;
	}
	
	public static Guild getGuildFromName(String guildName) {
		
			for (Guild g : GuildManager.guilds) {
				if (g.getGuildName().equalsIgnoreCase(guildName)) {
					return g;
				}
			}
		
			return null;
	}
	
	
	public static boolean isPlayerGuildOwner(Player p, Guild g) {
		
		if (g.getCreator().equals(p)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void addMember(Player p) {
		GuildManager.members.get(this.getGuildName()).add(p.getUniqueId());
		GuildManager.playersGuilds.put(p.getUniqueId(), aGuildName);
	}
	
	public void removeMember(Player p) {
		GuildManager.members.get(this.getGuildName()).remove(p.getUniqueId());
		GuildManager.playersGuilds.remove(p.getUniqueId());
	}
	
	public boolean hasMember(Player p) {
		
		if (GuildManager.members.get(this.getGuildName()).contains(p.getUniqueId())) {
			return true;
		} else {
			return false;
		}
		
	}

	
}
