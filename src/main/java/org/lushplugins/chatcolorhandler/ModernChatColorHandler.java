package org.lushplugins.chatcolorhandler;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.Parsers;
import org.lushplugins.chatcolorhandler.parsers.custom.*;
import org.lushplugins.chatcolorhandler.resolvers.Resolver;
import org.lushplugins.chatcolorhandler.resolvers.Resolvers;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class ModernChatColorHandler {
    // TODO: Consider accepting an audience instead of player

    static {
        ChatColorHandler.translate("init");
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static Component translate(@Nullable String string) {
        return translate(string, null, Collections.emptyList(), Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param parsers Parsers which this message will be parsed through (Empty will allow all parsers)
     */
    public static Component translate(@Nullable String string, @NotNull List<Class<? extends Parser>> parsers) {
        return translate(string, null, parsers, Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public static Component translate(@Nullable String string, Player player) {
        return translate(string, player, Collections.emptyList(), Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static Component translate(@Nullable String string, Player player, @NotNull List<Class<? extends Parser>> parsers) {
        return translate(string, player, parsers, Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     * @param resolvers Resolvers which will be used for this message (Paper + forks only)
     */
    public static Component translate(@Nullable String string, Player player, @NotNull List<Class<? extends Parser>> parsers, @NotNull List<Class<? extends Resolver>> resolvers) {
        if (string == null || string.isBlank()) return Component.empty();

        TagResolver.Builder resolverBuilder = TagResolver.builder();
        if (parsers.isEmpty() || parsers.contains(MiniMessageColorParser.class)) {
            resolverBuilder.resolver(StandardTags.defaults());
        }

        if (parsers.isEmpty() || parsers.contains(MiniMessageResolverParser.class)) {
            resolverBuilder.resolver(Resolvers.getResolver((Audience) player, resolvers));
        }

        String legacyParsed = Parsers.parseString(string, Parser.OutputType.MINI_MESSAGE, player, parsers);
        return MiniMessageMessenger.MINI_MESSAGE.deserialize(legacyParsed, resolverBuilder.build());
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
    public static List<Component> translate(@NotNull Collection<String> strings, @NotNull List<Class<? extends Parser>> parsers) {
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
