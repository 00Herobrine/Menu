package org.x00hero;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.x00hero.Menu.MenuController;
import org.x00hero.Tests.MenuEvents;
import org.x00hero.Tests.MenuTypes;

public final class Main extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        //Config.Initialize();
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("menu").setExecutor(new MenuTypes());
    }
    private void registerEvents() {
        pm.registerEvents(new MenuEvents(), plugin);
        pm.registerEvents(new MenuController(), plugin);
        pm.registerEvents(new MenuTypes(), plugin);
    }

    @Override
    public void onDisable() {

    }
}
