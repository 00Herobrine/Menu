package org.x00hero.Menu.Events.Menu;

import org.bukkit.entity.Player;
import org.x00hero.Menu.Pages.Page;

public class MenuPageCreateEvent extends MenuEvent {
    public MenuPageCreateEvent(Player player, Page page) {
        super(player, page);
    }
}
