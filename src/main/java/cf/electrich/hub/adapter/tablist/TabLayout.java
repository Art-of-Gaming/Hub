package cf.electrich.hub.adapter.tablist;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import dev.risas.dracma.DracmaAPI;
import me.lucanius.edge.adapter.TabAdapter;
import me.lucanius.edge.column.TabColumn;
import me.lucanius.edge.entry.TabData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TabLayout implements TabAdapter {
    private final Main plugin = Main.getInstance();
    File tablistFile = new File(Main.getInstance().getDataFolder(), "tablist.yml");
    YamlConfiguration tablistConfig = YamlConfiguration.loadConfiguration(tablistFile);

    @Override
    public List<String> getHeader(Player player) {
        return Arrays.asList("", CC.translate("&6&lELECTRIC &7("+Bukkit.getOnlinePlayers().size()+CC.translate(" players)")), "");
    }

    @Override
    public List<String> getFooter(Player player) {
        return Arrays.asList("", CC.translate("&7Enjoy your &aperfect community&7."), CC.translate("&7&o(( All of you are being listened ))"), "", CC.translate("&7Request support in our community"), CC.translate("&e&odiscord.electrich.cf"), "");
    }

    @Override
    public Set<TabData> getEntries(Player player) {
        Set<TabData> entries = new HashSet<>();
        /*

        Useful variables: plugin.getEdge().getSkin(player.getUniqueId())
        Data without head: entries.add(new TabData(TabColumn.LEFT, 4, "Example"));
        Data with head: entries.add(new TabData(TabColumn.LEFT, 4, "Profile", player.getEdge().getSkin("76679b22-4802-4b6b-85dc-115d72ea9b20")));

        */

        // Left side

        entries.add(new TabData(TabColumn.LEFT, 4, "Left"));

        // Center side

        entries.add(new TabData(TabColumn.MIDDLE, 0, CC.translate("&6&lHub")));
        entries.add(new TabData(TabColumn.MIDDLE, 3, CC.translate("&6Rank")));
        entries.add(new TabData(TabColumn.MIDDLE, 4, CC.translate(Main.getGroupDisplayName(player))));
        entries.add(new TabData(TabColumn.MIDDLE, 7, CC.translate("&6Coins")));
        entries.add(new TabData(TabColumn.MIDDLE, 4, CC.translate(DracmaAPI.getCurrency(player))));

        // Right side

        entries.add(new TabData(TabColumn.RIGHT, 4, "Right"));

        // Far right side

        entries.add(new TabData(TabColumn.FAR_RIGHT, 4, "Far Right"));

        return entries;
    }
}
