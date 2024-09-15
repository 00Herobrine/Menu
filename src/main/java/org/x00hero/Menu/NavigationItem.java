package org.x00hero.Menu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.x00hero.Menu.Pages.Page;

public class NavigationItem extends MenuItem implements Cloneable {
    protected boolean navToPage = false;
    protected int shiftAmount = 5;
    protected int navAmount = 1;

    public NavigationItem(Material material, int slot, int navAmount, int shiftAmount) {
        super(material, 1, slot);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
        isCancelClick = true;
    }
    public NavigationItem(Material material, int slot, int navAmount, int shiftAmount, boolean navToPage) {
        super(material, 1, slot);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
        this.navToPage = navToPage;
        isCancelClick = true;
    }
    public NavigationItem(Material material, String name, int slot, int navAmount, int shiftAmount) {
        super(material, 1, slot, name);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
        isCancelClick = true;
    }
    public NavigationItem(Material material, String name, int slot, int navAmount, boolean navToPage) {
        super(material, 1, slot, name);
        this.navAmount = navAmount;
        this.navToPage = navToPage;
        isCancelClick = true;
    }
    public NavigationItem(Material material, String name, int slot, int navAmount, int shiftAmount, boolean navToPage) {
        super(material, 1, slot, name);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
        this.navToPage = navToPage;
        isCancelClick = true;
    }
    public NavigationItem(MenuItem menuItem, int navAmount, int shiftAmount) {
        super(menuItem);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
    }
    public NavigationItem(MenuItem menuItem, int navAmount, int shiftAmount, boolean navToPage) {
        super(menuItem);
        this.navAmount = navAmount;
        this.shiftAmount = shiftAmount;
        this.navToPage = navToPage;
    }

    public int getNavAmount() { return navAmount; }
    public int getShiftAmount() { return shiftAmount; }

    public void navigate(Player player, boolean isShift) {
        Page page = MenuController.getPage(player);
        Menu menu = page.getMenu();
        int navAmount = isShift ? shiftAmount : this.navAmount;
        if(navToPage) menu.open(player, Math.max(0, Math.min(navAmount, menu.getMaxPages())));
        else menu.open(player, Math.max(0, Math.min(page.getNumber() + navAmount, menu.getMaxPages())));
    }

    @Override
    public NavigationItem clone() {
        NavigationItem clone = (NavigationItem) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        clone.navToPage = navToPage;
        clone.navAmount = navAmount;
        clone.shiftAmount = shiftAmount;
        return clone;
    }
}
