package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Pattern;

/**
 * Translates hex in format "§x§r§r§g§g§b§b"
 */
public class LegacyHexParser implements Parser {
    public static final LegacyHexParser INSTANCE = new LegacyHexParser();
    private static final Pattern HEX_PATTERN = Pattern.compile("[§&]x[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])");

    @Override
    public ParserType getType() {
        return ParserType.COLOR;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        return HEX_PATTERN.matcher(string).replaceAll("<reset><#$1$2$3$4$5$6>");
    }
}
