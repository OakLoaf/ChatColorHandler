package org.lushplugins.chatcolorhandler.parsers;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Parser {

    /**
     * @return The type of the parser {@link ParserTypes}
     */
    String getType();

    /**
     * Parse a string
     * @param string The string to parse
     * @param outputType The output type to parse for
     * @return The parsed string
     */
    String parseString(@NotNull String string, @NotNull OutputType outputType);

    /**
     * Parse a string in relation to a player
     * @param string The string to parse
     * @param outputType The output type to parse for
     * @param player The player to apply whilst parsing
     * @return The parsed string
     */
    default String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        return parseString(string, outputType);
    }

    enum OutputType {
        SPIGOT,
        MINI_MESSAGE
    }
}
