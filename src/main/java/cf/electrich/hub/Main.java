package cf.electrich.hub;

import cf.electrich.hub.adapter.tablist.TabLayout;
import cf.electrich.hub.commands.GamemodeCommand;
import cf.electrich.hub.commands.SpawnCommand;
import cf.electrich.hub.listeners.PlayerListener;
import cf.electrich.hub.utils.CC;
import dev.risas.dracma.DracmaAPI;
import me.lucanius.edge.Edge;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static DracmaAPI dracma;
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
        CachedMetaData metaData = Objects.requireNonNull(luckPerms.getUserManager().getUser(p.getUniqueId())).getCachedData().getMetaData();
        String prefix = metaData.getPrefix();
        return prefix != null ? prefix : "";
    }

    public String getPlayerGroupDisplayName(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        assert user != null;
        Group group = luckPerms.getGroupManager().getGroup(user.getPrimaryGroup());
        assert group != null;
        return group.getDisplayName();
    }


    public static int getCoins(Player player) {
        return dracma.getCurrency(player.getUniqueId(), "coins");
    }

    public static String returnConfig(String s) {
        return getInstance().getConfig().getString(s);
    }

    @Override
    public void onEnable() {
        instance = this;
        this.edge = new Edge(this, new TabLayout());
        log("&aHub has been loaded.");
        dracma = new DracmaAPI();
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
