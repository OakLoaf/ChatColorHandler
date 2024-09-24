package org.lushplugins.chatcolorhandler;

import org.lushplugins.chatcolorhandler.messengers.LegacyMessenger;
import org.lushplugins.chatcolorhandler.messengers.Messenger;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Parsers;
import org.lushplugins.chatcolorhandler.parsers.custom.*;
import org.lushplugins.chatcolorhandler.resolvers.MiniPlaceholdersResolver;
import org.lushplugins.chatcolorhandler.resolvers.Resolvers;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unused")
public class ChatColorHandler {
    private static Messenger messenger;

    static {
        Parsers.register(new LegacyHexParser(), 85);
        Parsers.register(new LegacySpigotParser(), 84);
        Parsers.register(new HexParser(), 83);

        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage").getMethod("miniMessage");
            messenger = new MiniMessageMessenger();
            Parsers.register(new MiniMessageColorParser(), 70);
            Parsers.register(new MiniMessageResolverParser(), 89);
            debugLog("Found MiniMessage in Server. MiniMessage support enabled.");
        } catch (ClassNotFoundException | NoSuchMethodException ignored) {
            messenger = new LegacyMessenger();
            debugLog("Unable to find MiniMessage. MiniMessage support not enabled.");
        }

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null && pluginManager.isPluginEnabled("PlaceholderAPI")) {
            Parsers.register(new PlaceholderAPIParser(), 90);
            debugLog("Found plugin \"PlaceholderAPI\". PlaceholderAPI support enabled.");
        }

        if (pluginManager.getPlugin("MiniPlaceholders") != null && pluginManager.isPluginEnabled("MiniPlaceholders")) {
            Resolvers.register(new MiniPlaceholdersResolver());
            debugLog("Found plugin \"MiniPlaceholders\". MiniPlaceholders support enabled.");
        }
    }

    protected static Messenger getMessenger() {
        return messenger;
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
     * @param string String to be translated
     */
    public static String translate(@Nullable String string) {
        return translate(string, null, Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param parsers Parsers which this message won't be parsed through
     */
    public static String translate(@Nullable String string, @NotNull List<? extends Class<? extends Parser>> parsers) {
        return translate(string, null, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param parserTypes Parsers which this message won't be parsed through
     */
    public static String translate(@Nullable String string, @NotNull String... parserTypes) {
        return translate(string, null, parserTypes);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     */
    public static String translate(@Nullable String string, Player player) {
        return translate(string, player, Collections.emptyList());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static String translate(@Nullable String string, Player player, List<? extends Class<? extends Parser>> parsers) {
        if (string == null || string.isBlank()) return "";

        return Parsers.parseString(string, Parser.OutputType.SPIGOT, player, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     * @param parserTypes Parser types which this message will be parsed through
     */
    public static String translate(@Nullable String string, Player player, String... parserTypes) {
        if (string == null || string.isBlank()) return "";

        return Parsers.parseString(string, Parser.OutputType.SPIGOT, player, parserTypes);
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     */
    public static List<String> translate(@NotNull Collection<String> strings) {
        return strings.stream().map(ChatColorHandler::translate).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, List<? extends Class<? extends Parser>> parsers) {
        return strings.stream().map(string -> ChatColorHandler.translate(string, parsers)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parserTypes Parser types which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, String... parserTypes) {
        return strings.stream().map(string -> ChatColorHandler.translate(string, parserTypes)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     */
    public static List<String> translate(@NotNull Collection<String> strings, Player player) {
        return strings.stream().map(string -> ChatColorHandler.translate(string, player)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, Player player, List<? extends Class<? extends Parser>> parsers) {
        return strings.stream().map(string -> ChatColorHandler.translate(string, player, parsers)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     * @param parserTypes Parser types which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, Player player, String... parserTypes) {
        return strings.stream().map(string -> ChatColorHandler.translate(string, player, parserTypes)).toList();
    }

    /**
     * Strips colour from string
     *
     * @param string String to be converted
     */
    public static String stripColor(@Nullable String string) {
        if (string == null || string.isBlank()) {
            return "";
        }

        return ChatColor.stripColor(ChatColorHandler.translate(string, ParserTypes.COLOR));
    }

    private static void debugLog(String log) {
        if (ChatColorHandlerDebugger.debug()) {
            Bukkit.getLogger().info("[ChatColorHandler] " + log);
        }
    }
}
