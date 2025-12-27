package org.x00hero.Menu.Events.Item;

import org.bukkit.event.Cancellable;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Events.Menu.MenuInteractEvent;
import org.x00hero.Menu.Items.MenuItem;

public class MenuItemMoveEvent extends MenuInteractEvent implements Cancellable {
    private boolean isCancelled;

    public MenuItemMoveEvent(InventoryView transaction, MenuItem menuItem) {
        super(transaction, menuItem, menuItem.getPage());
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        isCancelled = b;
    }
}
