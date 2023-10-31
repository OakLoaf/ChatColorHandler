package me.dave.chatcolorhandler.messengers;

import me.dave.chatcolorhandler.ChatColorHandler;
import me.dave.chatcolorhandler.parsers.custom.HexParser;
import me.dave.chatcolorhandler.parsers.custom.LegacyCharParser;
import me.dave.chatcolorhandler.parsers.custom.PlaceholderAPIParser;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.kyori.adventure.title.TitlePart;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;
import java.util.List;

public class MiniMessageMessenger extends AbstractMessenger {
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Override
    public void sendMessage(@NotNull CommandSender recipient, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) recipient);
        
        String legacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(message, (recipient instanceof Player player ? player : null), List.of(LegacyCharParser.class, PlaceholderAPIParser.class)));
        Component parsed = miniMessage.deserialize(legacyParsed);

        audience.sendMessage(parsed);
    }

    @Override
    public void broadcastMessage(@Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) Bukkit.getServer());
        String legacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(message, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)));
        audience.sendMessage(miniMessage.deserialize(legacyParsed));
    }

    @Override
    public void sendActionBarMessage(@NotNull Player player, @Nullable String message) {
        if (message == null || message.isBlank()) return;

        Audience audience = Audience.audience((Audience) player);
        String legacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(message, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)));
        audience.sendActionBar(miniMessage.deserialize(legacyParsed));
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title) {
        if (title == null || title.isBlank()) return;

        sendTitle(player, title, null, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle) {
        sendTitle(player, title, subtitle, 10, 70, 20);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int fadeOut) {
        sendTitle(player, title, subtitle, fadeIn, 70, fadeOut);
    }

    @Override
    public void sendTitle(@NotNull Player player, @Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut) {
        Audience audience = Audience.audience((Audience) player);

        Title.Times times = Title.Times.times(Duration.ofMillis(fadeIn * 50L), Duration.ofMillis(stay * 50L), Duration.ofMillis(fadeOut * 50L));
        String subtitleLegacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(subtitle, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)));
        String titleLegacyParsed = legacyParser(ChatColorHandler.translateAlternateColorCodes(title, player, List.of(LegacyCharParser.class, PlaceholderAPIParser.class)));

        audience.sendTitlePart(TitlePart.TIMES, times);
        audience.sendTitlePart(TitlePart.SUBTITLE, miniMessage.deserialize(subtitleLegacyParsed));
        audience.sendTitlePart(TitlePart.TITLE, miniMessage.deserialize(titleLegacyParsed));
    }

    private String legacyParser(String string) {
        string = string.replace('§', '&');
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
