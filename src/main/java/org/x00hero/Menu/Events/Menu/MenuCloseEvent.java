package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.x00hero.Menu.Pages.Page;

public class MenuCloseEvent extends MenuEvent implements Cancellable {
    private final InventoryCloseEvent inventoryCloseEvent;
    private boolean isCancelled;

    public MenuCloseEvent(Player player, Page page, InventoryCloseEvent e) {
        super(player, page);
        this.inventoryCloseEvent = e;
    }

    @Override
    public boolean isCancelled() { return isCancelled; }
    @Override
    public void setCancelled(boolean b) { isCancelled = b; player.openInventory(inventoryCloseEvent.getInventory()); } // might need to delay this by a tick
}
