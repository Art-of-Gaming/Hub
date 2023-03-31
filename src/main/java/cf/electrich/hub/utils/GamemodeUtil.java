package cf.electrich.hub.utils;

import java.util.Objects;

public class GamemodeUtil {
    public static String whatGamemode(String g) {
        if (Objects.equals(g, "1") || Objects.equals(g, "c") || Objects.equals(g, "creative")) {
            return "creative";
        } else if (Objects.equals(g, "0") || Objects.equals(g, "s") || Objects.equals(g, "survival")) {
            return "survival";
        } else {
            return "unknown";
        }
    }
}
