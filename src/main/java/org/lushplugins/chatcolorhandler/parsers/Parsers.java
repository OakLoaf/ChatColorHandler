package org.lushplugins.chatcolorhandler.parsers;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;

public class Parsers {
    private Map<Parser, Integer> parsers = new HashMap<>();

    /**
     * @param parser parser to register
     * @param priority priority of parser, higher value will go first
     */
    public void register(Parser parser, int priority) {
        parsers.put(parser, priority);
        parsers = sortByValue(parsers);
    }

    public String parseString(@NotNull String string, @NotNull Parser.OutputType outputType, @Nullable Player player, @NotNull List<Parser> parsers) {
        for (Parser parser : parsers) {
            try {
                string = parser.parseString(string, outputType, player);
            } catch (Throwable e) {
                Bukkit.getLogger().log(Level.WARNING, "[ChatColorHandler] Failed to parse string '%s' through parser '%s: ".formatted(string, parser.getClass().getSimpleName()), e);
            }
        }

        return string;
    }

    public List<Parser> getRegisteredParsers() {
        return parsers.keySet().stream().toList();
    }

    public List<Resolver> getRegisteredResolvers() {
        return parsers.keySet().stream()
            .map(parser -> parser instanceof Resolver resolver ? resolver : null)
            .filter(Objects::nonNull)
            .toList();
    }

    public List<Parser> ofType(@NotNull String type) {
        return ofTypes(type);
    }

    public List<Parser> ofTypes(@NotNull String... types) {
        return parsers.keySet().stream()
            .filter(parser -> {
                for (String type : types) {
                    if (parser.getType().equals(type)) {
                        return true;
                    }
                }

                return false;
            })
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
