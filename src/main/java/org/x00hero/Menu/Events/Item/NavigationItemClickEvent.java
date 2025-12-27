package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Items.NavigationItem;
import org.x00hero.Menu.Pages.Page;

public class NavigationItemClickEvent extends MenuItemClickEvent {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    public final NavigationItem clickedItem;
    public NavigationItemClickEvent(InventoryClickEvent event, Player player, NavigationItem navigationItem, ItemStack heldItem, Page page) {
        super(event, player, navigationItem, heldItem, page);
        this.clickedItem = navigationItem;
        event.setResult(Result.DENY);
        //event.setCancelled(true);
    }

    public int getNavAmount() { return isShiftClick() ? clickedItem.getShiftAmount() : clickedItem.getNavAmount(); }
    //public NavigationItem getNavigationItem() { return clickedItem; }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}