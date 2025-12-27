package org.x00hero.Menu;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.x00hero.Main;
import org.x00hero.Menu.Pages.Page;

import java.io.File;

public class MenuManager {
    public static String UNTITLED_MENU = "UNTITLED_MENU_420";
    public static String SubPath = "menus";
    public static void Initialize() {
        InitializeMenus();
    }

    private static void InitializeMenus() {
        File menuFolder = new File(Main.plugin.getDataFolder().getAbsolutePath(), SubPath);
        if(!menuFolder.exists()) return;
        for(File menuFile : menuFolder.listFiles()) {
            InitializeMenu(menuFile);
        }
    }

    private static void InitializeMenu(File menuFile) {
        YamlConfiguration menuConfig = null;
        if(menuFile.isDirectory()) {
            menuConfig = YamlConfiguration.loadConfiguration(new File(menuFile.getAbsolutePath(), "config.yml"));
        } else if(menuFile.isFile() && isCorrectFileExtension(menuFile, ".yml")) {
            menuConfig = YamlConfiguration.loadConfiguration(menuFile);
        }
        if(menuConfig == null) return;

        String ID = menuFile.getName();
        String title = menuConfig.getString("title", UNTITLED_MENU);
        Menu menu = new Menu(ID);
        ConfigurationSection pages = menuConfig.getConfigurationSection("pages");
        if(pages != null) {
            InitializePages(menu, pages);
        }
    }

    private static void InitializePages(Menu menu, ConfigurationSection section) {
        for(String sectionKey : section.getKeys(false)) {
            ConfigurationSection pageConfig = section.getConfigurationSection(sectionKey);
            if(pageConfig == null) continue;
            InitializePage(menu, pageConfig);
        }
    }
    private static void InitializePage(Menu menu, ConfigurationSection section) {
        String type = section.getString("type", "FIXED");
        String permission = section.getString("permission", null);
        int slots = section.getInt("slots", Page.MIN_SLOTS);

    }

    public static boolean isCorrectFileExtension(File file, String extension) {
        return isCorrectFileExtension(file.getPath(), extension);
    }
    public static boolean isCorrectFileExtension(String filePath, String extension) {
        return getFileExtension(filePath).equals(extension);
    }
    public static String getFileExtension(File file) { return getFileExtension(file.getPath()); }
    public static String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf('.');
        if(lastIndex == -1) return "NO EXTENSION";
        return filePath.substring(lastIndex);
    }
}
