package me.dave.chatcolorhandler.legacySerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegacyTranslator {
    private static final Pattern hexPattern = Pattern.compile("<#[a-fA-F0-9]{6}>");

    public static String translateFromMiniMessage(String miniMessage) {
        // Translate MiniMessage colours to legacy
        String legacy = miniMessage.replaceAll("<black>", "&0")
            .replaceAll("<dark_blue>", "&1")
            .replaceAll("<dark_green>", "&2")
            .replaceAll("<dark_aqua>", "&3")
            .replaceAll("<dark_red>", "&4")
            .replaceAll("<dark_purple>", "&5")
            .replaceAll("<gold>", "&6")
            .replaceAll("<gray>", "&7")
            .replaceAll("<dark_gray>", "&8")
            .replaceAll("<blue>", "&9")
            .replaceAll("<green>", "&a")
            .replaceAll("<aqua>", "&b")
            .replaceAll("<red>", "&c")
            .replaceAll("<light_purple>", "&d")
            .replaceAll("<yellow>", "&e")
            .replaceAll("<white>", "&f")

            .replaceAll("<strikethrough>", "&m")
            .replaceAll("<obfuscated>", "&k")
            .replaceAll("<italic>", "&o")
            .replaceAll("<bold>", "&l")
            .replaceAll("<reset>", "&r");

        // Translate MiniMessage hex colours to legacy
        Matcher match = hexPattern.matcher(legacy);
        while (match.find()) {
            String color = legacy.substring(match.start() + 1, match.end() - 1);
            legacy = legacy.replace("<#" + color + ">", "&#" + color);
            match = hexPattern.matcher(legacy);
        }

        // Remove closing statements
        legacy = legacy.replaceAll("</.+>", "");

        return legacy;
    }
}
