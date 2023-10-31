package me.dave.chatcolorhandler.messengers;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMessenger implements Messenger {

    /**
     * Sends a recipient multiple messages
     *
     * @param recipient Sender to receive message
     * @param messages Messages to be displayed
     */
    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String... messages) {
        sendMessage(recipient, String.join(" ", messages));
    }

    /**
     * Sends multiple recipients a message
     *
     * @param recipients Senders to receive message
     * @param message Message to be displayed
     */
    @Override
    public void sendMessage(CommandSender[] recipients, @Nullable String message) {
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
    @Override
    public void sendMessage(CommandSender[] recipients, @Nullable String... messages) {
        if (messages == null) return;

        String message = String.join(" ", messages);
        for (CommandSender recipient : recipients) {
            sendMessage(recipient, message);
        }
    }

    /**
     * Sends all online players multiple messages
     *
     * @param messages Messages to be displayed
     */
    @Override
    public void broadcastMessage(@NotNull String... messages) {
        if (messages == null) return;
        broadcastMessage(String.join(" ", messages));
    }

    /**
     * Sends multiple players an ACTION_BAR message
     *
     * @param players Players to receive this action bar message
     * @param message Message to be displayed
     */
    @Override
    public void sendActionBarMessage(@NotNull Player[] players, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        for (Player player : players) {
            sendActionBarMessage(player, message);
        }
    }
}
