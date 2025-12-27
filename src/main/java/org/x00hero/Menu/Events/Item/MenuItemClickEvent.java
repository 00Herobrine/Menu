package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.Events.Menu.MenuInteractEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuItemClickEvent extends MenuClickEvent {
    public MenuItemClickEvent(InventoryClickEvent event, Player whoClicked, MenuItem clickedItem, ItemStack heldItem, Page page) {
        super(event, clickedItem, heldItem, page);
    }
}
