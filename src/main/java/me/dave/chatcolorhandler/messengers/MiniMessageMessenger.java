package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.parsers.custom.LegacyCharParser;
import me.dave.chatcolorhandler.parsers.custom.MiniMessageParser;
import me.dave.chatcolorhandler.parsers.custom.PlaceholderAPIParser;
import me.dave.chatcolorhandler.resolvers.Resolvers;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.List;

public class MiniMessageMessenger extends AbstractMessenger {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) recipient);
        
        String legacyParsed = MiniMessageParser.legacyToMiniMessage(ChatColorHandler.translateAlternateColorCodes(message, (recipient instanceof Player player ? player : null), List.of(LegacyCharParser.class, PlaceholderAPIParser.class)), true);
        Component parsed = miniMessage.deserialize(legacyParsed, Resolvers.getResolver(audience, null));

        audience.sendMessage(parsed);
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) Bukkit.getServer());
        String legacyParsed = MiniMessageParser.legacyToMiniMessage(ChatColorHandler.translateAlternateColorCodes(message, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)), true);
        audience.sendMessage(miniMessage.deserialize(legacyParsed, Resolvers.getResolver(audience, null)));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) player);
        String legacyParsed = MiniMessageParser.legacyToMiniMessage(ChatColorHandler.translateAlternateColorCodes(message, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)), true);
        audience.sendActionBar(miniMessage.deserialize(legacyParsed, Resolvers.getResolver(audience, null)));
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title) {
        if (title == null || title.isBlank()) return;

        sendTitle(player, title, null, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        sendTitle(player, title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(player, title, subtitle, fadeIn, 70, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        Audience audience = Audience.audience((Audience) player);
        TagResolver resolver = Resolvers.getResolver(audience, null);

        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(stay * 50L), Duration.ofMillis(fadeOut * 50L));
        String subtitleLegacyParsed = MiniMessageParser.legacyToMiniMessage(ChatColorHandler.translateAlternateColorCodes(subtitle, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)), true);
        String titleLegacyParsed = MiniMessageParser.legacyToMiniMessage(ChatColorHandler.translateAlternateColorCodes(title, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)), true);

        audience.sendTitlePart(TitlePart.TIMES, times);
        audience.sendTitlePart(TitlePart.SUBTITLE, miniMessage.deserialize(subtitleLegacyParsed, resolver));
        audience.sendTitlePart(TitlePart.TITLE, miniMessage.deserialize(titleLegacyParsed, resolver));
    }
}
