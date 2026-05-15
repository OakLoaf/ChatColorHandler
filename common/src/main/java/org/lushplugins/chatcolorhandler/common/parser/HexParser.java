package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Translates hex in format "&#rrggbb"
 */
public class HexParser implements Parser {
    public static final HexParser INSTANCE = new HexParser(80);
    private static final Pattern HEX_PATTERN = Pattern.compile("&(#[a-fA-F0-9]{6})");

    private final int priority;

    public HexParser(int priority) {
        this.priority = priority;
    }

    @Override
    public ParserType getType() {
        return ParserType.COLOR;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        return HEX_PATTERN.matcher(string).replaceAll("<reset><$1>");
    }
}
