package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Pages.Page;

public class MenuCloseEvent extends InventoryCloseEvent implements IMenuEvent, Cancellable {
    private final Menu menu;
    private final Page page;
    public boolean isCancelled = false;

    public MenuCloseEvent(InventoryCloseEvent e, Page page) {
        super(e.getView());
        this.page = page;
        this.menu = page.getMenu();
    }

    public Menu getMenu() { return menu; }
    public Page getPage() { return page; }
    @Override
    public boolean isCancelled() { return isCancelled; }
    @Override
    public void setCancelled(boolean b) { isCancelled = b; getPlayer().openInventory(getInventory()); } // might need to delay this by a tick

}
