package me.carrasp.quests;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Quest {
	
	public String questName;
	
	private int coinReward;
	
	private double theXpReward;
	
	private int theLevelRequirement;
	
	private String theQuestObjective;
	
	public static List<UUID> playersInQuests = new ArrayList<UUID>();
	
	public static List<String> quests = new ArrayList<String>();
	
	public Quest(String aName) {
		this.questName = aName;
		QuestManager.questNames.put(this.questName, this);
		quests.add(this.questName);
	}
	
	public Quest(String aName, int theCoinReward) {
		this.questName = aName;
		this.coinReward = theCoinReward;
		QuestManager.questNames.put(this.questName, this);
		quests.add(this.questName);
	}
	
	public Quest(String aName, int theCoinReward, double xpReward) {
		this.questName = aName;
		this.coinReward = theCoinReward;
		this.theXpReward = xpReward;
		QuestManager.questNames.put(this.questName, this);
		quests.add(this.questName);
	}
	
	public Quest(String aName, int theCoinReward, double xpReward, int levelRequirement) {
		this.questName = aName;
		this.coinReward = theCoinReward;
		this.theXpReward = xpReward;
		this.theLevelRequirement = levelRequirement;
		QuestManager.questNames.put(this.questName, this);
		quests.add(this.questName);
	}
	
	public Quest(String aName, int theCoinReward, double xpReward, int levelRequirement, String questObjective) {
		this.questName = aName;
		this.coinReward = theCoinReward;
		this.theXpReward = xpReward;
		this.theLevelRequirement = levelRequirement;
		this.theQuestObjective = questObjective;
		QuestManager.questNames.put(this.questName, this);
		quests.add(this.questName);
	}
	
	public String getQuestObjective() {
		return theQuestObjective;
	}
	
	public void addPlayerToQuest(Player p, Quest quest) {
		if (playersInQuests.contains(p.getUniqueId())) return;
		playersInQuests.add(p.getUniqueId());
		QuestManager.playersQuest.put(p.getName(), quest);
		
	}
	
	public List<UUID> getPlayersInQuest() {
		return playersInQuests;
	}
	
	public int getCoinReward() {
		return coinReward;
	}
	
	public double getXPReward() {
		return theXpReward;
	}
	
	public boolean isPlayerInQuest(Player p) {
		if (playersInQuests.contains(p.getUniqueId())) {
			return true;
		} else {
			return false;
		}
	}
	
	public int getLevelRequirement() {
		return this.theLevelRequirement;
	}
	
	public Quest getQuestByName(String name) {
		
		if (name == this.questName) {
			return this;
		} else {
			return null;
		}
		
	}
	
	public void removePlayerFromQuest(Player p) {
		if (playersInQuests.contains(p.getUniqueId())) {
			
			if (QuestManager.playersQuest.containsKey(p.getName())) {
				
				playersInQuests.remove(p.getUniqueId());
				QuestManager.playersQuest.remove(p.getName());
			}
			
		}
	}
	
	public static Quest getPlayersQuest(Player p) {
		
		if (playersInQuests.contains(p.getUniqueId())) {
			
			if (QuestManager.playersQuest.containsKey(p.getName())) {
				
				return QuestManager.playersQuest.get(p.getName());
				
			} else {
				return null;
			}
			
		} else {
			return null;
		}
		
	}

	public static Quest getQuestFromName(String questName) {
		
		for (String qString : QuestManager.questNames.keySet()) {
			
			if (questName.equals(qString)) {
				return QuestManager.questNames.get(qString);
			}
			
		}
		return null;
		
	}
	
}
