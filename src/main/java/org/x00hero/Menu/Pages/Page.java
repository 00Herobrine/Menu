package org.x00hero.Menu.Pages;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Menu;
import org.x00hero.MenuController;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.NavigationItem;

import java.util.ArrayList;
import java.util.List;

public abstract class Page {
    public static final int MIN_SLOTS = 1;
    public static final int MAX_SLOTS = 54;
    protected boolean allowAddition = true, allowRemoval = true;
    protected String title;
    protected List<MenuItem> items;
    protected List<NavigationItem> navItems = new ArrayList<>();
    protected int biggestSlot;
    protected int slots = MIN_SLOTS;
    protected final int number;
    protected final InventoryType type;
    protected Menu menu;
    protected Inventory inventory;

    public static int getMaxSlots() { return MAX_SLOTS; }
    public static int getMinSlots() { return MIN_SLOTS; }

    public Page(int pageNumber) {
        this.number = pageNumber;
        this.items = new ArrayList<>();
        this.type = InventoryType.CHEST;
    }
    public Page(int pageNumber, String title) {
        this.number = pageNumber;
        this.items = new ArrayList<>();
        this.type = InventoryType.CHEST;
        setTitle(title);
    }

    public Page(int pageNumber, InventoryType type) {
        this.number = pageNumber;
        this.items = new ArrayList<>();
        this.type = type;
        setSlots(type.getDefaultSize());
    }
    public Page(int pageNumber, int slots) {
        this.number = pageNumber;
        this.items = new ArrayList<>();
        this.type = InventoryType.CHEST;
        setSlots(slots);
    }
    public Page(int pageNumber, int slots, String title) {
        this.number = pageNumber;
        this.items = new ArrayList<>();
        this.type = InventoryType.CHEST;
        setSlots(slots);
        setTitle(title);
    }

    public MenuItem addItem(ItemStack itemStack) { return addItem(itemStack, MenuItem.UNPAGED_SLOT); }
    public MenuItem addItem(ItemStack itemStack, int slot) { return addItem(new MenuItem(itemStack, slot)); }
    public MenuItem addItem(MenuItem menuItem, int slot) { return addItem(new MenuItem(menuItem, slot)); }
    public MenuItem addItem(MenuItem menuItem) {
        if(menuItem.isUnslotted()) menuItem.setSlot(getAvailableSlot(menuItem));
        if(!isValidSlot(menuItem.getSlot()) && allowAddition) return null;
        if(items.contains(menuItem)) Bukkit.broadcastMessage("Similar");
        items.add(menuItem);
        updateBiggest(menuItem.getSlot());
        return menuItem;
    }
    public void removeItem(int slot) { items.removeIf(item -> item.getSlot() == slot); }
    public void removeItem(MenuItem menuItem) { items.remove(menuItem); }

    public boolean isValidSlot(int slot) { return slot >= MIN_SLOTS - 1 && slot <= slots; }
    public boolean isFull() { return items.size() >= slots; }
    public Menu getMenu() { return menu; }
    public Page setMenu(Menu menu) { this.menu = menu; return this; }
    public String getTitle() { return title == null ? menu.getTitle() : title; }
    public void setTitle(String title) { this.title = title; }
    public InventoryType getType() { return type; }
    public Inventory getInventory() { return inventory; }
    public abstract Inventory createInventory();
    public void setItems(List<MenuItem> items) { this.items = items; }
    public void setNavItems(List<NavigationItem> navItems) { this.navItems = navItems; }
    public MenuItem getItem(ItemStack itemStack) { for(MenuItem item : items) if(item.equals(itemStack)) return item; return null; }
    public MenuItem getItem(int slot) { for(MenuItem item : items) if(item.getSlot() == slot) return item; return null; }
    public List<MenuItem> getItems() { return items; }
    public List<NavigationItem> getNavItems() { return navItems; }
    public void open(Player player) { MenuController.openPage(player, this); }

    public int getNumber() { return number; }
    public static int getAdjustedAmount(Integer slots) { return (int) (Math.ceil((double) Math.max(slots, 1) / 9)) * 9; }
    public int getSlots() { return slots; }
    public void setSlots(int slots) {
        if (slots < MIN_SLOTS || slots > MAX_SLOTS)
            throw new IllegalArgumentException("Invalid number of slots. Slots must be between " + MIN_SLOTS + " and " + MAX_SLOTS + ".");
        this.slots = slots;
    }
    //public void setSlots(int slots) { this.slots = Math.max(MIN_SLOTS, Math.min(MAX_SLOTS, slots)); }
    private void updateBiggest(int slot) { if(biggestSlot < slot) biggestSlot = slot; }
    public int getBiggestSlot() { return biggestSlot; }
    public boolean isAvailableSlot(int slot) {
        if(!isValidSlot(slot)) return false;
        for(MenuItem item : items)
            if(item.getSlot() == slot) return false;
        return true;
    }
    public boolean isAvailableSlot(int slot, List<Integer> blacklist) {
        if(!isValidSlot(slot)) return false;
        for(MenuItem item : items)
            if(item.getSlot() == slot || blacklist.contains(slot)) return false;
        return true;
    }
    public int getAvailableSlot(MenuItem menuItem) {
        List<Integer> taken = new ArrayList<>();
        for(MenuItem item : items) {
            if(item.isSimilar(menuItem))
                if(item.getAmount() < item.getMaxStackSize())
                    return item.getSlot();
            taken.add(item.getSlot());
        }
        for (int i = 0; i < slots; i++)
            if(isAvailableSlot(i, taken)) return i;
        return MenuItem.UNPAGED_SLOT;
    }
    public int getAvailableSlot() {
        List<Integer> taken = new ArrayList<>();
        for(MenuItem item : items) { taken.add(item.getSlot()); }
        for (int i = 0; i < slots; i++)
            if(isAvailableSlot(i, taken)) return i;
        return MenuItem.UNPAGED_SLOT;
    }
}
