package ru.owopeef.owopermissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.owopeef.owopermissions.utils.Permissions;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        // Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("perms")) {
            if (args.length == 0) {
                sender.sendMessage("null");
                return true;
            }
            if (args.length == 1)
            {
                sender.sendMessage("null");
                return true;
            }
            if (args.length == 2) {
                Player input_player = Bukkit.getServer().getPlayer(args[0]);
                Permissions.setGroup(input_player, args[1]);
                sender.sendMessage("Success");
                return true;
            }
        }
        return true;
    }
}
