package org.x00hero.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.x00hero.Menu.Pages.Page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItem extends ItemStack {
    public static final int UNPAGED_SLOT = -1;
    protected int slot = UNPAGED_SLOT;
    protected boolean isEnabled = true, isCancelClick = false;
    protected Page page;
    protected String ID;
    protected static final String Key = "MenuItem";
    public MenuItem() { super(); }
    public MenuItem(ItemStack itemStack) {
        super(itemStack);
        if(itemStack.getItemMeta() != null && itemStack.getItemMeta().hasDisplayName()) setName(itemStack.getItemMeta().getDisplayName());
        setLore(itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore() ? itemStack.getItemMeta().getLore() : new ArrayList<>());
    }
    public MenuItem(MenuItem menuItem) {
        super(menuItem);
        this.slot = menuItem.slot;
        this.isEnabled = menuItem.isEnabled;
        this.isCancelClick = menuItem.isCancelClick;
        this.page = menuItem.page;
    }
    public MenuItem(MenuItem menuItem, int slot) {
        super(menuItem);
        this.slot = slot;
        this.isEnabled = menuItem.isEnabled;
        this.isCancelClick = menuItem.isCancelClick;
        this.page = menuItem.page;
    }
    public MenuItem(ItemStack itemStack, int slot) {
        super(itemStack);
        this.slot = slot;
    }
    public MenuItem(Material material) { super(material); }
    public MenuItem(Material material, int amount) { super(material, amount); }
    public MenuItem(Material material, String name) {
        super(material);
        setName(name);
    }
    public MenuItem(Material material, int amount, String name) {
        super(material, amount);
        setName(name);
    }
    public MenuItem(Material material, String name, String lore) {
        super(material);
        setName(name);
        setLore(lore);
    }
    public MenuItem(Material material, int amount, String name, String lore) {
        super(material, amount);
        setName(name);
        setLore(lore);
    }
    public Object getCustomData(NamespacedKey key, PersistentDataType type) { return getItemMeta().getPersistentDataContainer().getOrDefault(key, type, "1"); }
    public void setCustomData(NamespacedKey key, PersistentDataType type, Object input) { setCustomData(this, key, type, input); }
    public static void setCustomData(ItemStack itemStack, NamespacedKey key, PersistentDataType type, Object input) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null)
            itemMeta.getPersistentDataContainer().set(key, type, input);
        itemStack.setItemMeta(itemMeta);
    }
    public static String formatColors(String string) { return formatColors(string, '&'); }
    public static String formatColors(String string, char translateChar) { return ChatColor.translateAlternateColorCodes(translateChar, string); }
    public String getName() { return getItemMeta().hasDisplayName() ? getItemMeta().getDisplayName() : getType().name(); }
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }
    public void setName(String name) { ItemMeta meta = getItemMeta(); meta.setDisplayName(formatColors(name)); super.setItemMeta(meta); }
    public void setLore(String rawLore) { super.getItemMeta().setLore(Arrays.stream(formatColors(rawLore).split("\n")).toList()); }
    public void setLore(List<String> lore) { ItemMeta meta = getItemMeta() == null ? Bukkit.getItemFactory().getItemMeta(getType()) : getItemMeta(); meta.setLore(lore); super.setItemMeta(meta); }

    public Page getPage() { return page; }
    public void setPage(Page page) { this.page = page; }
    public boolean isPaged() { return page != null; }
    public int getSlot() { return slot; }
    public void setSlot(int slot) { this.slot = slot; }
    public boolean isCancelClick() { return isCancelClick; }
    public void setCancelClick(boolean cancelClick) { isCancelClick = cancelClick; }

    public boolean isEnabled() { return isEnabled; }
    public boolean isUnslotted() { return slot == -1; }
}
