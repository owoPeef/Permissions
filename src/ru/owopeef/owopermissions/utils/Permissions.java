package ru.owopeef.owopermissions.utils;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owopermissions.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Permissions
{
    public static Plugin plugin = Main.getPlugin(Main.class);
    public static void setGroup(Player player, String group)
    {
        List<String> list = Config.readConfigStringParents("groups");
        String uuid = String.valueOf(player.getUniqueId());
        int a = 0;
        while (list.size() != a)
        {
            if (Objects.equals(list.get(a), group))
            {
                List<String> permissions = Config.readConfigStringList("groups", group, "permissions");
                int b = 0;
                while (b != permissions.size())
                {
                    PermissionAttachment attachment = player.addAttachment(plugin);
                    attachment.setPermission(permissions.get(b), true);
                    b++;
                }
                boolean hasGroup = hasGroup(player, group);
                if (!hasGroup)
                {
                    List<String> player_groups_list = Config.readConfigStringList("users", uuid);
                    List<String> groups = new ArrayList<>();
                    if (player_groups_list.size() == 1)
                    {
                        groups.add(player_groups_list.get(0));
                    }
                    if (player_groups_list.size() >= 2)
                    {
                        int c = 0;
                        while (c != player_groups_list.size())
                        {
                            groups.add(player_groups_list.get(c));
                            c++;
                        }
                    }
                    groups.add(group);
                    Config.appendConfig("users", uuid, groups);
                }
            }
            a++;
        }
    }
    public static boolean hasGroup(Player player, String group)
    {
        List<String> list = Config.readConfigStringParents("users");
        plugin.getLogger().info(String.valueOf(list.size()));
        if (list.size() == 0)
        {
            return false;
        }
        String uuid = String.valueOf(player.getUniqueId());
        int a = 0;
        while (list.size() != a)
        {
            String currentPlayer = list.get(a);
            if (Objects.equals(currentPlayer, uuid))
            {
                List<String> player_groups = Config.readConfigStringList("users", uuid);
                int b = 0;
                while (player_groups.size() != b)
                {
                    String currentPlayerGroup = player_groups.get(b);
                    if (Objects.equals(currentPlayerGroup, group))
                    {
                        return true;
                    }
                    b++;
                }
            }
            a++;
        }
        return false;
    }
}
