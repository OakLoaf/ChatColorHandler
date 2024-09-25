package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

public class LegacySpigotParser implements Parser {
    public static final LegacySpigotParser INSTANCE = new LegacySpigotParser();

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> ChatColor.translateAlternateColorCodes('&', string);
            case MINI_MESSAGE -> string
                // Legacy Ampersand
                .replace("§", "&")
                // Colours
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
                // Decorations
                .replace("&m", "<strikethrough>")
                .replace("&k", "<obfuscated>")
                .replace("&n", "<underlined>")
                .replace("&o", "<italic>")
                .replace("&l", "<bold>")
                .replace("&r", "<reset>");
        };
    }
}
