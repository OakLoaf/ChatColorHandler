package org.lushplugins.chatcolorhandler.messengers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMessenger implements Messenger {

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive messages
     * @param messages Messages to be displayed
     */
    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages) {
        if (messages == null) {
            return;
        }

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
    @Override
    public void sendMessage(CommandSender[] recipients, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        for (CommandSender recipient : recipients) {
            sendMessage(recipient, message);
        }
    }

    /**
     * Sends multiple recipients, multiple messages
     *
     * @param recipients Senders to receive messages
     * @param messages Messages to be displayed
     */
    @Override
    public void sendMessage(CommandSender[] recipients, @Nullable String... messages) {
        if (messages == null) {
            return;
        }

        for (CommandSender recipient : recipients) {
            for (String message : messages) {
                sendMessage(recipient, message);
            }
        }
    }

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    @Override
    public void broadcastMessage(@NotNull String... messages) {
        if (messages == null) {
            return;
        }

        for (String message : messages) {
            broadcastMessage(message);
        }
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    @Override
    public void sendActionBarMessage(@NotNull Player[] players, @Nullable String message) {
        if (message == null || message.isBlank()) {
            return;
        }

        for (Player player : players) {
            sendActionBarMessage(player, message);
        }
    }

    /**
     * Sends a player a TITLE message
     *
     * @param player Players to receive this action bar message
     * @param title Title to be displayed
     */
    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title) {
        sendTitle(player, title, null);
    }

    /**
     * Sends a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        sendTitle(player, title, subtitle, 10, 20);
    }

    /**
     * Sends a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(player, title, subtitle, fadeIn, 70, fadeOut);
    }

    /**
     * Sends multiple players a TITLE message
     *
     * @param players Players to receive this title message
     * @param title Title to be displayed
     */
    @Override
    public void sendTitle(@NotNull Player[] players, @Nullable String title) {
        sendTitle(players, title, null);
    }

    /**
     * Sends multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    @Override
    public void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle) {
        sendTitle(players, title, subtitle, 10, 20);
    }

    /**
     * Sends multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    @Override
    public void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(players, title, subtitle, fadeIn, 70, fadeOut);
    }

    /**
     * Sends multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param stay Duration for title to appear
     * @param fadeOut Duration for title to fade out
     */
    @Override
    public void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        if ((title == null || title.isBlank()) && (subtitle == null || subtitle.isBlank())) {
            return;
        }

        for (Player player : players) {
            sendTitle(player, title, subtitle, fadeIn, stay, fadeOut);
        }
    }
}
