package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Events.Menu.MenuInteractEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuItemAddEvent extends MenuInteractEvent {
    private final Menu menu;
    private final Page page;
    private final MenuItem menuItem;

    public MenuItemAddEvent(InventoryView transaction, MenuItem menuItem, Page page) {
        super(transaction, menuItem, page);
        this.menuItem = menuItem;
        this.page = page;
        this.menu = page.getMenu();
    }

    public Player getPlayer() { return (Player) transaction.getPlayer(); }
    public Menu getMenu() { return menu; }
    public Page getPage() { return page; }
    public String getID() { return menuItem.getID(); }
    public MenuItem getMenuItem() { return menuItem; }
    public int getSlot() { return menuItem.getSlot(); }

}
