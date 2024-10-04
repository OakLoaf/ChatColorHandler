package org.lushplugins.chatcolorhandler.parsers.custom;

import net.md_5.bungee.api.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Hex in format "&#rrggbb" or "#rrggbb"
public class HexParser implements Parser {
    public static final HexParser INSTANCE = new HexParser();
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#[a-fA-F0-9]{6})");

    private HexParser() {}

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        Matcher match = HEX_PATTERN.matcher(string);
        return switch (outputType) {
            case SPIGOT -> match.replaceAll(result -> ChatColor.of(result.group(1)).toString());
            case MINI_MESSAGE -> match.replaceAll("<reset><$1>");
        };
    }
}
