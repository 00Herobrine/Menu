package org.x00hero.Menu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Items.MenuItem;
import org.x00hero.Menu.Pages.*;
import org.x00hero.Menu.Pages.Page;

import java.util.HashMap;

public class Menu extends HashMap<Integer, APage> {
    public static final int UNPAGED_SLOT = -1;
    private String title;
    private int defaultPage = 0;
    private int maxPages = 42069;
    private int maxPageSlots = APage.MAX_SLOTS;
    private int maxSlots = -1;
    private String permission = null;

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

    public int getAvailablePageNumber() {
        for(int i = 0; i < maxPages; i++) {
            if(!containsKey(i)) return i;
        }
        return UNPAGED_SLOT;
    }
    public APage getAvailablePage() {
        for(int i = 0; i < maxPages; i++) {
            APage page = getCreatePage(i);
            if(!page.isFull()) return page;
        }
        return null;
    }
    public APage getCreatePage(int pageNumber) {
        APage page = get(pageNumber);
        if(page == null) return createPage(pageNumber, PageType.Dynamic);
        return page;
    }
    public APage getPage(int pageNumber) { return get(pageNumber); }
    public APage createPage(int pageNumber, PageType type) {
        APage page = null;
        switch(type) {
            case Fixed -> page = new FixedPage(pageNumber, title, APage.MAX_SLOTS);
            case Dynamic -> page = new DynamicPage(pageNumber, title, APage.MAX_SLOTS);
            case Instanced -> page = new InstancedPage(pageNumber, title, APage.MAX_SLOTS);
            default -> throw new IllegalArgumentException("Unsupported page type: " + type);
        }
        setPage(pageNumber, page);
        return page;
    }
    public APage addPage(APage page) {
        int pageNumber = page.getPageNumber();
        if(pageNumber == UNPAGED_SLOT || pageNumber > maxPages || containsKey(pageNumber)) { // Doesn't have a slot, auto-page
            pageNumber = getAvailablePageNumber();
            page.setPageNumber(pageNumber);
        }
        page.setMenu(this);
        if(pageNumber == UNPAGED_SLOT) return null; // No available page
        return page;
    }
    public void setPage(APage page) {
        put(page.getPageNumber(), page);
    }
    public void setPage(int pageNumber, APage page) {
        if(pageNumber == Menu.UNPAGED_SLOT) { // Probably attempt an auto-page?
            pageNumber = getAvailablePageNumber();
            if(pageNumber == Menu.UNPAGED_SLOT) return; // no available pages
        }
        page.setPageNumber(pageNumber);
        put(pageNumber, page);
    }

    public MenuItem addItem(ItemStack itemStack) { return addItem(new MenuItem(itemStack)); }
    public MenuItem addItem(ItemStack itemStack, int slot) { return addItem(new MenuItem(itemStack, slot)); }
    public MenuItem addItem(MenuItem menuItem) { return addItem(menuItem, getAvailablePage()); }
    public MenuItem addItem(MenuItem menuItem, APage page) { return page.addItem(menuItem); }
    public MenuItem[] addItem(MenuItem... menuItems) {
        APage availablePage = getAvailablePage();
        for(MenuItem menuItem : menuItems) {
            if(availablePage.isFull()) availablePage = getAvailablePage();
            availablePage.addItem(menuItem);
        }
        return null;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getMaxPages() { return maxPages; }
    public void setMaxPages(int maxPages) { this.maxPages = maxPages; }

    public int getMaxPageSlots() { return maxPageSlots; }
    public void setMaxPageSlots(int maxPageSlots) { this.maxPageSlots = maxPageSlots; }

    public int getMaxSlots() { return maxSlots; }
    public void setMaxSlots(int maxSlots) { this.maxSlots = maxSlots; }

    public int getDefaultPage() { return defaultPage; }
    public void setDefaultPage(int defaultPage) { this.defaultPage = defaultPage; }
    /*    private void updateNavItems() { for(APage page : values()) updateNavItems(page); }
    private void updateNavItems(APage page) { page.setNavItems(); }*/

    public String getPermission() { return permission; }
    public void setPermission(String permission) { this.permission = permission; }
    public boolean hasPermission() { return permission != null; }

    public void open(Player player) { open(player, defaultPage); }
    public void open(Player player, int page) { getCreatePage(page).open(player); }

    public boolean isFirstPage(int page) { return page == 0; }
    public boolean isLastPage(int page) { return page == size() - 1; }
    public boolean isOnlyPage(int page) { return size() == 1; }
    public boolean hasNextPage(int page) { return size() - 1 > page || get(page + 1) != null; }
    public boolean hasPreviousPage(int page) { return page - 1 > 0 || get(page - 1) != null; }

    public APage getNextPage(APage page) { return getNextPage(page.getPageNumber()); }
    public APage getNextPage(int page) { return get(page + 1); }

    public APage getPreviousPage(APage page) { return getPreviousPage(page.getPageNumber()); }
    public APage getPreviousPage(int page) { return get(page - 1); }

}
