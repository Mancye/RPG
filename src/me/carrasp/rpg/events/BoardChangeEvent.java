package me.carrasp.rpg.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BoardChangeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();

	Player p;
	
	public BoardChangeEvent(Player p) {
		
		this.p = p;
		
	}
	
	public Player getBoardOwner() {
		return p;
	}
	
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList() {
		return handlers;
	}
	
}
