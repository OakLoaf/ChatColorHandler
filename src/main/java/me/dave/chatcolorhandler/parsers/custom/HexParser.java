package me.dave.chatcolorhandler.parsers.custom;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HexParser implements Parser {
    private static final Pattern hexPattern = Pattern.compile("&#[a-fA-F0-9]{6}");

    @Override
    public String parseString(String string) {
        // Parse message through Default Hex in format "&#rrggbb"
        Matcher match = hexPattern.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, ChatColor.of(color).toString());
            match = hexPattern.matcher(string);
        }

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    @Override
    public String parseString(String string, Player player) {
        return parseString(string);
    }

    public static String parseToMiniMessage(String string) {
        // Parse message through Default Hex in format "&#rrggbb"
        Matcher match = hexPattern.matcher(string);
        while (match.find()) {
            String color = string.substring(match.start() + 1, match.end());
            string = string.replace("&" + color, "<" + color + ">");
            match = hexPattern.matcher(string);
        }
        return string;
    }
}
