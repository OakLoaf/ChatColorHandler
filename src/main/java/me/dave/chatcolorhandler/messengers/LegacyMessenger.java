package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LegacyMessenger extends AbstractMessenger {

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        if (recipient instanceof Player player) recipient.sendMessage(ChatColorHandler.translateAlternateColorCodes(message, player));
        else recipient.sendMessage(ChatColorHandler.translateAlternateColorCodes(message));
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;
        Bukkit.broadcastMessage(ChatColorHandler.translateAlternateColorCodes(message));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColorHandler.translateAlternateColorCodes(message)));
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title) {
        player.sendTitle(ChatColorHandler.translateAlternateColorCodes(title), null, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        player.sendTitle(ChatColorHandler.translateAlternateColorCodes(title), ChatColorHandler.translateAlternateColorCodes(subtitle), 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        player.sendTitle(ChatColorHandler.translateAlternateColorCodes(title), ChatColorHandler.translateAlternateColorCodes(subtitle), fadeIn, 70, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(ChatColorHandler.translateAlternateColorCodes(title), ChatColorHandler.translateAlternateColorCodes(subtitle), fadeIn, stay, fadeOut);
    }
}
