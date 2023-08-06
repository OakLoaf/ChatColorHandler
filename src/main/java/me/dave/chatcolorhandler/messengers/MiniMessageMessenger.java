package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.parsers.custom.MiniMessageParser;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MiniMessageMessenger extends AbstractMessenger {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) recipient);
        TextComponent legacyParsed = LegacyComponentSerializer.legacy('§').deserialize(ChatColorHandler.translateAlternateColorCodes(message, (recipient instanceof Player player ? player : null), List.of(MiniMessageParser.class)));
        audience.sendMessage(miniMessage.deserialize(legacyParsed.content()));
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) Bukkit.getOnlinePlayers());
        TextComponent legacyParsed = LegacyComponentSerializer.legacy('§').deserialize(message);
        audience.sendMessage(miniMessage.deserialize(legacyParsed.content()));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) player);
        TextComponent legacyParsed = LegacyComponentSerializer.legacy('§').deserialize(message);
        audience.sendActionBar(miniMessage.deserialize(legacyParsed.content()));
    }
}
