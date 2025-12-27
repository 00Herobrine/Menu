package org.x00hero.Menu.Pages;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.x00hero.Main;
import org.x00hero.Menu.Items.MenuItem;

import java.io.File;
import java.util.UUID;

public class InstancedPage extends APage {
    public static String SubPath = "instances";

    public InstancedPage(int pageNumber, String title, int slots) {
        super(pageNumber, title, slots);
    }
    public InstancedPage(String title, int slots) {
        super(title, slots);
    }

    @Override
    public Inventory open() {
        return null;
    }

    @Override
    public void open(Player player) {
        if(!hasInstance(player)) {
            CreatePageInstance(player);
        } else {

        }
    }

    @Override
    public MenuItem addItem(MenuItem menuItem) {
        return null;
    }

    protected boolean CreatePageInstance(Player player) {
        return false;
    }

    public boolean hasInstance(Player player) { return hasInstance(player.getUniqueId()); }
    public boolean hasInstance(UUID uuid) {
        for(File instance : getInstanceFolder().listFiles()) {
            if(instance.getName().equalsIgnoreCase(uuid.toString())) {
                return true;
            }
        }
        return false;
    }
    public File getInstanceFolder() { return getInstanceFolder(true); }
    public File getInstanceFolder(boolean generate) {
        File instanceFolder = new File(Main.plugin.getDataFolder().getAbsolutePath(), SubPath);
        if(!instanceFolder.exists()) {
            instanceFolder.mkdir();
        }
        return instanceFolder;
    }
}
