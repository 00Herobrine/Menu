package org.x00hero.Menu.Events.Item;

import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Events.Menu.MenuInteractEvent;
import org.x00hero.Menu.Items.MenuItem;

public class MenuItemEvent extends MenuInteractEvent {
    private MenuItem menuItem;

    public MenuItemEvent(InventoryView transaction, MenuItem menuItem) {
        super(transaction, menuItem, menuItem.getPage());
    }

    public MenuItem getMenuItem() { return menuItem; }
}
