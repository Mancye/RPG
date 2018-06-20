package me.carrasp.party;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class PartyBase {
	
	public String name;
	public Player creator;
	
	public List<UUID> partyMembers = new ArrayList<UUID>();
	
	public PartyBase(String name, Player creator) {
		
		this.name = name;
		this.creator = creator;
		
	}
	
	public void addMember(Player p) {
		
		partyMembers.add(p.getUniqueId());
		
	}
	
	public void removeMember(Player p) {
		
		partyMembers.add(p.getUniqueId());
		
	}
	
	public String getPartyName() {
		return this.name;
	}
	
	public List<UUID> getMembersUUID() {
		return this.partyMembers;
	}
	
	public Player getCreator() {
		return this.creator;
	}

}
