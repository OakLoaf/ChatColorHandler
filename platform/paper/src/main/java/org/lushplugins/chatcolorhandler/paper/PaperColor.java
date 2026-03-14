package org.lushplugins.chatcolorhandler.paper;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.ColorHandler;
import org.lushplugins.chatcolorhandler.common.parser.*;
import org.lushplugins.chatcolorhandler.paper.resolver.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PaperColor extends ColorHandler<Component> {
    private static final PaperColor INSTANCE = new PaperColor();
    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
        .tags(TagResolver.empty())
        .build();
    private static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.builder()
        .character('§')
        .hexColors()
        .useUnusualXRepeatedCharacterHexFormat()
        .build();

    private final ResolverRegistry resolvers = new ResolverRegistry();

    public PaperColor() {
        parsers().register(HexParser.INSTANCE, 80);
        parsers().register(LegacyHexParser.INSTANCE, 70);
        parsers().register(LegacyAmpersandParser.INSTANCE, 69);
        parsers().register(SoundParser.INSTANCE, 60);
        parsers().register(ParticleParser.INSTANCE, 59);

        resolvers().register(
            MiniMessageColorResolver.INSTANCE,
            MiniMessageDefaultsResolver.INSTANCE,
            MiniMessageInteractionResolver.INSTANCE,
            MiniMessagePlaceholderResolver.INSTANCE,
            MiniMessageTextFormattingResolver.INSTANCE,
            MiniPlaceholdersResolver.INSTANCE
        );

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.isPluginEnabled("PlaceholderAPI")) {
            parsers().register(PlaceholderAPIParser.INSTANCE, 90);
        }

        if (pluginManager.isPluginEnabled("MiniPlaceholders")) {
            resolvers().register(MiniPlaceholdersResolver.INSTANCE);
        }

        List<Parser> defaultParsers = new ArrayList<>(parsers().values());
        defaultParsers.addAll(resolvers().values());
        settings().defaultParsers(defaultParsers);
    }

    public ResolverRegistry resolvers() {
        return resolvers;
    }

    @Override
    public Component translate(@Nullable String string, @Nullable Player player, Collection<Parser> parsers) {
        if (string == null || string.isBlank()) {
            return Component.empty();
        }

        String translated = parsers().parseString(string, player, parsers);
        TagResolver resolver = resolvers.asTagResolver(player, parsers.stream()
            .filter(parser -> parser instanceof Resolver)
            .map(parser -> (Resolver) parser)
            .toList());

        if (player != null) {
            return MINI_MESSAGE.deserialize(translated, player, resolver);
        } else {
            return MINI_MESSAGE.deserialize(translated, resolver);
        }
    }

    public String translateRaw(@Nullable String string, @Nullable Player player, Collection<Parser> parsers) {
        return LEGACY_SERIALIZER.serialize(translate(string, player, parsers));
    }

    public String translateRaw(@Nullable String string, @Nullable Player player) {
        return translateRaw(string, player, settings().defaultParsers());
    }

    public String translateRaw(@Nullable String string, Collection<Parser> parsers) {
        return translateRaw(string, null, parsers);
    }

    public String translateRaw(@Nullable String string) {
        return translateRaw(string, null, settings().defaultParsers());
    }

    @Override
    public void sendMessage(CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Component translated = translate(message, recipient instanceof Player player ? player : null);
        recipient.sendMessage(translated);
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Component translated = translate(message);
        Bukkit.getServer().sendMessage(translated);
    }

    @Override
    public void sendActionBarMessage(Player player, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Component translated = translate(message, player);
        player.sendActionBar(translated);
    }

    @Override
    public void sendTitle(Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        if ((title == null || title.isBlank()) && (subtitle == null || subtitle.isBlank())) {
            return;
        }

        Component translatedTitle = translate(title, player);
        Component translatedSubtitle = translate(subtitle, player);
        Title.Times times = Title.Times.times(
            Duration.ofMillis(fadeIn * 50L),
            Duration.ofMillis(stay * 50L),
            Duration.ofMillis(fadeOut * 50L)
        );

        player.sendTitlePart(TitlePart.TIMES, times);
        player.sendTitlePart(TitlePart.SUBTITLE, translatedSubtitle);
        player.sendTitlePart(TitlePart.TITLE, translatedTitle);
    }

    public static PaperColor handler() {
        return INSTANCE;
    }
}
