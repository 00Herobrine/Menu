package org.x00hero.Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NavigationItem extends MenuItem {
    protected boolean navToPage = false;
    public static NavigationItem backItem = new NavigationItem(new MenuItem(Material.ORANGE_STAINED_GLASS_PANE, "&eBack"), 45,1);
    public static NavigationItem forwardItem = new NavigationItem(new MenuItem(Material.LIME_STAINED_GLASS_PANE, "&aForward"), 53, 1);
    protected int amount = 1;

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
    public NavigationItem(ItemStack itemStack, int slot, int navAmount) {
        super(itemStack);
        this.slot = slot;
        this.amount = navAmount;
    }
    public NavigationItem(ItemStack itemStack, int slot, int navAmount, boolean navToPage) {
        super(itemStack);
        this.slot = slot;
        this.amount = navAmount;
        this.navToPage = navToPage;
    }
    public NavigationItem(MenuItem menuItem, int navAmount) {
        super(menuItem);
        this.amount = navAmount;
    }
    public NavigationItem(MenuItem menuItem, int navAmount, boolean navToPage) {
        super(menuItem);
        this.amount = navAmount;
        this.navToPage = navToPage;
    }

    public void navigate(Player player) {
        Menu menu = page.getMenu();
        if(navToPage) menu.open(player, amount);
        else menu.open(player, page.getNumber() + amount);
    }
}
