package org.lushplugins.chatcolorhandler;

import org.bukkit.plugin.PluginManager;
import org.lushplugins.chatcolorhandler.messengers.LegacyMessenger;
import org.lushplugins.chatcolorhandler.messengers.Messenger;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Parsers;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.parsers.custom.*;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("unused")
public class ChatColorHandler {
    private static boolean initialised = false;
    private static Messenger messenger;
    private static final Parsers parsers = new Parsers();
    private static final ChatColorHandlerSettings settings = new ChatColorHandlerSettings();

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     */
    public static String translate(@Nullable String string) {
        return translate(string, settings.defaultParsers());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param parsers Parsers which this message won't be parsed through
     */
    public static String translate(@Nullable String string, @NotNull List<Parser> parsers) {
        return translate(string, null, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     */
    public static String translate(@Nullable String string, Player player) {
        return translate(string, player, settings.defaultParsers());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static String translate(@Nullable String string, Player player, List<Parser> parsers) {
        ensureInitialised();

        if (string == null || string.isBlank()) {
            return "";
        }

        return ChatColorHandler.parsers.parseString(string, Parser.OutputType.SPIGOT, player, parsers);
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     */
    public static List<String> translate(@NotNull Collection<String> strings) {
        return translate(strings, settings.defaultParsers());
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, List<Parser> parsers) {
        return translate(strings, null, parsers);
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     */
    public static List<String> translate(@NotNull Collection<String> strings, Player player) {
        return translate(strings, player, settings.defaultParsers());
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public static List<String> translate(@NotNull Collection<String> strings, Player player, List<Parser> parsers) {
        return strings.stream().map(string -> translate(string, player, parsers)).toList();
    }

    /**
     * Strips colour from string
     *
     * @param string String to be converted
     */
    public static String stripColor(@Nullable String string) {
        ensureInitialised();

        if (string == null || string.isBlank()) {
            return "";
        }

        return ChatColor.stripColor(translate(string, ParserTypes.all()));
    }

    /**
     * Sends a recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        messenger().sendMessage(recipient, message);
    }

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages) {
        messenger().sendMessage(recipient, messages);
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @Nullable String message) {
        messenger().sendMessage(recipients, message);
    }

    /**
     * Sends multiple recipients, multiple messages
     *
     * @param recipients Senders to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(CommandSender[] recipients, @Nullable String... messages) {
        messenger().sendMessage(recipients, messages);
    }

    /**
     * Sends all online players a message
     *
     * @param message Message to be displayed
     */
    public static void broadcastMessage(@Nullable String message) {
        messenger().broadcastMessage(message);
    }

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    public static void broadcastMessage(@NotNull String... messages) {
        messenger().broadcastMessage(messages);
    }

    /**
     * Sends a player an ACTION_BAR message
     *
     * @param player Player to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        messenger().sendActionBarMessage(player, message);
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    public static void sendActionBarMessage(@NotNull Player[] players, @Nullable String message) {
        messenger().sendActionBarMessage(players, message);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title) {
        messenger().sendTitle(player, title);
    }

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public static void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        messenger().sendTitle(player, title, subtitle);
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
        messenger().sendTitle(player, title, subtitle, fadeIn, fadeOut);
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
        messenger().sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title) {
        messenger().sendTitle(players, title);
    }

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public static void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle) {
        messenger().sendTitle(players, title, subtitle);
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
        messenger().sendTitle(players, title, subtitle, fadeIn, fadeOut);
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
        messenger().sendTitle(players, title, subtitle, fadeIn, stay, fadeOut);
    }

    public static Messenger messenger() {
        ensureInitialised();
        return messenger;
    }

    /**
     * Adjust the ChatColorHandler messenger to be used. This is handled automatically by ChatColorHandler but can be overridden for custom uses.
     * @param messenger The messenger to use
     */
    public static void messenger(Messenger messenger) {
        ChatColorHandler.messenger = messenger;
    }

    public static Parsers parsers() {
        ChatColorHandler.ensureInitialised();
        return parsers;
    }

    public static ChatColorHandlerSettings settings() {
        return settings;
    }

    protected static void debugLog(String log) {
        if (settings.debug()) {
            Bukkit.getLogger().info("[ChatColorHandler] " + log);
        }
    }

    public static synchronized void ensureInitialised() {
        if (!initialised) {
            init();
        }
    }

    private static void init() {
        initialised = true;
        Parsers parsers = ChatColorHandler.parsers();

        parsers.register(HexParser.INSTANCE, 83);
        parsers.register(SpigotParser.INSTANCE, 65);

        if (isPaper()) {
            ChatColorHandler.messenger(new MiniMessageMessenger());

            parsers.register(MiniMessageResolverParser.INSTANCE, 89);
            parsers.register(LegacyHexParser.INSTANCE, 75);
            parsers.register(LegacySpigotParser.INSTANCE, 74);
            parsers.register(MiniMessageColorParser.INSTANCE, 73);
            parsers.register(MiniMessageInteractionParser.INSTANCE, 72);
            parsers.register(MiniMessagePlaceholderParser.INSTANCE, 71);
            parsers.register(MiniMessageTextFormattingParser.INSTANCE, 70);
            ChatColorHandler.debugLog("Found MiniMessage in Server. MiniMessage support enabled.");
        } else {
            ChatColorHandler.messenger(new LegacyMessenger());
            ChatColorHandler.debugLog("Unable to find MiniMessage. MiniMessage support not enabled.");
        }

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null && pluginManager.isPluginEnabled("PlaceholderAPI")) {
            parsers.register(PlaceholderAPIParser.INSTANCE, 90);
            ChatColorHandler.debugLog("Found plugin \"PlaceholderAPI\". PlaceholderAPI support enabled.");
        }

        if (pluginManager.getPlugin("MiniPlaceholders") != null && pluginManager.isPluginEnabled("MiniPlaceholders")) {
            parsers.register(MiniPlaceholdersParser.INSTANCE, -1);
            ChatColorHandler.debugLog("Found plugin \"MiniPlaceholders\". MiniPlaceholders support enabled.");
        }

        settings().defaultParsers(ParserTypes.all());
    }

    private static boolean hasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    private static boolean isPaper() {
        if (hasClass("com.destroystokyo.paper.PaperConfig") || hasClass("io.papermc.paper.configuration.Configuration")) {
            try {
                Class.forName("net.kyori.adventure.text.minimessage.MiniMessage").getMethod("miniMessage");
                return true;
            } catch (ClassNotFoundException | NoSuchMethodException ignored) {}
        }

        return false;
    }

    private ChatColorHandler() {}
}
