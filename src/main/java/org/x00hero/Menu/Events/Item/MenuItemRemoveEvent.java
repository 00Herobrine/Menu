package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuItemRemoveEvent extends InventoryInteractEvent implements Cancellable {
    private final Page page;
    private final MenuItem menuItem;
    private final Player player;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MenuItemRemoveEvent(Player whoClicked, MenuItem menuItem, Page page, InventoryInteractEvent event) {
        super(event.getView());
        this.player = whoClicked;
        this.menuItem = menuItem;
        this.page = page;
    }

    public Player getPlayer() { return player; }
    public Page getPage() { return page; }
    public Menu getMenu() { return page.getMenu(); }
    public String getID() { return menuItem.getID(); }
    public MenuItem getMenuItem() { return menuItem; }
    public int getSlot() { return menuItem.getSlot(); }
    public int getAmount() { return menuItem.getAmount(); }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }
}
