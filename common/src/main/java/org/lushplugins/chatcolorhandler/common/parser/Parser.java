package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface Parser {
    /**
     * @return The type of the parser or {@code null} if it should only apply when using all parsers
     */
    @Nullable ParserType getType();

    /**
     * Parse a string in relation to a player
     * @param string The string to parse
     * @param player The player to apply whilst parsing
     * @return The parsed string
     */
    String parseString(String string, @Nullable Player player);
}
