package org.x00hero.Menu.Events.Item;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuItemRemoveEvent extends InventoryInteractEvent {
    private final Page page;
    private final MenuItem menuItem;
    private final ItemStack heldItem;
    private static final HandlerList HANDLERS_LIST = new HandlerList();

    public MenuItemRemoveEvent(InventoryView transaction, MenuItem menuItem, Page page) {
        super(transaction);
        this.menuItem = menuItem;
        this.heldItem = transaction.getCursor();
        this.page = page;
    }

    public Player getPlayer() { return (Player) getWhoClicked(); }
    public Page getPage() { return page; }
    public Menu getMenu() { return page.getMenu(); }
    public String getID() { return menuItem.getID(); }
    public MenuItem getMenuItem() { return menuItem; }
    public ItemStack getHeldItem() { return heldItem; }
    public int getSlot() { return menuItem.getSlot(); }
    public int getAmount() { return menuItem.getAmount(); }

    @Override
    public HandlerList getHandlers() { return HANDLERS_LIST; }
    public static HandlerList getHandlerList() { return HANDLERS_LIST; }

}
