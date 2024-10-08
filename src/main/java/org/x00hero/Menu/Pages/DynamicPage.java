package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.NavigationItem;

public class DynamicPage extends Page {
    public DynamicPage(int pageNumber) { super(pageNumber, MAX_SLOTS); }
    public DynamicPage(int pageNumber, int slots) { super(pageNumber, slots); }
    public DynamicPage(int pageNumber, int slots, Menu menu) { super(pageNumber, slots, menu); }

    @Override
    public Inventory createInventory() {
        if(type != InventoryType.CHEST) inventory = Bukkit.createInventory(null, getType(), getTitle());
        else inventory = Bukkit.createInventory(null, getAdjustedAmount(biggestSlot), getTitle());
        for(MenuItem item : items)
            if(item.isVisible()) inventory.setItem(item.getSlot(), item);
        return inventory;
    }

    @Override
    public MenuItem addItem(MenuItem menuItem) {
        //nextPageCheck();
        return null;
    }

    private void nextPageCheck(int slots) {
        if(slots < MAX_SLOTS) return;
        menu.newPage(this);
    }
}
