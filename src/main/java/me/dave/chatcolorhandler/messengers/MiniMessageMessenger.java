package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.parsers.custom.MiniMessageParser;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MiniMessageMessenger extends AbstractMessenger {
    private final BukkitAudiences adventure;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public MiniMessageMessenger(Plugin plugin) {
        this.adventure = BukkitAudiences.create(plugin);
    }

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = adventure.sender(recipient);
        audience.sendMessage(miniMessage.deserialize(ChatColorHandler.translateAlternateColorCodes(message, (recipient instanceof Player player ? player : null), List.of(MiniMessageParser.class))));
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = adventure.all();
        audience.sendMessage(miniMessage.deserialize(message));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = adventure.player(player);
        audience.sendActionBar(miniMessage.deserialize(message));
    }
}
