package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.x00hero.Menu.Pages.Page;

public class MenuNavigationEvent extends MenuEvent implements Cancellable {
    public int navAmount;
    public final Page initialPage;
    private boolean isCancelled;

    public MenuNavigationEvent(Player player, Page initialPage, Page navigatedPage) {
        super(player, navigatedPage);
        this.initialPage = initialPage;
        this.navAmount = initialPage.getNumber() - navigatedPage.getNumber();
    }

    public int getNavAmount() { return navAmount; }
    public Page getInitialPage() { return initialPage; }
    @Override
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean b) { isCancelled = b; }
}
