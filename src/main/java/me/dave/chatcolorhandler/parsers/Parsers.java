package me.dave.chatcolorhandler.parsers;

import me.dave.chatcolorhandler.parsers.custom.Parser;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Parsers {
    private static Map<Parser, Integer> parsers = new HashMap<>();

    public static void register(Parser parser, int priority) {
        parsers.put(parser, priority);
        parsers = sortByValue(parsers);
    }

    public static String parseString(String string, @Nullable Player player, @Nullable List<Class<? extends Parser>> parsers) {
        for (Parser parser : Parsers.parsers.keySet()) {
            if (parsers == null || parsers.contains(parser.getClass())) {
                try {
                    string = parser.parseString(string, player);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
        return string;
    }

    private static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}
