package me.dave.chatcolorhandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniMessageTranslator {
    private static final Pattern fullStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#-\\.]+|)>([^<>]+)</\\1>");
    private static final Pattern halfStatementPattern = Pattern.compile("<([a-zA-Z0-9]+)([a-zA-Z0-9:#-\\.]+|)>([^<>]+)");

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
                case "gray","grey" -> replacement = "&7" + content;
                case "dark_gray","dark_grey" -> replacement = "&8" + content;
                case "blue" -> replacement = "&9" + content;
                case "green" -> replacement = "&a" + content;
                case "aqua" -> replacement = "&b" + content;
                case "red" -> replacement = "&c" + content;
                case "light_purple" -> replacement = "&d" + content;
                case "yellow" -> replacement = "&e" + content;
                case "white" -> replacement = "&f" + content;

                case "strikethrough","st" -> replacement = "&m" + content;
                case "obfuscated","obf" -> replacement = "&k" + content;
                case "underlined","u" -> replacement = "&n" + content;
                case "italic","em","i" -> replacement = "&o" + content;
                case "bold","b" -> replacement = "&l" + content;
                case "reset" -> replacement = "&r" + content;

                case "gradient" -> {
                    List<String> gradientHexList = new ArrayList<>();
                    double phase = 0;
                    for (String setting : settings) {
                        if (setting.matches("#[a-fA-F0-9]{6}")) gradientHexList.add(setting);
                    }
                    if (isStringDoubleInRange(settings[settings.length - 1], -1.0, 1.0)) phase = Double.parseDouble(settings[settings.length - 1]);
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

            String replacement = fullMatch.replaceAll("<", "@L£G").replaceAll(">", "@R£G");
            switch(type) {
                case "black" -> replacement = "&0";
                case "dark_blue" -> replacement = "&1";
                case "dark_green" -> replacement = "&2";
                case "dark_aqua" -> replacement = "&3";
                case "dark_red" -> replacement = "&4";
                case "dark_purple" -> replacement = "&5";
                case "gold" -> replacement = "&6";
                case "gray","grey" -> replacement = "&7";
                case "dark_gray","dark_grey" -> replacement = "&8";
                case "blue" -> replacement = "&9";
                case "green" -> replacement = "&a";
                case "aqua" -> replacement = "&b";
                case "red" -> replacement = "&c";
                case "light_purple" -> replacement = "&d";
                case "yellow" -> replacement = "&e";
                case "white" -> replacement = "&f";

                case "strikethrough","st" -> replacement = "&m";
                case "obfuscated","obf" -> replacement = "&k";
                case "underlined","u" -> replacement = "&n";
                case "italic","em","i" -> replacement = "&o";
                case "bold","b" -> replacement = "&l";
                case "reset" -> replacement = "&r";

                case "gradient" -> {
                    List<String> gradientHexList = new ArrayList<>();
                    double phase = 0.0;
                    for (String setting : settings) {
                        if (setting.matches("#[a-fA-F0-9]{6}")) gradientHexList.add(setting);
                    }
                    if (isStringDoubleInRange(settings[settings.length - 1], -1.0, 1.0)) phase = Double.parseDouble(settings[settings.length - 1]);
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

        // Return '<' and '>' characters
        legacy = legacy.replaceAll("@L£G", "<").replaceAll("@R£G", ">");

        return legacy;
    }

    private static String applyGradient(String string, List<String> hexColours, double phase) {
        String[] charArr = string.split("(?!^)");
        int length = string.length();
        double stepSize = (length - 1) / (hexColours.size() - 1.0);

        int currChar = (int) -Math.round((length / 2.0) * phase);
        if (currChar < 0) currChar = charArr.length + currChar;
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
                currRed = currRed + redStep;
                currGreen = currGreen + greenStep;
                currBlue = currBlue + blueStep;
                charArr[currChar % charArr.length] = "&" + rgb2Hex(new Color((int) Math.round(currRed), (int) Math.round(currGreen), (int) Math.round(currBlue))) + charArr[currChar % charArr.length];
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

    private static boolean isStringDoubleInRange(String string, double min, double max) {
        double num;
        try {
            num = Double.parseDouble(string);
        } catch (NumberFormatException err) {
            return false;
        }

        return !(num > max) && !(num < min);
    }
}
