package org.lushplugins.chatcolorhandler;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.Resolver;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@SuppressWarnings("unused")
public class ModernChatColorHandler {

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     */
    public static Component translate(@Nullable String string) {
        return translate(string, ChatColorHandler.settings().defaultParsers());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string  String to be translated
     * @param parsers Parsers which this message won't be parsed through
     */
    public static Component translate(@Nullable String string, @NotNull List<Parser> parsers) {
        return translate(string, null, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     */
    public static Component translate(@Nullable String string, Player player) {
        return translate(string, player, ChatColorHandler.settings().defaultParsers());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string    String to be converted
     * @param player    Player to parse placeholders for
     * @param parsers   Parsers which this message will be parsed through
     */
    public static Component translate(@Nullable String string, Player player, @NotNull List<Parser> parsers) {
        if (string == null || string.isBlank()) {
            return Component.empty();
        }

        String legacyParsed = ChatColorHandler.parsers().parseString(string, Parser.OutputType.MINI_MESSAGE, player, parsers);

        TagResolver tagResolver = Resolver.combineResolvers((Audience) player, parsers.stream()
            .map(parser -> parser instanceof Resolver resolver ? resolver : null)
            .filter(Objects::nonNull)
            .toList());

        return MiniMessageMessenger.MINI_MESSAGE.deserialize(legacyParsed, tagResolver);
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     */
    public static List<Component> translate(@NotNull Collection<String> strings) {
        return translate(strings, ChatColorHandler.settings().defaultParsers());
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<Component> translate(@NotNull Collection<String> strings, List<Parser> parsers) {
        return translate(strings, null, parsers);
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player  Player to parse placeholders for
     */
    public static List<Component> translate(@NotNull Collection<String> strings, Player player) {
        return translate(strings, player, ChatColorHandler.settings().defaultParsers());
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player  Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<Component> translate(@NotNull Collection<String> strings, Player player, List<Parser> parsers) {
        return strings.stream().map(string -> translate(string, player, parsers)).toList();
    }

    private ModernChatColorHandler() {}
}
