package org.x00hero.Menu.Pages;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Items.NavigationItem;
import org.x00hero.Menu.Menu;

import java.util.List;

public abstract class APage {
    // Constants
    public static final int MIN_SLOTS = 1;
    public static final int MAX_SLOTS = 54;
    public static final NavigationItem BACK_NAV = new NavigationItem(Material.ORANGE_STAINED_GLASS_PANE, "&eBack", 45,-1, -5);
    public static final NavigationItem FORWARD_NAV = new NavigationItem(Material.LIME_STAINED_GLASS_PANE, "&aForward", 53, 1, 5);

    // Page Info
    private int pageNumber; // Attempts to page to number when added to menu or finalized Number once added
    private int slots;
    private int biggestSlot;
    private Menu menu; // the menu it's part of
    private String title;
    private String permission = null; // the player permission required to view page
    private boolean isAddingAllowed, isRemovalAllowed;
    private List<MenuItem> items;

    public APage(int pageNumber, String title, int slots) {
        this.pageNumber = pageNumber;
        this.title = title;
        this.slots = slots;
    }
    public APage(String title, int slots) {
        this.pageNumber = Menu.UNPAGED_SLOT;
        this.title = title;
        this.slots = slots;
    }

    public abstract Inventory open();
    public abstract MenuItem addItem(MenuItem menuItem);

    public boolean isFull() { return items.size() >= slots; }

    public int getSlots() { return slots; }
    public void setSlots(int slots) { this.slots = slots; }

    public int getBiggestSlot() { return biggestSlot; }
    public void setBiggestSlot(int biggestSlot) { this.biggestSlot = biggestSlot; }

    public String getPermission() { return permission; }
    public void setPermission(String permission) { this.permission = permission; }
    public boolean hasPermission() { return permission != null; }

    public int getPageNumber() { return pageNumber; }
    public void setPageNumber(int pageNumber) { this.pageNumber = pageNumber; }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public boolean isAddingAllowed() { return isAddingAllowed; }
    public void setAddingAllowed(boolean addingAllowed) { isAddingAllowed = addingAllowed; }

    public boolean isRemovalAllowed() { return isRemovalAllowed; }
    public void setRemovalAllowed(boolean removalAllowed) {isRemovalAllowed = removalAllowed; }

    public List<MenuItem> getItems() { return items; }
    public void setItems(List<MenuItem> items) { this.items = items; }
}
