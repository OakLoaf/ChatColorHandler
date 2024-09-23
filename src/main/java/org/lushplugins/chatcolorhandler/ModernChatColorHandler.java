package org.lushplugins.chatcolorhandler;

import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.Parsers;
import org.lushplugins.chatcolorhandler.parsers.custom.*;
import org.lushplugins.chatcolorhandler.resolvers.Resolver;
import org.lushplugins.chatcolorhandler.resolvers.Resolvers;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
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
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     */
    public static List<Component> translate(@NotNull Collection<String> strings) {
        return strings.stream().map(ModernChatColorHandler::translate).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parsers Parsers which this message won't be parsed through
     */
    public static List<Component> translate(@NotNull Collection<String> strings, List<Class<? extends Parser>> parsers) {
        return strings.stream().map(string -> ModernChatColorHandler.translate(string, parsers)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     */
    public static List<Component> translate(@NotNull Collection<String> strings, Player player) {
        return strings.stream().map(string -> ModernChatColorHandler.translate(string, player)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<Component> translate(@NotNull Collection<String> strings, Player player, List<Class<? extends Parser>> parsers) {
        return strings.stream().map(string -> ModernChatColorHandler.translate(string, player, parsers)).toList();
    }
}
