package me.dave.chatcolorhandler.legacySerializer;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegacyTranslator {
    private static final Pattern fullStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#]+|)>([^<>]+)</\\1>");
    private static final Pattern halfStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#]+|)>([^<>]+)");

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
                    int phase = 0;
                    for (String setting : settings) {
                        if (setting.matches("#[a-fA-F0-9]{6}")) gradientHexList.add(setting);
                    }
                    if (settings[settings.length - 1].matches("[-][0-1]]")) phase = Integer.parseInt(settings[settings.length - 1]);
                    replacement = applyGradient(content, gradientHexList, phase);
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

                case "gradient" -> {
                    List<String> gradientHexList = new ArrayList<>();
                    int phase = 0;
                    for (String setting : settings) {
                        if (setting.matches("#[a-fA-F0-9]{6}")) gradientHexList.add(setting);
                    }
                    if (settings[settings.length - 1].matches("[-][0-1]]")) phase = Integer.parseInt(settings[settings.length - 1]);
                    replacement = applyGradient(content, gradientHexList, phase);
                }

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

    private static String applyGradient(String string, List<String> hexColours, int phase) {
        String[] charArr = string.split("(?!^)");
        int length = string.length();
        double stepSize = (length - 1) / (hexColours.size() - 1.0);

        int currChar = 0;
        for (int i = 0; i < hexColours.size() - 1; i++) {
            String fromHex = hexColours.get(i);
            String toHex = hexColours.get(i + 1);

            Color fromColor = hex2Rgb(fromHex);
            Color toColor = hex2Rgb(toHex);

            double redStep = (toColor.getRed() - fromColor.getRed()) / stepSize;
            double greenStep = (toColor.getGreen() - fromColor.getGreen()) / stepSize;
            double blueStep = (toColor.getBlue() - fromColor.getBlue()) / stepSize;

            double currRed = fromColor.getRed() - redStep;
            double currGreen = fromColor.getGreen() - greenStep;
            double currBlue = fromColor.getBlue() - blueStep;
            for (int j = 0; j < stepSize; j++) {
                if (currChar > length - 1) break;
                currRed = currRed + redStep;
                currGreen = currGreen + greenStep;
                currBlue = currBlue + blueStep;
                charArr[currChar] = "&" + rgb2Hex(new Color((int) Math.round(currRed), (int) Math.round(currGreen), (int) Math.round(currBlue))) + charArr[currChar];
                currChar++;
            }
        }

        return String.join("", charArr);
    }

    private static Color hex2Rgb(String colorStr) {
        return new Color(
            Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
            Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
            Integer.valueOf( colorStr.substring( 5, 7 ), 16 )
        );
    }

    private static String rgb2Hex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }
}
