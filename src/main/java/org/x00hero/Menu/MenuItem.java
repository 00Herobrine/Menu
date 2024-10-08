package org.x00hero.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.x00hero.Config;
import org.x00hero.Menu.Pages.Page;
import org.x00hero.Validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuItem extends ItemStack implements Cloneable {
    public static final int UNPAGED_SLOT = -1;
    protected int slot = UNPAGED_SLOT;
    protected int intendedSlot = UNPAGED_SLOT;
    protected boolean isEnabled = true, isCancelClick = false, isDynamic = false;
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
        this.intendedSlot = menuItem.intendedSlot;
        this.isEnabled = menuItem.isEnabled;
        this.isCancelClick = menuItem.isCancelClick;
        this.page = menuItem.page;
        this.ID = menuItem.ID;
        this.isDynamic = menuItem.isDynamic;
    }
    public MenuItem(MenuItem menuItem, int slot) {
        super(menuItem);
        this.slot = slot;
        this.intendedSlot = slot;
        this.isEnabled = menuItem.isEnabled;
        this.isCancelClick = menuItem.isCancelClick;
        this.page = menuItem.page;
        this.ID = menuItem.ID;
        this.isDynamic = menuItem.isDynamic;
    }
    public MenuItem(ItemStack itemStack, int slot) {
        super(itemStack);
        this.slot = slot;
    }
    public MenuItem(Material material) { super(material); }
    public MenuItem(Material material, int amount) { super(material, amount); }
    public MenuItem(Material material, int amount, int slot) {
        super(material, amount);
        this.slot = slot;
    }
    public MenuItem(Material material, int amount, int slot, String name) {
        super(material, amount);
        this.slot = slot;
        setName(name);
    }
    public MenuItem(Material material, int amount, String name) {
        super(material, amount);
        setName(name);
    }
    public MenuItem(Material material, int amount, String name, String lore) {
        super(material, amount);
        setName(name);
        setLore(lore);
        setCustomData(Config.getMenuItemKey(), PersistentDataType.STRING, Validator.encode(Config.getMenuItemString()));
    }
    public MenuItem(Material material, String name) {
        super(material);
        setName(name);
    }
    public MenuItem(Material material, String name, String lore) {
        super(material);
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

    public boolean isVisible() { return isSlotted() && isEnabled; }
    public boolean isEnabled() { return isEnabled; }
    public boolean isSlotted() { return slot != UNPAGED_SLOT; }

    @Override
    public MenuItem clone() {
        MenuItem clone = (MenuItem) super.clone();
        // TODO: copy mutable state here, so the clone can't change the internals of the original
        clone.slot = slot;
        clone.intendedSlot = intendedSlot;
        clone.page = page;
        clone.ID = ID;
        clone.isEnabled = isEnabled;
        clone.isCancelClick = isCancelClick;
        clone.isDynamic = isDynamic;
        clone.setName(getName());
        return clone;
    }
}
