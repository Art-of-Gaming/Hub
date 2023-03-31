package cf.electrich.hub;

import cf.electrich.hub.adapter.tablist.TabLayout;
import cf.electrich.hub.commands.GamemodeCommand;
import cf.electrich.hub.commands.SpawnCommand;
import cf.electrich.hub.listeners.PlayerListener;
import cf.electrich.hub.utils.CC;
import jdk.jfr.consumer.RecordedObject;
import me.lucanius.edge.Edge;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.types.DisplayNameNode;
import net.luckperms.api.query.QueryOptions;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.ibex.nestedvm.util.Seekable;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {
    private static Main instance;
    public static Main getInstance() { return instance; };
    private Edge edge;
    public void log(String s) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(s));
    }

    public void register(String r) {
        if (r.equals("commands")) {
            new SpawnCommand();
            new GamemodeCommand();
        }
        if (r.equals("listeners")) {
            new PlayerListener();
        }
    }

    public static String getPrefix(Player p) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        CachedMetaData metaData = luckPerms.getUserManager().getUser(p.getUniqueId()).getCachedData().getMetaData();
        String prefix = metaData.getPrefix();
        return prefix != null ? prefix : "";
    }

    public static String getGroupDisplayName(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            String groupName = user.getPrimaryGroup();
            Node node = luckPerms.getNodeBuilderRegistry().forContextualOptions(QueryOptions.nonContextual()).build().createNode("group." + groupName + ".displayname");
            DisplayNameNode displayNameNode = node.get(DisplayNameNode.class);
            if (displayNameNode != null) {
                return displayNameNode.value();
            }
        }
        return null;
    }

    public static String returnConfig(String s) {
        return getInstance().getConfig().getString(s);
    }

    @Override
    public void onEnable() {
        instance = this;
        this.edge = new Edge(this, new TabLayout());
        log("&aHub has been loaded.");
        register("commands");
        register("listeners");
    }

    @Override
    public void onDisable() {
        this.edge.getService().destroy();
        log("&cHub has been disabled.");
    }

    public Edge getEdge() {
        return this.edge;
    }
}
