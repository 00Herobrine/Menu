package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.NavigationItem;

public class FixedPage extends Page {
    public FixedPage(int pageNumber, int slots) {
        super(pageNumber, slots);
    }

    @Override
    public Inventory createInventory() {
        if(type != InventoryType.CHEST) inventory = Bukkit.createInventory(null, type, title);
        else inventory = Bukkit.createInventory(null, slots, title);
        for(MenuItem item : items)
            inventory.setItem(item.getSlot(), item);
        return inventory;
    }
}
