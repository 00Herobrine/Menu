package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.x00hero.Menu.Pages.Page;

public class MenuOpenEvent extends MenuEvent implements Cancellable {
    private boolean isCancelled;
    public MenuOpenEvent(Player player, Page page) {
        super(player, page);
    }

    @Override
    public boolean isCancelled() { return isCancelled; }
    @Override
    public void setCancelled(boolean b) { isCancelled = b; }
}
