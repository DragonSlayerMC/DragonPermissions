package me.DragonPermissions.DragonSlayer;

import java.util.ArrayList;
import java.util.List;

import dragon.core.Config;
import dragon.core.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GroupCommand implements CommandExecutor {

    //Main plugin;

    private final JavaPlugin plugin = JavaPlugin.getProvidingPlugin(this.getClass());

    //public GroupCommand(Main plugin) {
    //this.plugin = plugin;
    //}


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("dggroup")) {
            Player p = (Player) sender;
            Config messagesconfig = new Config(plugin, "messages.yml");
            FileConfiguration messages = messagesconfig.getConfig();
            Config myconfig = new Config(plugin, "groups.yml");
            FileConfiguration groups = myconfig.getConfig();
            if (args.length == 4) {


            } else if (args.length == 3) {
                if (groupExists(args[0])) {
                    //Prefix
                    if (args[1].equalsIgnoreCase("prefix")) {
                        groups.set("groups." + args[0] + ".prefix", args[2]);
                        myconfig.saveConfig();
                        p.sendMessage(Utils.DMessage(messages.getString("prefix"), "$GROUP", args[0]));

                        //add perm
                    } else if (args[1].equalsIgnoreCase("add") || args[1].equalsIgnoreCase("addPermission")) {

                        if (groups.getStringList("groups." + args[0] + ".permissions") == null) {
                            List<String> list = new ArrayList<String>();
                            list.add(args[2]);
                            groups.set("groups." + args[0] + ".permissions", list);
                            myconfig.saveConfig();
                            p.sendMessage(Utils.DMessage(messages.getString("permadded"), "$GROUP", args[0], "$PERM", args[2]));
                        } else {
                            List<String> list = groups.getStringList("groups." + args[0] + ".permissions");
                            list.add(args[2]);
                            groups.set("groups." + args[0] + ".permissions", list);
                            myconfig.saveConfig();
                            p.sendMessage(Utils.DMessage(messages.getString("permadded"), "$GROUP", args[0], "$PERM", args[2]));
                        }
                    }
                } else {
                    p.sendMessage(Utils.DMessage(messages.getString("notexist"), "$GROUP", args[0]));
                }
            } else if (args.length == 2) {
                if (args[1].equalsIgnoreCase("create")) {
                    groups.set("groups." + args[0] + ".default", false);
                    groups.set("groups." + args[0] + ".prefix", args[1] + " ");
                    myconfig.saveConfig();
                    p.sendMessage(Utils.DMessage(messages.getString("groupcreate"), "$GROUP", args[0]));
                }

            }
        }
        return true;
    }

    public boolean groupExists(String group) {

        Config myconfig = new Config(plugin, "groups.yml");
        FileConfiguration groups = myconfig.getConfig();
        if (groups.get("groups." + group + ".default") == null) {
            return false;
        }
        return true;
    }
}
	
