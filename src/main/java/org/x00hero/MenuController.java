package org.x00hero;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.x00hero.Menu.Events.Item.MenuItemAddEvent;
import org.x00hero.Menu.Events.Item.MenuItemClickEvent;
import org.x00hero.Menu.Events.Item.MenuItemRemoveEvent;
import org.x00hero.Menu.Events.Item.MenuItemSwapEvent;
import org.x00hero.Menu.Events.Menu.MenuClickEvent;
import org.x00hero.Menu.Events.Menu.MenuCloseEvent;
import org.x00hero.Menu.Events.Menu.MenuOpenEvent;
import org.x00hero.Menu.Menu;
import org.x00hero.Menu.MenuItem;
import org.x00hero.Menu.Pages.Page;

import java.util.HashMap;
import java.util.UUID;

public class MenuController implements Listener {
    private static HashMap<UUID, Page> inMenus = new HashMap<>();
    public static void openPage(Player player, Page page) {
        UUID uuid = player.getUniqueId();
        inMenus.remove(uuid);
        inMenus.put(uuid, page);
        CallEvent(new MenuOpenEvent(player, page));
        player.openInventory(page.createInventory());
    }

    @EventHandler
    public void InventoryHandler(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        int clickedRawSlot = e.getRawSlot();
        int clickedSlot = e.getSlot();
        ItemStack clicked = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        InventoryView view = e.getView();
        Inventory clickedInventory = e.getClickedInventory();
        Page page = MenuController.getPage(player);
        Inventory menuInventory = null;
        InventoryAction action = e.getAction();
        if(page != null) menuInventory = page.getInventory();
        if(view.getTopInventory() != menuInventory) return;
        boolean clickedMenu = clickedInventory == menuInventory;
        MenuItem clickedItem = null;
        if(clickedMenu) {
            clickedItem = page.getItem(clicked);
            CallEvent(new MenuItemClickEvent(player, clickedItem, page, e));
        }
        switch(action) {
            case PLACE_ONE, PLACE_SOME, PLACE_ALL -> {
                if(!clickedMenu) return;
                MenuItem menuItem = page.addItem(cursor, clickedSlot);
                if(menuItem != null) CallEvent(new MenuItemAddEvent(player, menuItem, page, e));
            }
            case PICKUP_ALL -> {
                if(!clickedMenu) return;
                CallEvent(new MenuItemRemoveEvent(player, clickedItem, page, e));
                page.removeItem(clickedItem);
            }
            case MOVE_TO_OTHER_INVENTORY -> {
                if(clickedMenu) {
                    CallEvent(new MenuItemRemoveEvent(player, clickedItem, page, e));
                    page.removeItem(clickedItem);
                } else {
                    MenuItem menuItem = page.addItem(clicked);
                    if(menuItem != null) CallEvent(new MenuItemAddEvent(player, menuItem, page, e));
                }
            }
        }
        CallEvent(new MenuClickEvent(player, page, clickedItem, cursor, e));
        Bukkit.broadcastMessage("Action: " + action);
    }

    @EventHandler
    public void InventoryClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        UUID playerID = player.getUniqueId();
        if(!inMenu(playerID)) return;
        Page page = getPage(playerID);
        removeInMenu(playerID);
        CallEvent(new MenuCloseEvent(player, page, e));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        UUID playerID = e.getPlayer().getUniqueId();
        if(inMenu(playerID)) removeInMenu(playerID);
    }

    public static Page getPage(UUID uuid) { return inMenus.get(uuid); }
    public static Page getPage(Player player) { return getPage(player.getUniqueId()); }
    public static Inventory getInventory(UUID uuid) { return getPage(uuid).getInventory(); }
    public static Inventory getInventory(Player player) { return getInventory(player.getUniqueId()); }
    public static void CallEvent(Event event) { Bukkit.getServer().getPluginManager().callEvent(event); }
    public static void setInMenu(UUID uuid, Page page) { inMenus.put(uuid, page); }
    public static void setInMenu(Player player, Page page) { setInMenu(player.getUniqueId(), page); }
    public static void removeInMenu(UUID uuid) { inMenus.remove(uuid); }
    public static void removeInMenu(Player player) { removeInMenu(player.getUniqueId()); }
    public static boolean inMenu(Player player) { return inMenu(player.getUniqueId()); }
    public static boolean inMenu(UUID uuid) { return inMenus.containsKey(uuid); }
    public static boolean inMenu(UUID uuid, Menu menu) {
        Page page = getPage(uuid);
        return page != null && page.getMenu() == menu;
    }

}
