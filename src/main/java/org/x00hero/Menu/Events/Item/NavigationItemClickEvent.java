package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.NavigationItem;
import org.x00hero.Menu.Pages.Page;

public class NavigationItemClickEvent extends MenuClickEvent {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private final NavigationItem navigationItem;
    public NavigationItemClickEvent(Player player, Page page, NavigationItem navigationItem, InventoryClickEvent event) {
        super(player, page, navigationItem, event.getCursor(), event);
        this.navigationItem = navigationItem;
        //event.setCancelled(true);
    }

    public NavigationItem getNavigationItem() { return navigationItem; }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}