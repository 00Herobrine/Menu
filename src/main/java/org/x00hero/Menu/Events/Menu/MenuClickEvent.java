package org.x00hero.Menu.Events.Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.Pages.Page;

public class MenuClickEvent extends InventoryClickEvent implements Cancellable {
    public final Player player;
    public final Menu menu;
    public final Page page;
    public final MenuItem clickedItem;
    public final MenuItem heldItem;
    private static final HandlerList handlerList = new HandlerList();
    protected InventoryClickEvent event;

    public MenuClickEvent(Player player, Page page, MenuItem clickedItem, ItemStack heldItem, InventoryClickEvent event) {
        super(event.getView(), event.getSlotType(), event.getSlot(), event.getClick(), event.getAction(), event.getHotbarButton());
        this.player = player;
        this.page = page;
        this.menu = page.getMenu();
        this.clickedItem = clickedItem != null && clickedItem.getType() != Material.AIR ? new MenuItem(clickedItem, event.getSlot()) : null;
        this.heldItem = heldItem != null && heldItem.getType() != Material.AIR ? new MenuItem(heldItem, event.getSlot()) : null;
        this.event = event;
    }

    public Player getPlayer() { return player; }
    public Menu getMenu() { return menu; }
    public Page getPage() { return page; }
    public MenuItem getClickedItem() { return clickedItem; }
    public MenuItem getHeldItem() { return heldItem; }

    @Override
    public HandlerList getHandlers() { return handlerList; }
    public static HandlerList getHandlerList() { return handlerList; }
}
