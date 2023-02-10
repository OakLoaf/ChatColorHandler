package me.dave.chatcolorhandler.legacySerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegacyTranslator {
//    private static final Pattern entryPattern = Pattern.compile("<[a-zA-Z0-9:#]+>");
    private static final Pattern fullStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#]+|)>([^<>]+)</\1>");
    private static final Pattern hexPattern = Pattern.compile("<#[a-fA-F0-9]{6}>");

    public static String translateFromMiniMessage(String miniMessage) {
        String legacy = miniMessage;

        // Translate full MiniMessage entry statements to legacy
        Matcher fullStatementMatch = fullStatementPattern.matcher(legacy);
        while (fullStatementMatch.find()) {
            String fullMatch = fullStatementMatch.group();
            String type = fullStatementMatch.group(1);
            String[] settings = fullStatementMatch.group(2).split(":");
            String content = fullStatementMatch.group(3);

            String replacement = fullMatch.replaceAll("<", "@L3£G").replaceAll(">", "@R£G");
            switch(type) {
                case "black" -> replacement = "&0";
                case "dark_blue" -> replacement = "&1";
                case "dark_green" -> replacement = "&2";
                case "dark_aqua" -> replacement = "&3";
                case "dark_red" -> replacement = "&4";
                case "dark_purple" -> replacement = "&5";
                case "gold" -> replacement = "&6";
                case "gray" -> replacement = "&7";
                case "dark_gray" -> replacement = "&8";
                case "blue" -> replacement = "&9";
                case "green" -> replacement = "&a";
                case "aqua" -> replacement = "&b";
                case "red" -> replacement = "&c";
                case "light_purple" -> replacement = "&d";
                case "yellow" -> replacement = "&e";
                case "white" -> replacement = "&f";

                case "strikethrough" -> replacement = "&m";
                case "st" -> replacement = "&m";

                case "obfuscated" -> replacement = "&k";
                case "obf" -> replacement = "&k";

                case "underlined" -> replacement = "&n";
                case "u" -> replacement = "&n";

                case "italic" -> replacement = "&o";
                case "em" -> replacement = "&o";
                case "i" -> replacement = "&o";

                case "bold" -> replacement = "&l";
                case "b" -> replacement = "&l";

                case "reset" -> replacement = "&r";

                case "gradient" -> {
                    String startingHex = "#ffffff";
                    String endingHex = "#ffffff";
                    if (settings.length > 0) startingHex = settings[1];
                    if (settings.length > 1) endingHex = settings[settings.length - 1];
                    replacement = startingHex + "-" + content + "-" + endingHex;
                }
            }

            legacy = legacy.replace(fullMatch, replacement);
            fullStatementMatch = fullStatementPattern.matcher(legacy);
        }

        // Translate MiniMessage hex colours to legacy
        Matcher hexMatch = hexPattern.matcher(legacy);
        while (hexMatch.find()) {
            String color = legacy.substring(hexMatch.start() + 1, hexMatch.end() - 1);
            legacy = legacy.replace("<#" + color + ">", "&#" + color);
            hexMatch = hexPattern.matcher(legacy);
        }

        // Remove closing statements
//        legacy = legacy.replaceAll("</.+>", "");

        // Return '<' and '>' characters
        legacy = legacy.replaceAll("@L£G", "<").replaceAll("@R£G", ">");

        return legacy;
    }
}
