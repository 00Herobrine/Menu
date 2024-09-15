package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.x00hero.Menu.Pages.Page;

public class MenuPageDeleteEvent extends MenuEvent {
    public MenuPageDeleteEvent(Player player, Page page) {
        super(player, page);
    }
}
