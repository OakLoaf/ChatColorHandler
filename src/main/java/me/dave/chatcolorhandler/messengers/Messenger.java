package me.dave.chatcolorhandler.messengers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Messenger {

    /**
     * Sends a recipient a message
     *
     * @param recipient Sender to receive this message
     * @param message Message to be displayed
     */
    void sendMessage(@NotNull CommandSender recipient, @Nullable String message);

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages);

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    void sendMessage(CommandSender[] recipients, @Nullable String message);

    /**
     * Sends multiple recipients, multiple messages
     *
     * @param recipients Senders to receive this message
     * @param messages Messages to be displayed
     */
    void sendMessage(CommandSender[] recipients, @Nullable String... messages);

    /**
     * Sends all online players a message
     *
     * @param message Message to be displayed
     */
    void broadcastMessage(@Nullable String message);

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    void broadcastMessage(@NotNull String... messages);

    /**
     * Sends a player an ACTION_BAR message
     *
     * @param player Player to receive this action bar message
     * @param message Message to be displayed
     */
    void sendActionBarMessage(@NotNull Player player, @Nullable String message);

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    void sendActionBarMessage(@NotNull Player[] players, @Nullable String message);

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     */
    void sendTitle(@NotNull Player player, @Nullable String title);

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle);

    /**
     * Send a player a TITLE message
     *
     * @param player Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut);

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
    void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     */
    void sendTitle(@NotNull Player[] players, @Nullable String title);

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     */
    void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle);

    /**
     * Send multiple players a TITLE message
     *
     * @param players Player to receive this title
     * @param title Title to be displayed
     * @param subtitle Subtitle to be displayed
     * @param fadeIn Duration for title to fade in
     * @param fadeOut Duration for title to fade out
     */
    void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut);

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
    void sendTitle(@NotNull Player[] players, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut);
}
