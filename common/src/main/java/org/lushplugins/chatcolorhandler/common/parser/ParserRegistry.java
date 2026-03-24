package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;

public class ParserRegistry {
    private Map<Parser, Integer> parsers = new HashMap<>();

    /**
     * @param parser parser to register
     * @param priority priority of parser, higher value will go first
     */
    public void register(Parser parser, int priority) {
        parsers.put(parser, priority);
        sort();
    }

    public String parseString(@NotNull String string, @Nullable Player player, @NotNull Parsers parsers) {
        for (Parser parser : parsers) {
            try {
                string = parser.parseString(string, player);
            } catch (Throwable e) {
                Bukkit.getLogger().log(Level.WARNING, "[ChatColorHandler] Failed to parse string '%s' through parser '%s: ".formatted(string, parser.getClass().getSimpleName()), e);
            }
        }

        return string;
    }

    public void sort() {
        parsers = sortByValue(parsers);
    }

    public Set<Parser> values() {
        return parsers.keySet();
    }

    public List<Parser> ofTypes(@NotNull String... types) {
        return values().stream()
            .filter(parser -> {
                ParserType parserType = parser.getType();
                if (parserType != null) {
                    for (String type : types) {
                        if (parserType.equals(type)) {
                            return true;
                        }
                    }
                }

                return false;
            })
            .toList();
    }

    public List<Parser> ofType(@NotNull String type) {
        return ofTypes(type);
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
