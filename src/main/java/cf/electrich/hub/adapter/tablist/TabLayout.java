package cf.electrich.hub.adapter.tablist;

import cf.electrich.hub.Main;
import cf.electrich.hub.utils.CC;
import me.lucanius.edge.adapter.TabAdapter;
import me.lucanius.edge.column.TabColumn;
import me.lucanius.edge.entry.TabData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
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

        entries.add(new TabData(TabColumn.LEFT, 3, CC.translate("&6HCF")));
        entries.add(new TabData(TabColumn.LEFT, 4, Main.isServerOnline(Main.returnConfig("SERVERS.HCF.IP")) ? CC.translate("&aOnline") : CC.translate("&cOffline")));
        try {
            entries.add(new TabData(TabColumn.LEFT, 5, CC.translate("&7Players: &e" + Main.getOnlinePlayers(Main.returnConfig("SERVERS.HCF.IP")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        entries.add(new TabData(TabColumn.LEFT, 6, CC.translate("&6Queue")));
        entries.add(new TabData(TabColumn.LEFT, 7, CC.translate("&c&oWorking on...")));
        entries.add(new TabData(TabColumn.LEFT, 11, CC.translate("&6Discord:")));
        entries.add(new TabData(TabColumn.LEFT, 12, CC.translate("&7discord.electrich.cf")));

        // Center side

        entries.add(new TabData(TabColumn.MIDDLE, 0, CC.translate("&6&lHub")));
        entries.add(new TabData(TabColumn.MIDDLE, 3, CC.translate("&6Rank")));
        entries.add(new TabData(TabColumn.MIDDLE, 4, CC.translate(Main.getPlayerGroupDisplayName(player))));
        entries.add(new TabData(TabColumn.MIDDLE, 7, CC.translate("&6Coins")));
        entries.add(new TabData(TabColumn.MIDDLE, 8, CC.translate(String.valueOf(Main.getCoins(player)))));
        entries.add(new TabData(TabColumn.MIDDLE, 11, CC.translate("&6Twitter:")));
        entries.add(new TabData(TabColumn.MIDDLE, 12, CC.translate("&7@electrichcf")));
        entries.add(new TabData(TabColumn.MIDDLE, 14, CC.translate("&6Store:")));
        entries.add(new TabData(TabColumn.MIDDLE, 15, CC.translate("&7store.electrich.cf")));

        // Right side

        entries.add(new TabData(TabColumn.RIGHT, 3, CC.translate("&6KitMap:")));
        entries.add(new TabData(TabColumn.RIGHT, 4, Main.isServerOnline(Main.returnConfig("SERVERS.KITS.IP")) ? CC.translate("&aOnline") : CC.translate("&cOffline")));
        try {
            entries.add(new TabData(TabColumn.RIGHT, 5, CC.translate("&7Players: &e" + Main.getOnlinePlayers(Main.returnConfig("SERVERS.KITS.IP")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        entries.add(new TabData(TabColumn.RIGHT, 7, CC.translate("&6Profile:")));
        entries.add(new TabData(TabColumn.RIGHT, 8, CC.translate("&c&oWorking on...")));
        entries.add(new TabData(TabColumn.RIGHT, 11, CC.translate("&6Teamspeak:")));
        entries.add(new TabData(TabColumn.RIGHT, 12, CC.translate("&7ts.electrich.cf")));

        // Far right side

        //entries.add(new TabData(TabColumn.FAR_RIGHT, 4, "Far Right"));

        return entries;
    }
}
