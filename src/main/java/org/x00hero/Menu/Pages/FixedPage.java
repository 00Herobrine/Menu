package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.Items.MenuItem;

public class FixedPage extends APage {
    public FixedPage(int pageNumber, String title, int slots) {
        super(pageNumber, title, slots);
    }
    public FixedPage(String title, int slots) {
        super(title, slots);
    }

    @Override
    public Inventory open() {
        return null;
    }

/*    @Override
    public Inventory createInventory() {
        if(type != InventoryType.CHEST) inventory = Bukkit.createInventory(null, type, title);
        else inventory = Bukkit.createInventory(null, slots, title);
        for(MenuItem item : items)
            inventory.setItem(item.getSlot(), item);
        return inventory;
    }*/

    @Override
    public MenuItem addItem(MenuItem menuItem) {
        return null;
    }
}
