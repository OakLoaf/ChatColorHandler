package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public class SpigotParser implements Parser {
    public static final SpigotParser INSTANCE = new SpigotParser();

    private SpigotParser() {}

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> ChatColor.translateAlternateColorCodes('&', string);
            case MINI_MESSAGE -> string;
        };
    }
}
