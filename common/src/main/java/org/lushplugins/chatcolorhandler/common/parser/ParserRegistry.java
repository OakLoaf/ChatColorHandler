package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.logging.Level;

public class ParserRegistry {
    private final Set<Parser> parsers = new HashSet<>();

    /**
     * @param parser parser to register
     */
    public void register(Parser parser) {
        parsers.add(parser);
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

    public List<Parser> values() {
        return parsers.stream()
            .sorted(Parsers.comparingPriority())
            .toList();
    }

    public List<Parser> unsorted() {
        return new ArrayList<>(parsers);
    }

    public List<Parser> sorted() {
        return values();
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
            .sorted(Parsers.comparingPriority())
            .toList();
    }

    public List<Parser> ofType(@NotNull String type) {
        return ofTypes(type);
    }
}
