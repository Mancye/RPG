package me.carrasp.duels;

import java.util.Map;
import java.util.UUID;

public class Duel {

	public Map<UUID, UUID> people;
	
	public Duel(Map<UUID, UUID> duelers) {
		
		this.people = duelers;
		
	}
	
	
	
}
