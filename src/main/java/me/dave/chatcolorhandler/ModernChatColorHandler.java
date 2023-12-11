package me.dave.chatcolorhandler;

import me.dave.chatcolorhandler.parsers.Parsers;
import me.dave.chatcolorhandler.parsers.custom.*;
import me.dave.chatcolorhandler.resolvers.Resolver;
import me.dave.chatcolorhandler.resolvers.Resolvers;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ModernChatColorHandler {

    static {
        ChatColorHandler.translateAlternateColorCodes("init");
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static Component translateAlternateColorCodes(@Nullable String string) {
        return translateAlternateColorCodes(string, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param parsers Parsers which this message won't be parsed through
     */
    public static Component translateAlternateColorCodes(@Nullable String string, List<Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return Component.empty();

        boolean parseHex = false;
        if (parsers != null) {
            parsers = new ArrayList<>(parsers);
            parseHex = parsers.remove(HexParser.class);
            parsers.remove(MiniMessageParser.class);
        }

        String legacyParsed = MiniMessageParser.legacyToMiniMessage(Parsers.parseString(string, null, parsers), parseHex);
        return MiniMessage.miniMessage().deserialize(legacyParsed, Resolvers.getResolver(null, null));
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public static Component translateAlternateColorCodes(@Nullable String string, Player player) {
        return translateAlternateColorCodes(string, player, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static Component translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers) {
        return translateAlternateColorCodes(string, player, parsers, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     * @param resolvers Resolvers which will be used for this message (Paper + forks only)
     */
    public static Component translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers, List<Class<? extends Resolver>> resolvers) {
        if (string == null || string.isBlank()) return Component.empty();

        boolean parseHex;
        if (parsers == null) {
            parseHex = true;
            parsers = List.of(LegacyCharParser.class, PlaceholderAPIParser.class);
        } else {
            parsers = new ArrayList<>(parsers);
            parseHex = parsers.remove(HexParser.class);
            parsers.remove(MiniMessageParser.class);
        }

        String legacyParsed = MiniMessageParser.legacyToMiniMessage(Parsers.parseString(string, player, parsers), parseHex);
        return MiniMessage.miniMessage().deserialize(legacyParsed, Resolvers.getResolver((Audience) player, resolvers));
    }
}
