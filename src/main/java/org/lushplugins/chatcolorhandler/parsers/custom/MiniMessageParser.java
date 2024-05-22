package org.lushplugins.chatcolorhandler.parsers.custom;

import org.lushplugins.chatcolorhandler.resolvers.Resolvers;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class MiniMessageParser implements Parser {
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public String parseString(String string) {
        return parseString(string, null);
    }

    @Override
    public String parseString(String string, Player player) {
        string = string.replace('§', '&');

        TagResolver resolver = Resolvers.getResolver(player != null ? (Audience) player : null, null);
        return LegacyComponentSerializer.builder().hexColors().build().serialize(miniMessage.deserialize(string, resolver));
    }

    public static String legacyToMiniMessage(String string, boolean parseHex) {
        string = string.replace('§', '&');

        if (parseHex) {
            string = HexParser.parseToMiniMessage(string);
        }

        return string
            .replace("&0", "<reset><black>")
            .replace("&1", "<reset><dark_blue>")
            .replace("&2", "<reset><dark_green>")
            .replace("&3", "<reset><dark_aqua>")
            .replace("&4", "<reset><dark_red>")
            .replace("&5", "<reset><dark_purple>")
            .replace("&6", "<reset><gold>")
            .replace("&7", "<reset><grey>")
            .replace("&8", "<reset><dark_grey>")
            .replace("&9", "<reset><blue>")
            .replace("&a", "<reset><green>")
            .replace("&b", "<reset><aqua>")
            .replace("&c", "<reset><red>")
            .replace("&d", "<reset><light_purple>")
            .replace("&e", "<reset><yellow>")
            .replace("&f", "<reset><white>")

            .replace("&m", "<strikethrough>")
            .replace("&k", "<obfuscated>")
            .replace("&n", "<underlined>")
            .replace("&o", "<italic>")
            .replace("&l", "<bold>")
            .replace("&r", "<reset>");
    }
}
