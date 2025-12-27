package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuItemSwapEvent extends InventoryInteractEvent {
    private final Page page;
    private final MenuItem menuItem;
    private final MenuItem swappedItem;
    private final Player whoClicked;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MenuItemSwapEvent(Player whoClicked, MenuItem menuItem, MenuItem swappedItem, Page page, InventoryInteractEvent event) {
        super(event.getView());
        this.whoClicked = whoClicked;
        this.menuItem = menuItem;
        this.swappedItem = swappedItem;
        this.page = page;
        if(!menuItem.isCancelClick() || !swappedItem.isCancelClick()) event.setCancelled(true);
    }

    public Player getPlayer() { return whoClicked; }
    public Page getPage() { return page; }
    public Menu getMenu() { return page.getMenu(); }
    public String getID() { return menuItem.getID(); }
    public MenuItem getMenuItem() { return menuItem; }
    public MenuItem getSwappedItem() { return swappedItem; }
    public int getSlot() { return menuItem.getSlot(); }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}
