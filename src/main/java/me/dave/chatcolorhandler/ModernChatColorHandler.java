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
        ChatColorHandler.translate("init");
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static Component translate(@Nullable String string) {
        return translate(string, null, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param parsers Parsers which this message won't be parsed through
     */
    public static Component translate(@Nullable String string, List<Class<? extends Parser>> parsers) {
        return translate(string, null, parsers, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public static Component translate(@Nullable String string, Player player) {
        return translate(string, player, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static Component translate(@Nullable String string, Player player, List<Class<? extends Parser>> parsers) {
        return translate(string, player, parsers, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     * @param resolvers Resolvers which will be used for this message (Paper + forks only)
     */
    public static Component translate(@Nullable String string, Player player, List<Class<? extends Parser>> parsers, List<Class<? extends Resolver>> resolvers) {
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

    /**
     * @deprecated Refactored to {@link ModernChatColorHandler#translate(String)}.
     *
     * @param string String to be converted
     */
    @Deprecated(forRemoval = true)
    public static Component translateAlternateColorCodes(@Nullable String string) {
        return translate(string, null, null, null);
    }

    /**
     * @deprecated Refactored to {@link ModernChatColorHandler#translate(String, List)}.
     *
     * @param string String to be converted
     * @param parsers Parsers which this message won't be parsed through
     */
    @Deprecated(forRemoval = true)
    public static Component translateAlternateColorCodes(@Nullable String string, List<Class<? extends Parser>> parsers) {
        return translate(string, null, parsers, null);
    }

    /**
     * @deprecated Refactored to {@link ModernChatColorHandler#translate(String, Player)}.
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    @Deprecated(forRemoval = true)
    public static Component translateAlternateColorCodes(@Nullable String string, Player player) {
        return translate(string, player, null, null);
    }

    /**
     * @deprecated Refactored to {@link ModernChatColorHandler#translate(String, Player, List)}.
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    @Deprecated(forRemoval = true)
    public static Component translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers) {
        return translate(string, player, parsers, null);
    }

    /**
     * @deprecated Refactored to {@link ModernChatColorHandler#translate(String, Player, List, List)}.
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     * @param resolvers Resolvers which will be used for this message (Paper + forks only)
     */
    @Deprecated(forRemoval = true)
    public static Component translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers, List<Class<? extends Resolver>> resolvers) {
        return translate(string, player, parsers, resolvers);
    }
}
