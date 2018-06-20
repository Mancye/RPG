package me.carrasp.spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class SpellManager {

	public static List<Spell> spells = new ArrayList<Spell>();
	
	public static Map<UUID, List<Spell>> playerSpells = new HashMap<UUID, List<Spell>>();
	
	public static boolean playerHasSpell(Player p, Spell spell) {
		
		if (!(playerSpells.containsKey(p.getUniqueId()))) return false;
			
		for (Spell s : playerSpells.get(p.getUniqueId())) {
			
			if (spell == s) {
				
				return true;
				
			}
			
		}
		return false;
	}
	
	
}
