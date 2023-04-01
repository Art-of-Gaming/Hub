package cf.electrich.hub.runnable;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import com.lunarclient.bukkitapi.LunarClientAPI;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class NameTagRunnable extends BukkitRunnable {
    private final Main instance;

    public NameTagRunnable(Main instance) {
        this.instance = instance;
    }

    @Override
    public void run() {
        for (Player player : this.instance.getServer().getOnlinePlayers()) {
            List<String> show = new ArrayList<>();
            show.add(CC.translate("&8[" + Main.getPlayerGroupDisplayName(player) + "&8]"));
            show.add(Main.getSuffix(player) + player.getName());

            show = new ArrayList<>(show);
            List<String> finalShow = show;
            for (Player target : this.instance.getServer().getOnlinePlayers()) {
                LunarClientAPI.getInstance().overrideNametag(player, finalShow, target);
            }
        }
    }
}
