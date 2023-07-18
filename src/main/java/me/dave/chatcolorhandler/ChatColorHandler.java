package me.dave.chatcolorhandler;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorHandler {
    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");
    private static boolean isMiniMessageEnabled = false;
    private static PlaceholderAPIParser placeholderAPIHook;

    /**
     * Sends this recipient a message
     *
     * @param message Message to be displayed
     */
    public static void broadcastMessage(@NotNull String message) {
        Bukkit.broadcastMessage(translateAlternateColorCodes(message));
    }

    /**
     * Sends this recipient multiple messages
     *
     * @param messages Messages to be displayed
     */
    public static void broadcastMessage(@NotNull String... messages) {
        broadcastMessage(String.join(" ", messages));
    }

    /**
     * Sends this recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @NotNull String message) {
        if (message.isBlank()) return;

        if (recipient instanceof Player player) recipient.sendMessage(translateAlternateColorCodes(player, message));
        else recipient.sendMessage(translateAlternateColorCodes(message));
    }

    /**
     * Sends this recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @NotNull String... messages) {
        sendMessage(recipient, String.join(" ", messages));
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @NotNull String message) {
        for (CommandSender recipient : recipients) {
            sendMessage(recipient, message);
        }
    }

    /**
     * Sends multiple recipients, multiple messages
     *
     * @param recipients Senders to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @NotNull String... messages) {
        String message = String.join(" ", messages);
        for (CommandSender recipient : recipients) {
            sendMessage(recipient, message);
        }
    }

    /**
     * Sends this player an ACTION_BAR message
     *
     * @param player Player to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player player, @NotNull String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translateAlternateColorCodes(message)));
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player[] players, @NotNull String message) {
        for (Player player : players) {
            sendActionBarMessage(player, message);
        }
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static String translateAlternateColorCodes(String string) {
        return translateAlternateColorCodes(null, string);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param player Player to parse placeholders for
     * @param string String to be converted
     */
    public static String translateAlternateColorCodes(Player player, String string) {
        // Parse message through PlaceholderAPI
        if (placeholderAPIHook != null) {
            string = placeholderAPIHook.parseString(player, string);
        }

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
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(List<String> strings) {
        return translateAlternateColorCodes(null, strings);
    }

    /**
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param player Player to parse placeholders for
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(Player player, List<String> strings) {
        List<String> outputList = new ArrayList<>();
        for (String string : strings) {
            outputList.add(translateAlternateColorCodes(string));
        }
        return outputList;
    }

    /**
     * Strips colour from string
     *
     * @param string String to be converted
     */
    public static String stripColor(String string) {
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
        return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', string));
    }

    /**
     * Strips colour from strings
     *
     * @param strings Strings to be converted
     */
    public static List<String> stripColor(List<String> strings) {
        List<String> outputList = new ArrayList<>();
        for (String string : strings) {
            outputList.add(stripColor(string));
        }
        return outputList;
    }

    /**
     * Enables MiniMessage to be parsed
     * This only allows for colours, text decorations and gradients
     *
     * @param enable Whether to enable MiniMessage
     */
    public static void enableMiniMessage(boolean enable) {
        isMiniMessageEnabled = enable;
    }

    /**
     * Enables PlaceholderAPI placeholders to be parsed
     * You must have PlaceholderAPI installed on your server
     *
     * @param enable Whether to enable PlaceholderAPI
     */
    public static void enablePlaceholderAPI(boolean enable) {
        if (enable && placeholderAPIHook == null) placeholderAPIHook = new PlaceholderAPIParser();
        else if (!enable) placeholderAPIHook = null;
    }
}
