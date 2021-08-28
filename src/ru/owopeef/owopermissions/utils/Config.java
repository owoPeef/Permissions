package ru.owopeef.owopermissions.utils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owopermissions.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Config
{
    public static Plugin plugin = Main.getPlugin(Main.class);
    public static String pluginName = plugin.getDescription().getName();
    public static void loadConfig()
    {
        File currentFile = new File(System.getProperty("user.dir") + "\\plugins\\"+pluginName+"\\config.yml");
        if (!currentFile.exists())
        {
            plugin.getConfig().options().copyDefaults(true);
            plugin.saveConfig();
        }
    }

    public static void reloadConfig() throws IOException, InvalidConfigurationException {
        File config = new File(System.getProperty("user.dir") + "\\plugins\\"+pluginName+"\\config.yml");
        plugin.getConfig().load(config);
    }

    public static void appendConfig(String path, String parent1, List<String> text) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        plugin.getConfig().set(path + "." + parent1, text);
        plugin.saveConfig();
    }

    public static void appendConfig(String path, String parent1, String parent2, String text) throws IOException, InvalidConfigurationException {
        reloadConfig();
        String currentText = readConfigString(path, parent1, parent2);
        if (Objects.equals(currentText, "0,0,0;"))
        {
            plugin.getConfig().set(path + "." + parent1 + "." + parent2, text + ";");
        }
        else
        {
            plugin.getConfig().set(path + "." + parent1 + "." + parent2, currentText + text + ";");
        }
        plugin.saveConfig();
    }

    public static String readConfigString(String path, String parent1, String parent2) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getString(path + "." + parent1 + "." + parent2);
    }

    public static String readConfig(String path) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().get(path).toString();
    }

    public static String readConfig(String path, String parent1) throws IOException, InvalidConfigurationException {
        reloadConfig();
        return plugin.getConfig().get(path + "." + parent1).toString();
    }

    public static String readConfig(String path, String parent1, String parent2) throws IOException, InvalidConfigurationException {
        reloadConfig();
        return plugin.getConfig().get(path + "." + parent1 + "." + parent2).toString();
    }

    public static String readConfig(String path, String parent1, String parent2, String parent3) throws IOException, InvalidConfigurationException {
        reloadConfig();
        return plugin.getConfig().get(path + "." + parent1 + "." + parent2 + "." + parent3).toString();
    }

    public static List<String> readConfigStringParents(String path)
    {
        try {
            reloadConfig();
        }
        catch (Exception e)
        {
            plugin.getLogger().info(e.getMessage());
        }
        List<String> list;
        try
        {
            list = new ArrayList<>(plugin.getConfig().getConfigurationSection(path).getKeys(false));
        }
        catch (Exception e)
        {
            list = Collections.emptyList();
        }
        return list;
    }

    public static List<String> readConfigStringList(String path, String parent2) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getStringList(path + "." + parent2);
    }

    public static List<String> readConfigStringList(String path, String parent2, String parent3) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getStringList(path + "." + parent2 + "." + parent3);
    }

    public static List<String> readConfigStringList(String path, String parent2, String parent3, String parent4) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getStringList(path + "." + parent2 + "." + parent3 + "." + parent4);
    }

    public static Boolean readConfigBoolean(String path) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getBoolean(path);
    }

    public static Integer readConfigInteger(String path) {
        try
        {
            reloadConfig();
        }
        catch (Exception e) {plugin.getLogger().info(e.getMessage());}
        return plugin.getConfig().getInt(path);
    }
}
