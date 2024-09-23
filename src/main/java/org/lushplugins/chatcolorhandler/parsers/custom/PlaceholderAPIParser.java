package org.lushplugins.chatcolorhandler.parsers.custom;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public class PlaceholderAPIParser implements Parser {


    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(String string) {
        return PlaceholderAPI.setPlaceholders(null, string);
    }

    @Override
    public String parseString(String string, Player player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }
}
