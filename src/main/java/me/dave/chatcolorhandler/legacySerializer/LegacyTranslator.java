package me.dave.chatcolorhandler.legacySerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegacyTranslator {
    private static final Pattern fullStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#]+|)>([^<>]+)</\\1>");
    private static final Pattern halfStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#]+|)>");

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
                case "black" -> replacement = "&0" + content;
                case "dark_blue" -> replacement = "&1" + content;
                case "dark_green" -> replacement = "&2" + content;
                case "dark_aqua" -> replacement = "&3" + content;
                case "dark_red" -> replacement = "&4" + content;
                case "dark_purple" -> replacement = "&5" + content;
                case "gold" -> replacement = "&6" + content;
                case "gray" -> replacement = "&7" + content;
                case "dark_gray" -> replacement = "&8" + content;
                case "blue" -> replacement = "&9" + content;
                case "green" -> replacement = "&a" + content;
                case "aqua" -> replacement = "&b" + content;
                case "red" -> replacement = "&c" + content;
                case "light_purple" -> replacement = "&d" + content;
                case "yellow" -> replacement = "&e" + content;
                case "white" -> replacement = "&f" + content;

                case "strikethrough" -> replacement = "&m" + content;
                case "st" -> replacement = "&m" + content;

                case "obfuscated" -> replacement = "&k" + content;
                case "obf" -> replacement = "&k" + content;

                case "underlined" -> replacement = "&n" + content;
                case "u" -> replacement = "&n" + content;

                case "italic" -> replacement = "&o" + content;
                case "em" -> replacement = "&o" + content;
                case "i" -> replacement = "&o" + content;

                case "bold" -> replacement = "&l" + content;
                case "b" -> replacement = "&l" + content;

                case "reset" -> replacement = "&r" + content;

                case "gradient" -> {
                    List<String> gradientHexList = new ArrayList<>();
                    for (String setting : settings) {
                        if (setting.matches("#[a-fA-F0-9]{6}")) gradientHexList.add(setting);
                    }
                    replacement = gradientHexList.get(0) + "-" + content + "-" + gradientHexList.get(gradientHexList.size() - 1);
                }

                default -> {
                    // Translate MiniMessage hex colours to legacy
                    if (type.matches("#[a-fA-F0-9]{6}")) replacement = "&" + type;
                }
            }

            legacy = legacy.replace(fullMatch, replacement);
            fullStatementMatch = fullStatementPattern.matcher(legacy);
        }

        // Translate full MiniMessage entry statements to legacy
        Matcher halfStatementMatch = halfStatementPattern.matcher(legacy);
        while (halfStatementMatch.find()) {
            String fullMatch = halfStatementMatch.group();
            String type = halfStatementMatch.group(1);
            String[] settings = halfStatementMatch.group(2).split(":");
            String content = halfStatementMatch.group(3);

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

                default -> {
                    // Translate MiniMessage hex colours to legacy
                    if (type.matches("#[a-fA-F0-9]{6}")) replacement = "&" + type;
                }
            }

            legacy = legacy.replace(fullMatch, replacement);
            halfStatementMatch = halfStatementPattern.matcher(legacy);
        }

        // Remove closing statements
//        legacy = legacy.replaceAll("</.+>", "");

        // Return '<' and '>' characters
        legacy = legacy.replaceAll("@L£G", "<").replaceAll("@R£G", ">");

        return legacy;
    }
}
