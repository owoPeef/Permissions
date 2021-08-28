package ru.owopeef.owopermissions;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owopermissions.commands.Commands;
import ru.owopeef.owopermissions.utils.Config;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new PlayerEvents(), this);
        getCommand("perms").setExecutor(new Commands());
    }
}
