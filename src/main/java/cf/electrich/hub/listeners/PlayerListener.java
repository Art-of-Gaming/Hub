package cf.electrich.hub.listeners;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class PlayerListener implements Listener {
    public PlayerListener() {
        Bukkit.getServer().getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission(Main.returnConfig("PERMISSIONS.TREE") + Main.returnConfig("PERMISSIONS.VIPJOIN"))) {
            if (Objects.equals(Main.getInstance().getConfig().getString("VIP_JOIN.ENABLED"), "false")) {
                e.setJoinMessage(null);
            } else {
                e.setJoinMessage(CC.translate(Main.getInstance().getConfig().getString("VIP_JOIN.MESSAGE").replace("<prefix>", Main.getPrefix(player)).replace("<player>", player.getName())));
            }
        } else {
            e.setJoinMessage(null);
        }
        Location spawn = Bukkit.getWorlds().get(0).getSpawnLocation();
        spawn.add(0.5, 0.0, 0.5);
        player.teleport(spawn);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        if (Objects.equals(Main.getInstance().getConfig().getString("CHAT.ENABLED"), "true")) {
            Player player = e.getPlayer();
            e.setCancelled(true);
            for (Player p2 : Bukkit.getOnlinePlayers()) {
                p2.sendMessage(CC.translate(Main.getInstance().getConfig().getString("CHAT.FORMAT").replace("<prefix>", Main.getPrefix(player)).replace("<player>", player.getName())).replace("<message>", CC.translate(e.getMessage())));
            }
        }
    }
}
