package me.carrasp.rpg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.illumenmc.rpg.Levels;

public class XPGainEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	Player p;
	
	public XPGainEvent(Player p) {
		
		this.p = p;
		
	}
	
	public Double getXP() {
		
		return Levels.getExp(p);
		
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
