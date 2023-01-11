package me.dave.chatcolorhandler;

import de.themoep.minedown.MineDown;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ChatColorHandler {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * Sends this sender a message
     *
     * @param player  Player to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(@NotNull Player player, @NotNull String message) {
        player.spigot().sendMessage(getTranslatedComponent(message));
    }

    /**
     * Sends this sender multiple messages
     *
     * @param player   Player to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(@NotNull Player player, @NotNull String... messages) {
        sendMessage(player, String.join(" ", messages));
    }

    /**
     * Sends this sender multiple messages
     *
     * @param players Players to receive this message
     * @param message Message to be displayed
     */
    public static void sendMessage(Player[] players, @NotNull String message) {
        for (Player player : players) {
            sendMessage(player, message);
        }
    }

    /**
     * Sends this sender multiple messages
     *
     * @param players  Players to receive this message
     * @param messages Messages to be displayed
     */
    public static void sendMessage(Player[] players, @NotNull String... messages) {
        String message = String.join(" ", messages);
        for (Player player : players) {
            sendMessage(player, message);
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
