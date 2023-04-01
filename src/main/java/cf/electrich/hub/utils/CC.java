package cf.electrich.hub.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class CC {
    public static String translate(String t) {
        return ChatColor.translateAlternateColorCodes('&', t);
    }
    public static List<String> translate(List<String> lines) {
        return lines.stream().map(CC::translate).collect(Collectors.toList());
    }
}