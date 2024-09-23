package org.lushplugins.chatcolorhandler.parsers;

import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.custom.Parser;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Parsers {
    private static Map<Parser, Integer> parsers = new HashMap<>();

    /**
     * @param parser parser to register
     * @param priority priority of parser, higher value will go first
     */
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

    public static Collection<Parser> getColourParsers() {
        return getParsersByType(ParserTypes.COLOR);
    }

    public static Collection<Parser> getPlaceholderParsers() {
        return getParsersByType(ParserTypes.PLACEHOLDER);
    }

    public static Collection<Parser> getParsersByType(@NotNull String type) {
        return parsers.keySet().stream().filter(parser -> parser.getType().equals(type)).toList();
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
