package org.x00hero;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;

import java.security.KeyStore;
import java.security.SecureRandom;
import java.util.Base64;

import static org.x00hero.Main.plugin;

public class Config {
    private static byte[] secret;
    private static int byteCount;
    private static String NavItemString;
    private static String MenuItemString;
    private static NamespacedKey NavItemKey;
    private static NamespacedKey MenuItemKey;
    private static byte[] publicKey;

    public static int getByteCount() { return byteCount; }
    protected static byte[] getSecret() { return secret; }
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
        //secret = config.getByteList("secret-key");
        publicKey = Base64.getDecoder().decode(secret);
    }

    private void generateAndSave() {
        FileConfiguration config = plugin.getConfig();
        if (!config.contains("secret-key") || config.getString("secret-key").equalsIgnoreCase("")) {
            byte[] keyBytes = new byte[config.getInt("bytes")];
            new SecureRandom().nextBytes(keyBytes);
            String base64Key = Base64.getEncoder().encodeToString(keyBytes);
            config.set("secret-key", base64Key);
            plugin.saveConfig();
        }
    }
}
