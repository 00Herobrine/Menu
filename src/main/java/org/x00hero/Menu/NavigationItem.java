package org.x00hero.Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class NavigationItem extends MenuItem {
    public boolean navToPage = false;
    public int amount = 1;
    public NavigationItem(Material material, int slot, int navAmount) {
        super(material, slot);
        this.amount = navAmount;
        isCancelClick = true;
    }
    public NavigationItem(Material material, int slot, int navAmount, boolean navToPage) {
        super(material, slot);
        this.amount = navAmount;
        this.navToPage = navToPage;
        isCancelClick = true;
    }

    public void Navigate(Player player) {
        Menu menu = page.getMenu();
        if(navToPage) menu.open(player, amount);
        else menu.open(player, page.getNumber() + amount);
    }
}
