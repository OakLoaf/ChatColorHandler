package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Translates hex in format "&#rrggbb"
 */
public class HexParser implements Parser {
    public static final HexParser INSTANCE = new HexParser();
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#[a-fA-F0-9]{6})");

    @Override
    public ParserType getType() {
        return ParserType.COLOR;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        return HEX_PATTERN.matcher(string).replaceAll("<reset><$1>");
    }
}
