package me.carrasp.rpg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.illumenmc.rpg.Levels;

public class LevelUpEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	Player p;
	
	public LevelUpEvent(Player p) {
		
		this.p = p;
		Levels.exp.put(p.getUniqueId(), (double) 1);
		
	}
	
	public int getLevel() {
		
		return Levels.levels.get(p.getUniqueId());
		
	}
	
	public Player getPlayer() {
		return p;
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
