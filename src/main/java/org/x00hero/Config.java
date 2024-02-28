package org.x00hero;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Base64;

import static org.x00hero.Main.plugin;

public class Config {
    private static String secret;
    private static int byteCount;
    private static String NavItemString;
    private static String MenuItemString;
    private static NamespacedKey NavItemKey;
    private static NamespacedKey MenuItemKey;
    private static byte[] publicKey;

    public static int getByteCount() { return byteCount; }
    public static String getSecret() { return secret; }
    public static byte[] getPublicKey() { return publicKey; }
    public static String getMenuItemString() { return MenuItemString; }
    public static String getNavItemString() { return NavItemString; }
    public static NamespacedKey getMenuItemKey() { return MenuItemKey; }
    public static NamespacedKey getNavItemKey() { return NavItemKey; }

    public static void Initialize() {
        FileConfiguration config = plugin.getConfig();
        NavItemString = config.getString("NavItem-Key");
        MenuItemString = config.getString("MenuItem-Key");
        NavItemKey = new NamespacedKey(plugin, NavItemString);
        MenuItemKey = new NamespacedKey(plugin, MenuItemString);
        byteCount = config.getInt("bytes");
        secret = config.getString("secret-key");
        publicKey = Base64.getDecoder().decode(secret);
    }
}
