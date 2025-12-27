package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Items.MenuItem;

public class DynamicPage extends APage {
    public static final int itemsForNewLine = 7; // the amount of items required until a new line is created in the inventory

    public DynamicPage(int pageNumber, String Title, int slots) {
        super(pageNumber, Title, slots);
    }

    @Override
    public Inventory open() {
        return null;
    }

    @Override
    public MenuItem addItem(MenuItem menuItem) {
        return null;
    }

   /* public DynamicPage(int pageNumber) { super(pageNumber, MAX_SLOTS); }
    public DynamicPage(int pageNumber, int slots) { super(pageNumber, slots); }
    public DynamicPage(int pageNumber, int slots, Menu menu) { super(pageNumber, slots, menu); }*/

    /*@Override
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
    }*/
}
