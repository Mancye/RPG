package me.carrasp.guilds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class GuildManager {
	
	public static Map<UUID, String> playersGuilds;
	
	public static List<Guild> guilds;
	
	public static Map<String, List<UUID>> members;
	
	public static void setUpStuff() {
		
		members = new HashMap<String, List<UUID>>();
		
		guilds = new ArrayList<Guild>();
		
		playersGuilds = new HashMap<UUID, String>();
		
	}
	
	public static Guild getPlayersGuild(Player p) {
		
		if (!(playersGuilds.containsKey(p.getUniqueId()))) return null;
		
		return Guild.getGuildFromName(playersGuilds.get(p.getUniqueId()));
		
	}

	
	
}
