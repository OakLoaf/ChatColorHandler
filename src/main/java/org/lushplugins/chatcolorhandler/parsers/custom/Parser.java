package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.entity.Player;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public interface Parser {

    /**
     * @return The type of the parser, by default we use 'COLOR' and `PLACEHOLDER` {@link ParserTypes}
     */
    String getType();

    /**
     * Parse a string
     * @param string The string to parse
     * @return The parsed string
     */
    String parseString(String string);

    /**
     * Parse a string in relation to a player
     * @param string The string to parse
     * @param player The player to apply whilst parsing
     * @return The parsed string
     */
    default String parseString(String string, Player player) {
        return parseString(string);
    }
}
