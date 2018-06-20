package me.carrasp.mobs.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.illumenmc.rpg.Main;

public class Menu implements Listener {
	
	private Main plugin;
	
	public Menu(Main main) {
		
		plugin = main;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}

	public static Inventory getCMMenu() {
		
		Inventory menu = Bukkit.createInventory(null, 9, ChatColor.RED + "Main Menu");
		
		ItemStack config = new ItemStack(Material.PAPER);
		ItemMeta configmeta = config.getItemMeta();
		List<String> configlore = new ArrayList<String>();
		
		configmeta.setDisplayName(ChatColor.RED + "Mob Configuration");
		configlore.add(ChatColor.GRAY + ">> " + ChatColor.RED + "Opens A Menu To Configure Traits Of Mobs");
		configmeta.setLore(configlore);
		config.setItemMeta(configmeta);
		
		//Add Items To Inventory
		menu.addItem(config);
		
		return menu;
		
	}
	
	private Inventory getConfigMenu() {
		
		Inventory config = Bukkit.createInventory(null, 36, ChatColor.RED + "Mob Configuration");
		
		ItemStack fz = new ItemStack(Material.BOOK);
		ItemMeta fzMeta = fz.getItemMeta();
		fzMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Fast Zombie Configuration");
		fz.setItemMeta(fzMeta);
		
		config.addItem(fz);
		
		ItemStack fs = new ItemStack(Material.BOOK);
		ItemMeta fsMeta = fs.getItemMeta();
		fsMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Fast Skeleton Configuration");
		fs.setItemMeta(fsMeta);
		
		config.addItem(fs);
		
		ItemStack ss = new ItemStack(Material.BOOK);
		ItemMeta ssMeta = ss.getItemMeta();
		ssMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Skeleton Swordsman Configuration");
		ss.setItemMeta(ssMeta);
		
		config.addItem(ss);
		
		ItemStack zs = new ItemStack(Material.BOOK);
		ItemMeta zsMeta = zs.getItemMeta();
		zsMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Zombie Swordsman Configuration");
		zs.setItemMeta(zsMeta);
		
		config.addItem(zs);
		
		ItemStack zt = new ItemStack(Material.BOOK);
		ItemMeta ztMeta = zt.getItemMeta();
		ztMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Zombie Tank Configuration");
		zt.setItemMeta(ztMeta);
		
		config.addItem(zt);
		
		ItemStack st = new ItemStack(Material.BOOK);
		ItemMeta stMeta = st.getItemMeta();
		stMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.RED + "Skeleton Tank Configuration");
		st.setItemMeta(stMeta);
		
		config.addItem(st);
		
		ItemStack t = new ItemStack(Material.BOOK);
		ItemMeta tMeta = t.getItemMeta();
		tMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.DARK_RED + "TARANTULA Configuration");
		t.setItemMeta(tMeta);
		
		config.addItem(t);
		
		ItemStack sk = new ItemStack(Material.BOOK);
		ItemMeta skMeta = sk.getItemMeta();
		skMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.DARK_RED + "SKELETON KING Configuration");
		sk.setItemMeta(skMeta);
		
		config.addItem(sk);
		
		ItemStack zk = new ItemStack(Material.BOOK);
		ItemMeta zkMeta = zk.getItemMeta();
		zkMeta.setDisplayName(ChatColor.GRAY + ">> " + ChatColor.DARK_RED + "ZOMBIE KING Configuration");
		zk.setItemMeta(zkMeta);
		
		config.addItem(zk);
		
		
		
		return config;
		
	}
	
	@EventHandler
	private void configMenuClick(InventoryClickEvent event) {
		
		if (!(event.getInventory().getName().contains("Mob Configuration"))) return;
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		event.setCancelled(true);
		
		Player p = (Player) event.getWhoClicked();
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: p.openInventory(FZMenu.getFZMenu());
		break;
		
		case 1: p.openInventory(FSMenu.getFSMenu());
		break;
		
		case 2: p.openInventory(SSMenu.getSSMenu());
		break;
		
		case 3: p.openInventory(ZSMenu.getZSMenu());
		break;
		
		case 4: p.openInventory(ZTMenu.getZTMenu());
		break;
		
		case 5: p.openInventory(STMenu.getSTMenu());
		break;
		
		case 6: p.openInventory(TMenu.getTMenu());
		break;
		
		case 7: p.openInventory(SKMenu.getSKMenu());
		break;
		
		case 8: p.openInventory(ZKMenu.getZKMenu());
		break;
		
		}
		
	}
	
	@EventHandler
	private void mainMenuClick(InventoryClickEvent event) {
		
		if (event.getInventory().getName().contains("Main Menu")) {
		
		if (!(event.getWhoClicked() instanceof Player)) return;
		
		Player p = (Player) event.getWhoClicked();
		
		event.setCancelled(true);
		
		int slot = event.getSlot();
		
		switch (slot) {
		
		case 0: p.openInventory(getConfigMenu());
		p.sendMessage(ChatColor.GRAY + ">> " + ChatColor.RED + "Configuration Menu Opened");
		break;
		
		}
		
		}
		
	}
	
}
