package me.dave.chatcolorhandler;

import me.dave.chatcolorhandler.messengers.LegacyMessenger;
import me.dave.chatcolorhandler.messengers.Messenger;
import me.dave.chatcolorhandler.messengers.MiniMessageMessenger;
import me.dave.chatcolorhandler.parsers.Parsers;
import me.dave.chatcolorhandler.parsers.custom.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("unused")
public class ChatColorHandler {
    private static final String LOGGER_PREFIX = "[ChatColorHandler] ";
    private static final Pattern HEX_PATTERN = Pattern.compile("&#[a-fA-F0-9]{6}");
    private static Messenger messenger;

    static {
        Parsers.register(new LegacyCharParser(), 100);
        Parsers.register(new HexParser(), 70);

        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage");
            Parsers.register(new MiniMessageParser(), 80);
            messenger = new MiniMessageMessenger();

            Bukkit.getLogger().info(LOGGER_PREFIX + "Found MiniMessage in Server. MiniMessage support enabled.");
        } catch (ClassNotFoundException ignored) {
            messenger = new LegacyMessenger();
            Bukkit.getLogger().info(LOGGER_PREFIX + "Unable to find MiniMessage. MiniMessage support not enabled.");
        }

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null && pluginManager.isPluginEnabled("PlaceholderAPI")) {
            Parsers.register(new PlaceholderAPIParser(), 90);
            Bukkit.getLogger().info(LOGGER_PREFIX + "Found plugin \"PlaceholderAPI\". PlaceholderAPI support enabled.");
        }
    }

    /**
     * Sends a recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        messenger.sendMessage(recipient, message);
    }

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages) {
        messenger.sendMessage(recipient, messages);
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @Nullable String message) {
        messenger.sendMessage(recipients, message);
    }

    /**
     * Sends multiple recipients, multiple messages
     *
     * @param recipients Senders to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @Nullable String... messages) {
        messenger.sendMessage(recipients, messages);
    }

    /**
     * Sends all online players a message
     *
     * @param message Message to be displayed
     */
    public static void broadcastMessage(@Nullable String message) {
        messenger.broadcastMessage(message);
    }

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    public static void broadcastMessage(@NotNull String... messages) {
        messenger.broadcastMessage(messages);
    }

    /**
     * Sends a player an ACTION_BAR message
     *
     * @param player Player to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        messenger.sendActionBarMessage(player, message);
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player[] players, @Nullable String message) {
        messenger.sendActionBarMessage(players, message);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title) {
        messenger.sendTitle(player, title);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        messenger.sendTitle(player, title, subtitle);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        messenger.sendTitle(player, title, subtitle, fadeIn, fadeOut);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param stay Duration for title to appear
     * @param fadeOut Duration for title to fade out
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        messenger.sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title) {
        messenger.sendTitle(players, title);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle) {
        messenger.sendTitle(players, title, subtitle);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        messenger.sendTitle(players, title, subtitle, fadeIn, fadeOut);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param stay Duration for title to appear
     * @param fadeOut Duration for title to fade out
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        messenger.sendTitle(players, title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     */
    public static String translateAlternateColorCodes(@Nullable String string) {
        return translateAlternateColorCodes(string, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param parsers Parsers which this message won't be parsed through
     */
    public static String translateAlternateColorCodes(@Nullable String string, List<Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return "";

        return Parsers.parseString(string, null, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     */
    public static String translateAlternateColorCodes(@Nullable String string, Player player) {
        return translateAlternateColorCodes(string, player, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static String translateAlternateColorCodes(@Nullable String string, Player player, List<Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return "";

        return Parsers.parseString(string, player, parsers);
    }

    /**
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     */
    public static List<String> translateAlternateColorCodes(@Nullable List<String> strings) {
        return translateAlternateColorCodes(strings, null, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translateAlternateColorCodes(@Nullable List<String> strings, List<Class<? extends Parser>> parsers) {
        return translateAlternateColorCodes(strings, null, parsers);
    }

    /**
     * Translates multiple strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     * @param player Player to parse placeholders for
     */
    public static List<String> translateAlternateColorCodes(@Nullable List<String> strings, Player player) {
        return translateAlternateColorCodes(strings, player, null);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param strings Strings to be converted
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translateAlternateColorCodes(@Nullable List<String> strings, Player player, List<Class<? extends Parser>> parsers) {
        if (strings == null || strings.isEmpty()) return Collections.emptyList();

        List<String> outputList = new ArrayList<>();
        for (String string : strings) {
            outputList.add(translateAlternateColorCodes(string, player, parsers));
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
        string = string.replace("§", "&");

        // Parse message through Default Hex in format "&#rrggbb"
        Matcher match = HEX_PATTERN.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, ChatColor.of(color) + "");
            match = HEX_PATTERN.matcher(string);
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
