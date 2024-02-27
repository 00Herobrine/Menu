package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.NavigationItem;

public class DynamicPage extends Page {
    public DynamicPage(int pageNumber) {
        super(pageNumber, MAX_SLOTS);
    }
    public DynamicPage(int pageNumber, int slots) {
        super(pageNumber, slots);
    }

    @Override
    public Inventory createInventory() {
        if(type != InventoryType.CHEST) inventory = Bukkit.createInventory(null, getType(), getTitle());
        else inventory = Bukkit.createInventory(null, getAdjustedAmount(biggestSlot), getTitle());
        for(MenuItem item : items)
            inventory.setItem(item.getSlot(), item);
        for(NavigationItem navItem : navItems)
            inventory.setItem(navItem.getSlot(), navItem);
        return inventory;
    }
}
