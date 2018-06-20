package me.carrasp.spells;

import org.bukkit.inventory.ItemStack;

public class Spell {
	
	private String name;
	
	private ItemStack icon;
	
	private int levelRequirement;
	
	private int slot;
	
	public Spell(String spellName) {
		this.name = spellName;
	}
	
	public Spell(String spellName, ItemStack itemIcon) {
		this.name = spellName;
		this.icon = itemIcon;
	}
	
	public Spell(String spellName, ItemStack itemIcon, int requiredLevel) {
		this.name = spellName;
		this.icon = itemIcon;
		this.levelRequirement = requiredLevel;
	}
	
	public Spell(String spellName, ItemStack itemIcon, int requiredLevel, int spellSlot) {
		this.name = spellName;
		this.icon = itemIcon;
		this.levelRequirement = requiredLevel;
		this.slot = spellSlot;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ItemStack getItemIcon() {
		return this.icon;
	}
	
	public int getRequiredLevel() {
		return this.levelRequirement;
	}
	
	public int getSlot() {
		return this.slot;
	}
	
}
