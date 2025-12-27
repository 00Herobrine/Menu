package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.inventory.InventoryView;
import org.x00hero.Menu.Pages.Page;

public class MenuNavigationEvent extends MenuEvent implements Cancellable {
    public int navAmount;
    public final Page initialPage;
    private boolean isCancelled;

    public MenuNavigationEvent(InventoryView transaction, Page initialPage, Page navigatedPage) {
        super(transaction, navigatedPage);
        this.initialPage = initialPage;
        this.navAmount = initialPage.getPageNumber() - navigatedPage.getPageNumber();
    }

    public Player getPlayer() { return (Player) transaction.getPlayer(); }
    public int getNavAmount() { return navAmount; }
    public Page getInitialPage() { return initialPage; }
    @Override
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean b) { isCancelled = b; }
}
