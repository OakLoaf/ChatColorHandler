package me.dave.chatcolorhandler;

import me.dave.chatcolorhandler.parsers.Parsers;
import me.dave.chatcolorhandler.parsers.custom.LegacyChatParser;
import me.dave.chatcolorhandler.parsers.custom.MiniMessageParser;
import me.dave.chatcolorhandler.parsers.custom.PlaceholderAPIParser;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorHandler {
    private static boolean setup = false;
    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    /**
     * Setup ChatColorHandler for use
     *
     * @param plugin Plugin instance
     */
    public static void setup(Plugin plugin) {
        if (setup) return;

        Parsers.register(new LegacyChatParser(), 0);

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null) {
            Parsers.register(new PlaceholderAPIParser(), 100);
            plugin.getLogger().info("Found plugin \"PlaceholderAPI\". PlaceholderAPI support enabled.");
        }

        try {
            Class.forName("com.destroystokyo.paper.PaperConfig");
            Parsers.register(new MiniMessageParser(), 99);
            plugin.getLogger().info("Server running on PaperMC (or fork). MiniMessage support enabled.");
        } catch (ClassNotFoundException ignored) {}

        setup = true;
        plugin.getLogger().info("ChatColorHandler has successfully hooked into " + plugin.getName() + ".");
    }

    /**
     * Sends this recipient a message
     *
     * @param message Message to be displayed
     */
    public static void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;
        Bukkit.broadcastMessage(translateAlternateColorCodes(message));
    }

    /**
     * Sends this recipient multiple messages
     *
     * @param messages Messages to be displayed
     */
    public static void broadcastMessage(@NotNull String... messages) {
        if (messages == null) return;
        broadcastMessage(String.join(" ", messages));
    }

    /**
     * Sends this recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        if (recipient instanceof Player player) recipient.sendMessage(translateAlternateColorCodes(message, player));
        else recipient.sendMessage(translateAlternateColorCodes(message));
    }

    /**
     * Sends this recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages) {
        sendMessage(recipient, String.join(" ", messages));
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @Nullable String message) {
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
    public static void sendMessage(CommandSender[] recipients, @Nullable String... messages) {
        if (messages == null) return;

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
    public static void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(translateAlternateColorCodes(message)));
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player[] players, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        for (Player player : players) {
            sendActionBarMessage(player, message);
        }
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static String translateAlternateColorCodes(@Nullable String string) {
        return translateAlternateColorCodes(string, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public static String translateAlternateColorCodes(@Nullable String string, Player player) {
        if (string == null || string.isBlank()) return "";

        return Parsers.parseString(string, player);
    }

    /**
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(@Nullable List<String> strings) {
        return translateAlternateColorCodes(null, strings);
    }

    /**
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param player Player to parse placeholders for
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(Player player, @Nullable List<String> strings) {
        if (strings == null || strings.isEmpty()) return Collections.emptyList();

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
    public static String stripColor(@Nullable String string) {
        if (string == null || string.isBlank()) return "";

        // Parse message through MiniMessage
//        if (isMiniMessageEnabled) {
//            string = TinyMessageTranslator.translateFromMiniMessage(string);
//        }

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
    public static List<String> stripColor(@Nullable List<String> strings) {
        if (strings == null || strings.isEmpty()) return Collections.emptyList();

        List<String> outputList = new ArrayList<>();
        for (String string : strings) {
            outputList.add(stripColor(string));
        }
        return outputList;
    }
}
