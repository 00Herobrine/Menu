package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Pages.Page;

public class MenuInteractEvent extends InventoryInteractEvent {
    public final MenuItem clickedItem;
    public final Page page;
    public final Menu menu;
    public static HandlerList handlerList = new HandlerList();

    public MenuInteractEvent(InventoryView transaction, MenuItem clickedItem, Page Page) {
        super(transaction);
        this.clickedItem = clickedItem;
        page = Page;
        menu = Page.getMenu();
    }

    public Player getPlayer() { return (Player) getWhoClicked(); }

    @Override
    public HandlerList getHandlers() { return handlerList; }
    public static HandlerList getHandlerList() { return handlerList; }
}
