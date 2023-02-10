package me.dave.chatcolorhandler.legacySerializer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LegacyTranslator {
    private static final Pattern entryPattern = Pattern.compile("<.+>");
    private static final Pattern hexPattern = Pattern.compile("<#[a-fA-F0-9]{6}>");

    public static String translateFromMiniMessage(String miniMessage) {
        String legacy = miniMessage;

        // Translate MiniMessage entry statements to legacy
        Matcher entryMatch = entryPattern.matcher(legacy);
        while (entryMatch.find()) {
            String contents = legacy.substring(entryMatch.start() + 1, entryMatch.end() - 1);
            String[] contentsArr = contents.split(":");

            String replacement = "<" + contents + ">";
            switch(contentsArr[0]) {
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
                    Pattern gradClosePattern = Pattern.compile("</gradient>");
                    String gradientSnippet = legacy.substring(entryMatch.start());
                    Matcher gradCloseMatch = gradClosePattern.matcher(gradientSnippet);
                    if (!gradCloseMatch.find()) break;
                    gradientSnippet = gradientSnippet.substring(0, gradCloseMatch.end());

                    replacement = "HEX-" + gradientSnippet + "-HEX";
                }
            }

            legacy = legacy.replace("<" + contents + ">", replacement);
            entryMatch = entryPattern.matcher(legacy);
        }

        // Translate MiniMessage hex colours to legacy
        Matcher hexMatch = hexPattern.matcher(legacy);
        while (hexMatch.find()) {
            String color = legacy.substring(hexMatch.start() + 1, hexMatch.end() - 1);
            legacy = legacy.replace("<#" + color + ">", "&#" + color);
            hexMatch = hexPattern.matcher(legacy);
        }

        // Remove closing statements
        legacy = legacy.replaceAll("</.+>", "");

        return legacy;
    }
}
