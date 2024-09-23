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

    public static String parseString(@NotNull String string, @NotNull Parser.OutputType outputType, @Nullable Player player, @NotNull List<? extends Class<? extends Parser>> parsers) {
        return parseString(string, outputType, player, Parsers.parsers.keySet().stream().filter(parser -> parsers.isEmpty() || parsers.contains(parser.getClass())).toArray(Parser[]::new));
    }

    public static String parseString(@NotNull String string, @NotNull Parser.OutputType outputType, @Nullable Player player, @NotNull String... parserTypes) {
        return parseString(string, outputType, player, Parsers.parsers.keySet().stream().filter(parser -> List.of(parserTypes).contains(parser.getType())).toArray(Parser[]::new));
    }

    private static String parseString(@NotNull String string, @NotNull Parser.OutputType outputType, @Nullable Player player, @NotNull Parser... parsers) {
        for (Parser parser : parsers) {
            try {
                string = parser.parseString(string, outputType, player);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        return string;
    }

    public static List<? extends Class<? extends Parser>> getParsersByType(@NotNull String type) {
        return parsers.keySet().stream()
            .filter(parser -> parser.getType().equals(type))
            .map(Parser::getClass)
            .toList();
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
