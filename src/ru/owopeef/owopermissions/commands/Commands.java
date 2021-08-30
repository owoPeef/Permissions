package ru.owopeef.owopermissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.owopeef.owopermissions.utils.Permissions;

import java.util.Objects;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        // Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("perms")) {
            if (args.length == 2)
            {
                if (Objects.equals(args[0], "display_name"))
                {
                    Player input_player = Bukkit.getServer().getPlayer(args[1]);
                    sender.sendMessage(input_player.getDisplayName());
                    return true;
                }
            }
            if (args.length == 4) {
                if (Objects.equals(args[0], "add") && Objects.equals(args[2], "group"))
                {
                    Player input_player = Bukkit.getServer().getPlayer(args[1]);
                    Permissions.setGroup(input_player, args[3]);
                    sender.sendMessage("Success");
                    return true;
                }
            }
        }
        sender.sendMessage("Command not found");
        return true;
    }
}
