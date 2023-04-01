package cf.electrich.hub;

import cf.electrich.hub.adapter.scoreboard.ScoreboardLayout;
import cf.electrich.hub.adapter.tablist.TabLayout;
import cf.electrich.hub.commands.GamemodeCommand;
import cf.electrich.hub.commands.SpawnCommand;
import cf.electrich.hub.listeners.PlayerListener;
import cf.electrich.hub.runnable.NameTagRunnable;
import cf.electrich.hub.utils.CC;
import dev.risas.dracma.DracmaAPI;
import io.github.thatkawaiisam.assemble.Assemble;
import io.github.thatkawaiisam.assemble.AssembleStyle;
import me.lucanius.edge.Edge;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

public final class Main extends JavaPlugin {
    private static Main instance;
    private NameTagRunnable nameTagRunnable;
    //private static DracmaAPI dracma;
    public static Main getInstance() { return instance; };
    private Edge edge;
    public static void log(String s) {
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
        if (r.equals("runnable")) {
            this.nameTagRunnable = new NameTagRunnable(this);
            this.nameTagRunnable.runTaskTimerAsynchronously(this, 5L, 10L);
        }
    }

    public static String getPrefix(Player p) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        CachedMetaData metaData = Objects.requireNonNull(luckPerms.getUserManager().getUser(p.getUniqueId())).getCachedData().getMetaData();
        String prefix = metaData.getPrefix();
        return prefix != null ? prefix : "";
    }

    public static String getSuffix(Player p) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        CachedMetaData metaData = Objects.requireNonNull(luckPerms.getUserManager().getUser(p.getUniqueId())).getCachedData().getMetaData();
        String suffix = metaData.getSuffix();
        return suffix != null ? suffix : "";
    }

    public static String getPlayerGroupDisplayName(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            return "&7Default";
        }
        Group group = luckPerms.getGroupManager().getGroup(user.getPrimaryGroup());
        if (group == null) {
            return "&7Default";
        }
        return group.getDisplayName();
    }

    public static boolean isServerOnline(String ip) {
        int port = Integer.parseInt(ip.split(":")[1]);
        try {
            InetAddress address = InetAddress.getByName(ip);
            Socket socket = new Socket();
            InetSocketAddress socketAddress = new InetSocketAddress(address, port);
            socket.connect(socketAddress, 1000);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @SuppressWarnings("EmptyWhileStatementBody")
    public static int getOnlinePlayers(String ip) throws IOException {
        int port = Integer.parseInt(ip.split(":")[1]);
        try (Socket socket = new Socket(ip, port)) {
            InputStream inputStream = socket.getInputStream();
            byte[] data = new byte[2048];
            int length;
            while ((length = inputStream.read(data)) != -1 && length < 2048) {
            }
            String response = new String(data, 0, length);
            String[] parts = response.split("\0");
            String playersOnline = parts[4];
            playersOnline = playersOnline.substring(playersOnline.indexOf(":") + 1, playersOnline.indexOf(")"));
            return Integer.parseInt(playersOnline);
        }
    }


    /*public static int getCoins(Player player) {
        return dracma.getCurrency(player.getUniqueId(), "coins");
    }*/

    public static String returnConfig(String s) {
        return getInstance().getConfig().getString(s);
    }

    @Override
    public void onEnable() {
        instance = this;
        this.edge = new Edge(this, new TabLayout());
        Assemble assemble = new Assemble(this, new ScoreboardLayout());
        assemble.setTicks(2);
        assemble.setAssembleStyle(AssembleStyle.VIPER);
        log("&aHub has been loaded.");
        //dracma = new DracmaAPI();
        register("commands");
        register("listeners");
        register("runnable");
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
