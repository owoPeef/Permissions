package ru.owopeef.owopermissions;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owopermissions.utils.Config;

import java.util.List;
import java.util.Objects;

public class PlayerEvents implements Listener
{
    public static Plugin plugin = Main.getPlugin(Main.class);
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        List<String> player_groups = Config.readConfigStringList("users", player.getUniqueId().toString());
        List<String> groups = Config.readConfigStringParents("groups");
        if (player_groups.size() == 1)
        {
            String group = player_groups.get(0);
            List<String> permissions = Config.readConfigStringList("groups", group, "permissions");
            int c = 0;
            while (c != permissions.size())
            {
                PermissionAttachment attachment = player.addAttachment(plugin);
                attachment.setPermission(permissions.get(c), true);
                c++;
            }
        }
        if (player_groups.size() >= 2) {
            int a = 0;
            while (a != player_groups.size())
            {
                String currentGroup = player_groups.get(a);
                int b = 0;
                while (b != groups.size())
                {
                    if (Objects.equals(groups.get(b), currentGroup))
                    {
                        List<String> permissions = Config.readConfigStringList("groups", currentGroup, "permissions");
                        int c = 0;
                        while (c != permissions.size())
                        {
                            PermissionAttachment attachment = player.addAttachment(plugin);
                            attachment.setPermission(permissions.get(c), true);
                            c++;
                        }
                    }
                    b++;
                }
                a++;
            }
        }
    }
}
