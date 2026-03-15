package org.lushplugins.chatcolorhandler.common;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.Parser;
import org.lushplugins.chatcolorhandler.common.parser.ParserRegistry;
import org.lushplugins.chatcolorhandler.common.settings.ColorHandlerSettings;

import java.util.Collection;
import java.util.List;

/**
 * @param <T> Translation output type
 */
@SuppressWarnings("unused")
public abstract class ColorHandler<T> {
    private final ColorHandlerSettings settings = new ColorHandlerSettings();
    private final ParserRegistry parsers = new ParserRegistry();

    public ColorHandlerSettings settings() {
        return settings;
    }

    public ParserRegistry parsers() {
        return parsers;
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public abstract T translate(@Nullable String string, @Nullable Player player, Collection<Parser> parsers);

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param player Player to parse placeholders for
     */
    public T translate(@Nullable String string, @Nullable Player player) {
        return translate(string, player, settings.defaultParsers());
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     * @param parsers Parsers which this message won't be parsed through
     */
    public T translate(@Nullable String string, Collection<Parser> parsers) {
        return translate(string, null, parsers);
    }

    /**
     * Translates a string to allow for hex colours and placeholders
     *
     * @param string String to be translated
     */
    public T translate(@Nullable String string) {
        return translate(string, null, settings.defaultParsers());
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     * @param parsers Parsers which this message will be parsed through
     */
    public List<T> translate(Collection<String> strings, Player player, List<Parser> parsers) {
        return strings.stream().map(string -> translate(string, player, parsers)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param player Player to parse placeholders for
     */
    public List<T> translate(Collection<String> strings, Player player) {
        return strings.stream().map(string -> translate(string, player)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     * @param parsers Parsers which this message will be parsed through
     */
    public List<T> translate(Collection<String> strings, List<Parser> parsers) {
        return strings.stream().map(string -> translate(string, parsers)).toList();
    }

    /**
     * Translates a collection of strings to allow for hex colours and placeholders
     *
     * @param strings Strings to be translated
     */
    public List<T> translate(Collection<String> strings) {
        return strings.stream().map(this::translate).toList();
    }

    /**
     * Sends a recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    public abstract void sendMessage(CommandSender recipient, @Nullable String message);

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    public void sendMessage(CommandSender recipient, String... messages) {
        for (String message : messages) {
            sendMessage(recipient, message);
        }
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    public void sendMessage(Collection<CommandSender> recipients, @Nullable String message) {
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
    public void sendMessage(Collection<CommandSender> recipients, String... messages) {
        for (String message : messages) {
            sendMessage(recipients, message);
        }
    }

    /**
     * Sends all online players a message
     *
     * @param message Message to be displayed
     */
    public abstract void broadcastMessage(@Nullable String message);

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    public void broadcastMessage(String... messages) {
        for (String message : messages) {
            broadcastMessage(message);
        }
    }

    /**
     * Sends a player an action bar message
     *
     * @param player Player to receive this action bar message
     * @param message Message to be displayed
     */
    public abstract void sendActionBarMessage(Player player, @Nullable String message);

    /**
     * Sends multiple players an action bar message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    public void sendActionBarMessage(Collection<Player> players, @Nullable String message) {
        for (Player player : players) {
            sendActionBarMessage(player, message);
        }
    }

    /**
     * Send a player a title message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param stay Duration for title to appear
     * @param fadeOut Duration for title to fade out
     */
    public abstract void sendTitle(Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Send a player a title message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    public void sendTitle(Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(player, title, subtitle, fadeIn, 70, fadeOut);
    }

    /**
     * Send a player a title message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public void sendTitle(Player player, @Nullable String title, @Nullable String subtitle) {
        sendTitle(player, title, subtitle, 10, 20);
    }

    /**
     * Send a player a title message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     */
    public void sendTitle(Player player, @Nullable String title) {
        sendTitle(player, title, null);
    }

    /**
     * Send multiple players a title message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param stay Duration for title to appear
     * @param fadeOut Duration for title to fade out
     */
    public void sendTitle(Collection<Player> players, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        for (Player player : players) {
            sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
        }
    }

    /**
     * Send multiple players a title message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    public void sendTitle(Collection<Player> players, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(players, title, subtitle, fadeIn, 70, fadeOut);
    }

    /**
     * Send multiple players a title message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    public void sendTitle(Collection<Player> players, @Nullable String title, @Nullable String subtitle) {
        sendTitle(players, title, subtitle, 10, 20);
    }

    /**
     * Send multiple players a title message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     */
    public void sendTitle(Collection<Player> players, @Nullable String title) {
        sendTitle(players, title, null);
    }
}
