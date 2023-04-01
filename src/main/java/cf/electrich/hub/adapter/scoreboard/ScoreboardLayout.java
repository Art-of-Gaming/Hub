package cf.electrich.hub.adapter.scoreboard;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import io.github.thatkawaiisam.assemble.AssembleAdapter;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardLayout implements AssembleAdapter {
    @Override
    public String getTitle(Player player) {
        return CC.translate("&6&lHUB-01");
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> toReturn = new ArrayList<>();

        toReturn.add("&r");
        toReturn.add("&6Online:");
        toReturn.add(Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        toReturn.add("&r");
        toReturn.add("&6Rank:");
        toReturn.add(Main.getPlayerGroupDisplayName(player));
        toReturn.add("&r");
        toReturn.add("&7&oelectrich.cf");

        toReturn.add(0,CC.translate("&7&m" + StringUtils.repeat("-", 20)));
        toReturn.add(toReturn.size(), CC.translate("&7&m" + StringUtils.repeat("-", 20)));
        return toReturn;
    }
}
