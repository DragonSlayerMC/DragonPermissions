package me.DragonPermissions.DragonSlayer;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;

public class onJoinEvent implements Listener {
	
	Main plugin;
	
	
	@EventHandler
	public void onJoin(org.bukkit.event.player.PlayerJoinEvent e) {
		Player p = e.getPlayer();
		PermissionAttachment attachment = p.addAttachment(plugin);
		plugin.perms.put(p.getUniqueId(), attachment);
		PermissionAttachment pperms = plugin.perms.get(p.getUniqueId());
	}

}
