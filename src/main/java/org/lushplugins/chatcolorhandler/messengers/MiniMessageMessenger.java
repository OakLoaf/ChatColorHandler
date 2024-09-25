package org.lushplugins.chatcolorhandler.messengers;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.lushplugins.chatcolorhandler.ModernChatColorHandler;
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

public class MiniMessageMessenger extends AbstractMessenger {
    public static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
        .tags(TagResolver.empty())
        .build();
    public static final LegacyComponentSerializer LEGACY_COMPONENT_SERIALIZER = LegacyComponentSerializer.builder().build();


    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Audience audience = Audience.audience((Audience) recipient);
        Component parsed = ModernChatColorHandler.translate(message, (recipient instanceof Player player ? player : null));
        audience.sendMessage(parsed);
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Audience audience = Audience.audience((Audience) Bukkit.getServer());
        audience.sendMessage(ModernChatColorHandler.translate(message));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        Audience audience = Audience.audience((Audience) player);
        audience.sendActionBar(ModernChatColorHandler.translate(message, player));
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        if ((title == null || title.isBlank()) && (subtitle == null || subtitle.isBlank())) {
            return;
        }

        Audience audience = Audience.audience((Audience) player);
        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(stay * 50L), Duration.ofMillis(fadeOut * 50L));
        audience.sendTitlePart(TitlePart.TIMES, times);
        audience.sendTitlePart(TitlePart.SUBTITLE, ModernChatColorHandler.translate(subtitle, player));
        audience.sendTitlePart(TitlePart.TITLE, ModernChatColorHandler.translate(title, player));
    }
}
