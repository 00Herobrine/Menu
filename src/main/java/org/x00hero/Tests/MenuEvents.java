package org.x00hero.Tests;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.x00hero.Menu.Events.Item.*;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.Events.Menu.MenuCloseEvent;
import org.x00hero.Menu.Events.Menu.MenuNavigationEvent;
import org.x00hero.Menu.Events.Menu.MenuOpenEvent;

public class MenuEvents implements Listener {
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
        e.getPlayer().sendMessage("NavItem Clicked " + e.getNavigationItem().getName() + " NavAmount: " + e.getNavAmount());
    }
    @EventHandler
    public void MenuNavigation(MenuNavigationEvent e) {
        e.getPlayer().sendMessage("Opened " + e.getPage().getNumber() + " from " + e.getInitialPage().getNumber() + " NavAmount: " + e.getNavAmount());
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
