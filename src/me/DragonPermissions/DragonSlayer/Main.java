package me.DragonPermissions.DragonSlayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Main plugin;

	
	
	public HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();
	
	Server server = Bukkit.getServer();
	PluginManager pm = server.getPluginManager();
	
	public void onEnable() {
		plugin = this;
		saveDefaultConfig();
		pm.registerEvents(new onJoinEvent(), this);
		getCommand("group").setExecutor(new GroupCommand());
		
		
	}
	
	public void onDisable() {
		perms.clear();
		
	}
	


}
