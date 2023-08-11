package me.dave.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class MiniMessageParser implements Parser {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public String parseString(String string) {
        string = string.replace('§', '&');
        return LegacyComponentSerializer.builder().hexColors().build().serialize(miniMessage.deserialize(string));
    }

    @Override
    public String parseString(String string, Player player) {
        return parseString(string);
    }
}
