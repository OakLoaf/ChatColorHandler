package me.dave.chatcolorhandler;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class MiniMessageHandler {
    private final MiniMessage miniMessage;

    public MiniMessageHandler() {
        try {
            miniMessage = MiniMessage.miniMessage();
        } catch (NoClassDefFoundError err) {
            Bukkit.getLogger().severe("Could not enable MiniMessage, make sure you have it's dependency setup correctly: https://docs.adventure.kyori.net/minimessage/api.html");
            throw new NoClassDefFoundError();
        }
    }

    public Component deserialize(String string) {
        // Initial character replacements as MiniMessage crashes with '
        string = string.replaceAll("§", "&");

        // Parse message through MiniMessage
        return miniMessage.deserialize(string);
    }
}
