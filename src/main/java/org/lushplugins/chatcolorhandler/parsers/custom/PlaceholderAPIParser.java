package org.lushplugins.chatcolorhandler.parsers.custom;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public class PlaceholderAPIParser implements Parser {

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return PlaceholderAPI.setPlaceholders(null, string);
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }
}
