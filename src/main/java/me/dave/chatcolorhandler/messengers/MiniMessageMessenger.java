package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.parsers.custom.HexParser;
import me.dave.chatcolorhandler.parsers.custom.MiniMessageParser;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MiniMessageMessenger extends AbstractMessenger {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) recipient);
        
        String legacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(message, (recipient instanceof Player player ? player : null), List.of(MiniMessageParser.class, HexParser.class)));

//        List<Component> legacyComponents = legacyParsed.children();
//        for (Component legacyComponent : legacyComponents) {
//            if (legacyComponent instanceof TextComponent legacyTextComponent) {
//                legacyTextComponent.content();
//            }
//        }
//
//        String content = legacyParsed.content();
        Component parsed = miniMessage.deserialize(legacyParsed);

        audience.sendMessage(parsed);
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) Bukkit.getOnlinePlayers());
        TextComponent legacyParsed = LegacyComponentSerializer.legacy('§').deserialize(message);
        audience.sendMessage(miniMessage.deserialize(legacyParsed.content()));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) player);
        TextComponent legacyParsed = LegacyComponentSerializer.legacy('§').deserialize(message);
        audience.sendActionBar(miniMessage.deserialize(legacyParsed.content()));
    }

    private String legacyParser(String string) {
        string = HexParser.parseToMiniMessage(string);

        return string
            .replaceAll("&0", "<reset><black>")
            .replaceAll("&1", "<reset><dark_blue>")
            .replaceAll("&2", "<reset><dark_green>")
            .replaceAll("&3", "<reset><dark_aqua>")
            .replaceAll("&4", "<reset><dark_red>")
            .replaceAll("&5", "<reset><dark_purple>")
            .replaceAll("&6", "<reset><gold>")
            .replaceAll("&7", "<reset><grey>")
            .replaceAll("&8", "<reset><dark_grey>")
            .replaceAll("&9", "<reset><blue>")
            .replaceAll("&a", "<reset><green>")
            .replaceAll("&b", "<reset><aqua>")
            .replaceAll("&c", "<reset><red>")
            .replaceAll("&d", "<reset><light_purple>")
            .replaceAll("&e", "<reset><yellow>")
            .replaceAll("&f", "<reset><white>")

            .replaceAll("&m", "<strikethrough>")
            .replaceAll("&k", "<obfuscated>")
            .replaceAll("&n", "<underlined>")
            .replaceAll("&o", "<italic>")
            .replaceAll("&l", "<bold>")
            .replaceAll("&r", "<reset>");
    }
}
