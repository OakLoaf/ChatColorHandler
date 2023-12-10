package me.dave.chatcolorhandler;

import me.dave.chatcolorhandler.parsers.Parsers;
import me.dave.chatcolorhandler.parsers.custom.*;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModernChatColorHandler {

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public Component translateAlternateColorCodes(@Nullable String string) {
        return translateAlternateColorCodes(string, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param parsers Parsers which this message won't be parsed through
     */
    public Component translateAlternateColorCodes(@Nullable String string, List<Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return Component.empty();

        boolean parseHex = parsers.remove(HexParser.class);
        parsers.remove(MiniMessageParser.class);

        String legacyParsed = MiniMessageParser.legacyToMiniMessage(Parsers.parseString(string, null, parsers), parseHex);
        return MiniMessage.miniMessage().deserialize(legacyParsed);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public Component translateAlternateColorCodes(@Nullable String string, Player player) {
        return translateAlternateColorCodes(string, player, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public Component translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return Component.empty();

        boolean parseHex = parsers.remove(HexParser.class);
        parsers.remove(MiniMessageParser.class);

        String legacyParsed = MiniMessageParser.legacyToMiniMessage(Parsers.parseString(string, player, parsers), parseHex);
        return MiniMessage.miniMessage().deserialize(legacyParsed);
    }
}
