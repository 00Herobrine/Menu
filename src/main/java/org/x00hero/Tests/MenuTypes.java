package org.x00hero.Tests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;

public class MenuTypes implements Listener, CommandExecutor {
    private static final Menu StoredMenu = new Menu("Stored Menu");

    public static Menu ScalingMenu(int slots) {
        Menu scalingMenu = new Menu("Scaling Menu");
        int i = 0;
        for(Material material : Material.values()) {
            //Bukkit.broadcastMessage("Iterating " + material.name() + i);
            if(i > slots) break;
            if(material.isAir()) continue;
            scalingMenu.addItem(new MenuItem(material, material.name() + i));
            i++;
        }
        return scalingMenu;
    }

    public static void storeMenu() { }
    public static Menu getStoredMenu() { return StoredMenu; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) return false;
        if(command.getName().equalsIgnoreCase("menu")) {
            if(args.length < 1) return false;
            switch(args[0].toLowerCase()) {
                case "scaling":
                    player.sendMessage("Parse : " + args[1]);
                    MenuTypes.ScalingMenu(Integer.parseInt(args[1].trim())).open(player);
                    return true;
                case "stored":
                    MenuTypes.getStoredMenu().open(player);
                    return true;
            }
        }
        return false;
    }

}
