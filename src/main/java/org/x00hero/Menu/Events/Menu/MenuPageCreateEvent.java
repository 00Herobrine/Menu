package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Pages.Page;

public class MenuPageCreateEvent extends MenuEvent {
    public MenuPageCreateEvent(InventoryView transaction, Page page) {
        super(transaction, page);
    }
}
