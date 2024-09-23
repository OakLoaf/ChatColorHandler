package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.entity.Player;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public class LegacyCharParser implements Parser {

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(String string) {
        // Replace legacy character
        return string.replace("§", "&");
    }

    @Override
    public String parseString(String string, Player player) {
        return parseString(string);
    }
}
