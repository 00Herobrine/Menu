package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.x00hero.Menu.Pages.Page;

public class MenuNavigationEvent extends MenuEvent implements Cancellable {
    public final Player player;
    public final Page initialPage;
    private boolean isCancelled;

    public MenuNavigationEvent(Player player, Page initialPage, Page navigatedPage) {
        super(player, navigatedPage);
        this.player = player;
        this.initialPage = initialPage;
    }

    public Page getInitialPage() { return initialPage; }
    @Override
    public boolean isCancelled() { return isCancelled; }
    public void setCancelled(boolean b) { isCancelled = b; }
}
