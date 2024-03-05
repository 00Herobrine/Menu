package org.x00hero.Menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Pages.DynamicPage;
import org.x00hero.Menu.Pages.Page;

import java.util.HashMap;

public class Menu extends HashMap<Integer, Page> {
    private String title;
    private int maxPages = 42069;
    private int maxPageSlots = Page.MAX_SLOTS;
    private int maxSlots = -1;

    public Menu(String title) { this.title = title; }
    public Menu(String title, int maxPages) {
        this.title = title;
        this.maxPages = maxPages;
    }
    public Menu(String title, int maxPages, int maxPageSlots) {
        this.title = title;
        this.maxPages = maxPages;
        this.maxPageSlots = maxPageSlots;
    }
    public Menu(String title, int maxPages, int maxPageSlots, int maxSlots) {
        this.title = title;
        this.maxPages = maxPages;
        this.maxPageSlots = maxPageSlots;
        this.maxSlots = maxSlots;
    }
    public Menu(int maxSlots, String title) {
        this.title = title;
        this.maxSlots = maxSlots;
    }

    public Page getAvailablePage() {
        for(int i = 0; i < maxPages; i++) {
            Page page = tryGetPage(i);
            if(!page.isFull()) return page;
        }
        return null;
    }
    public Page tryGetPage(int pageNumber) {
        Page page = get(pageNumber);
        if(page == null) return createPage(pageNumber);
        return page;
    }
    public Page getPage(int pageNumber) { return get(pageNumber); }
    public Page createPage(int pageNumber) {
        Page page = new DynamicPage(pageNumber, maxPageSlots).setMenu(this);
        put(pageNumber, page);
        return page;
    }
    public MenuItem addItem(ItemStack itemStack) { return addItem(new MenuItem(itemStack)); }
    public MenuItem addItem(ItemStack itemStack, int slot) { return addItem(new MenuItem(itemStack, slot)); }
    public MenuItem addItem(MenuItem menuItem) { return addItem(menuItem, getAvailablePage()); }
    public MenuItem addItem(MenuItem menuItem, Page page) { return page.addItem(menuItem); }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getMaxPages() { return maxPages; }
    public void setMaxPages(int maxPages) { this.maxPages = maxPages; }

    public int getMaxPageSlots() { return maxPageSlots; }
    public void setMaxPageSlots(int maxPageSlots) { this.maxPageSlots = maxPageSlots; }

    public int getMaxSlots() { return maxSlots; }
    public void setMaxSlots(int maxSlots) { this.maxSlots = maxSlots; }

    public void open(Player player) { open(player, 0); }
    public void open(Player player, int page) { tryGetPage(page).open(player); }
    public boolean isFirstPage(int page) { return page == 0; }
    public boolean isLastPage(int page) { return page == size() - 1; }
    public boolean isOnlyPage(int page) { return size() == 1; }
    public boolean hasNextPage(int page) { return size() - 1 > page || get(page + 1) != null; }
    public boolean hasPreviousPage(int page) { return page - 1 > 0 || get(page - 1) != null; }
}
