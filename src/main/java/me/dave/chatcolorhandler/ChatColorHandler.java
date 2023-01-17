package me.dave.chatcolorhandler;

import me.dave.chatcolorhandler.utils.LegacyComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorHandler {
    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * Sends this sender a message
     *
     * @param sender Player to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender sender, @NotNull String message) {
        sender.sendMessage(translateAlternateColorCodes(message));
    }

    /**
     * Sends this sender multiple messages
     *
     * @param sender   Player to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull CommandSender sender, @NotNull String... messages) {
        sendMessage(sender, String.join(" ", messages));
    }

    /**
     * Sends this sender multiple messages
     *
     * @param senders Players to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(CommandSender[] senders, @NotNull String message) {
        for (CommandSender sender : senders) {
            sendMessage(sender, message);
        }
    }

    /**
     * Sends this sender multiple messages
     *
     * @param senders  Players to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(CommandSender[] senders, @NotNull String... messages) {
        String message = String.join(" ", messages);
        for (CommandSender sender : senders) {
            sendMessage(sender, message);
        }
    }

    /**
     * Converts message to a BaseComponent[]
     *
     * @param message Messages to be displayed
     */
    public static String translateAlternateColorCodes(String message) {
        message = message.replaceAll("§", "&");

        // Parse message through MiniMessage
        message = LegacyComponentSerializer.builder().build().serialize(miniMessage.deserialize(message));

        // Parse message through Default Hex in format "&#rrggbb"
        Matcher match = hexPattern.matcher(message);
        while (match.find()) {
            String color = message.substring(match.start() + 1, match.end());
            message = message.replace("&" + color, ChatColor.of(color) + "");
            match = hexPattern.matcher(message);
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
