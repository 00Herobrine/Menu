package org.x00hero.Tests;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.x00hero.Menu.Events.Item.*;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.Events.Menu.MenuCloseEvent;
import org.x00hero.Menu.Events.Menu.MenuNavigationEvent;
import org.x00hero.Menu.Events.Menu.MenuOpenEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;

public class ShittyMenus implements Listener, CommandExecutor {
    private static Menu StoredMenu = new Menu("Stored Menu");

    public static Menu ScalingMenu(int slots) {
        Menu scalingMenu = new Menu("Scaling Menu");
        int i = 0;
        for(Material material : Material.values()) {
            Bukkit.broadcastMessage("Iterating " + material.name() + i);
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
                    ShittyMenus.ScalingMenu(Integer.parseInt(args[1].trim())).open(player);
                    return true;
                case "stored":
                    ShittyMenus.getStoredMenu().open(player);
                    return true;
            }
        }
        return false;
    }

    @EventHandler
    public void MenuItemClick(MenuItemClickEvent e) {
        if(e.getClickedItem() == null) return;
        e.getPlayer().sendMessage("Clicked " + e.getClickedItem().getName() + " on " + e.getMenu().getTitle() + " @ " + e.getRawSlot());
    }
    @EventHandler
    public void MenuItemSwap(MenuItemSwapEvent e) {
        e.getPlayer().sendMessage("Swapped " + e.getMenuItem().getName() + " with " + e.getSwappedItem().getName());
    }
    @EventHandler
    public void MenuItemRemove(MenuItemRemoveEvent e) {
        e.getPlayer().sendMessage("Removed " + e.getMenuItem().getName() + " from " + e.getPage().getNumber());
    }
    @EventHandler
    public void MenuItemAdd(MenuItemAddEvent e) {
        e.getPlayer().sendMessage("Added " + e.getMenuItem().getName() + " to " + e.getPage().getNumber());
    }

    @EventHandler
    public void NavigationClick(NavigationItemClickEvent e) {
        e.getPlayer().sendMessage("NavItem Clicked " + e.getNavigationItem().getName());
    }
    @EventHandler
    public void MenuNavigation(MenuNavigationEvent e) {
        e.getPlayer().sendMessage("Opened " + e.getPage().getNumber() + " from " + e.getInitialPage().getNumber());
    }

    @EventHandler
    public void MenuClick(MenuClickEvent e) {
        e.getPlayer().sendMessage("Clicked " + e.getMenu().getTitle() + " @ " + e.getRawSlot());
    }
    @EventHandler
    public void MenuClose(MenuCloseEvent e) {
        e.getPlayer().sendMessage("Closed " + e.getMenu().getTitle() + " on " + e.getPage().getNumber());
    }
    @EventHandler
    public void MenuOpen(MenuOpenEvent e) {
        e.getPlayer().sendMessage("Opened " + e.getMenu().getTitle() + " on " + e.getPage().getNumber());
    }
}
