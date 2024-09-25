package org.lushplugins.chatcolorhandler.parsers.custom;

import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Hex in format '§x§r§r§g§g§b§b'
public class LegacyHexParser implements Parser {
    public static final LegacyHexParser INSTANCE = new LegacyHexParser();
    private static final Pattern HEX_PATTERN = Pattern.compile("[§&]x[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])[§&]([a-fA-F0-9])");

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> string;
            case MINI_MESSAGE -> {
                Matcher match = HEX_PATTERN.matcher(string);
                yield match.replaceAll("<reset><#$1$2$3$4$5$6>");
            }
        };
    }
}
