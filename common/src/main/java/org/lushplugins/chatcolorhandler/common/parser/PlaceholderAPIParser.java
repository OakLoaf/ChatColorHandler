package org.lushplugins.chatcolorhandler.common.parser;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class PlaceholderAPIParser implements Parser {
    public static final PlaceholderAPIParser INSTANCE = new PlaceholderAPIParser();

    @Override
    public ParserType getType() {
        return ParserType.PLACEHOLDER;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }
}
