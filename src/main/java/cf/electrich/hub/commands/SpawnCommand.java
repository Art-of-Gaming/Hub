package cf.electrich.hub.commands;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    public SpawnCommand() {
        Main.getInstance().getCommand("spawn").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = ((Player) commandSender);
            Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
            spawn.add(0.5, 0.0, 0.5);
            player.teleport(spawn);
        } else {
            commandSender.sendMessage(CC.translate("&cOnly players!"));
        }
        return false;
    }
}
