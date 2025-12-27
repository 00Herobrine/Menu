package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.Pages.APage;
import org.x00hero.Menu.Pages.Page;

public class MenuOpenEvent extends InventoryOpenEvent implements IMenuEvent, Cancellable {
    private final APage page;
    private final Menu menu;
    private boolean isCancelled;
    public MenuOpenEvent(InventoryView transaction, APage page) {
        super(transaction);
        this.page = page;
        this.menu = page.getMenu();
    }

    public APage getPage() { return page; }
    public Menu getMenu() { return menu; }
    @Override
    public boolean isCancelled() { return isCancelled; }
    @Override
    public void setCancelled(boolean b) { isCancelled = b; }
}
