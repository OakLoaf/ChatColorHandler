package me.dave.chatcolorhandler;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorHandler {
    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    private static boolean isMiniMessageEnabled = false;

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
     * Converts string to a BaseComponent[]
     *
     * @param string String to be converted
     */
    public static String translateAlternateColorCodes(String string) {
        // Parse message through MiniMessage
        if (isMiniMessageEnabled) {
            string = MiniMessageTranslator.translateFromMiniMessage(string);
        }

        // Replace legacy character
        string = string.replaceAll("§", "&");

        // Parse message through Default Hex in format "&#rrggbb"
        Matcher match = hexPattern.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, ChatColor.of(color) + "");
            match = hexPattern.matcher(string);
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Converts strings to a BaseComponent[]
     *
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(List<String> strings) {
        List<String> outputList = new ArrayList<>();
        for (String string : strings) {
            outputList.add(translateAlternateColorCodes(string));
        }
        return outputList;
    }

    /**
     * Enables MiniMessage to be parsed.
     * This only allows for colours, text decorations and gradients
     *
     * @param enable Whether to enable MiniMessage
     */
    public static void enableMiniMessage(boolean enable) {
        isMiniMessageEnabled = enable;
    }
}
