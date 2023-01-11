package me.dave.chatcolorhandler;

import de.themoep.minedown.MineDown;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ChatColorHandler {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * Sends this sender a message
     *
     * @param sender Player to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull CommandSender sender, @NotNull String message) {
        sender.spigot().sendMessage(getTranslatedComponent(message));
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
     * Converts message to a BaseComponent[]
     *
     * @param message Messages to be displayed
     */
    private static BaseComponent[] getTranslatedComponent(String message) {
        String mmParsed = LegacyComponentSerializer.builder().build().serialize(miniMessage.deserialize(message));
        return new MineDown(mmParsed).toComponent();
    }
}
