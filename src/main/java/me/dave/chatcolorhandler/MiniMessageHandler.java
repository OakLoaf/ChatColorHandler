package me.dave.chatcolorhandler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class MiniMessageHandler {
    private final MiniMessage miniMessage;

    public MiniMessageHandler() {
        try {
            miniMessage = MiniMessage.miniMessage();
        } catch (NoClassDefFoundError err) {
            throw new NoClassDefFoundError(err.getMessage());
        }

    }

    public Component deserialize(String string) {
        // Initial character replacements as MiniMessage crashes with '
        string = string.replaceAll("§", "&");

        // Parse message through MiniMessage
        return miniMessage.deserialize(string);
    }
}
