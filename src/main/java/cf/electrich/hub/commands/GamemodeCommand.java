package cf.electrich.hub.commands;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import cf.electrich.hub.utils.GamemodeUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    public GamemodeCommand() {
        Main.getInstance().getCommand("gamemode").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player)commandSender;
            if (player.hasPermission(Main.returnConfig("PERMISSIONS.TREE") + Main.returnConfig("PERMISSIONS.GAMEMODE"))) {
                if (strings.length == 0) {
                    player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_USAGE")));
                    return false;
                } else if (strings.length == 2) {
                    String mode = GamemodeUtil.whatGamemode(strings[0]);
                    if (mode.equals("unknown")) {
                        player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.UNEXIST_GAMEMODE")));
                        return false;
                    }
                    try {
                        Player p2 = Bukkit.getPlayer(strings[1]);
                        if (mode.equals("creative")) {
                            p2.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_OTHER").replace("<prefix>", Main.getPrefix(p2)).replace("<player>", p2.getName()).replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.CREATIVE_MODE"))));
                            p2.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_YOU").replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.CREATIVE_MODE"))));
                            return false;
                        }
                        if (mode.equals("survival")) {
                            p2.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_OTHER").replace("<prefix>", Main.getPrefix(p2)).replace("<player>", p2.getName()).replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.SURVIVAL_MODE"))));
                            p2.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_YOU").replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.SURVIVAL_MODE"))));
                            return false;
                        }
                    } catch (Exception e) {
                        player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.UNEXIST_PLAYER")));
                    }
                } else if (strings.length == 1) {
                    String mode = GamemodeUtil.whatGamemode(strings[0]);
                    if (mode.equals("unknown")) {
                        player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.UNEXIST_GAMEMODE")));
                        return false;
                    }
                    if (mode.equals("creative")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_YOU").replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.CREATIVE_MODE"))));
                        return false;
                    }
                    if (mode.equals("survival")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.GAMEMODE_CHANGE_YOU").replace("<gamemode>", Main.returnConfig("MESSAGES.WORDS.SURVIVAL_MODE"))));
                        return false;
                    }
                } else {
                    player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.TOO_MUCH_ARGS")));
                    return false;
                }
            } else {
                player.sendMessage(CC.translate(Main.returnConfig("MESSAGES.PHRASES.NO_PERMISSIONS")));
                return false;
            }
        } else {
            commandSender.sendMessage(CC.translate("&cOnly players."));
        }
        return false;
    }
}
