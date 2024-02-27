package org.x00hero;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.x00hero.Tests.ShittyMenus;

public final class Main extends JavaPlugin {
    public static PluginManager pm = Bukkit.getPluginManager();
    public Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("menu").setExecutor(new ShittyMenus());
    }
    private void registerEvents() {
        pm.registerEvents(new MenuController(), plugin);
        pm.registerEvents(new ShittyMenus(), plugin);
    }

    @Override
    public void onDisable() {

    }
}
