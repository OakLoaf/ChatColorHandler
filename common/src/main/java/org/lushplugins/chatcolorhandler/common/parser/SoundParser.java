package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoundParser implements Parser {
    public static final SoundParser INSTANCE = new SoundParser();
    private static final Pattern SOUND_PATTERN = Pattern.compile("<sound:([A-Za-z0-9._-]+)(?::([0-9.]+))?(?::([0-9.]+))?>");
    private static final float DEFAULT_VOLUME = 1.0f;
    private static final float DEFAULT_PITCH = 1.0f;

    @Override
    public ParserType getType() {
        return ParserType.SOUND;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        if (player == null || !string.contains("<sound:")) {
            return string;
        }

        Matcher soundMatcher = SOUND_PATTERN.matcher(string);
        if (!soundMatcher.lookingAt()) {
            return string;
        }

        while (soundMatcher.find()) {
            try {
                String sound = soundMatcher.group(1).toLowerCase();
                float volume = soundMatcher.group(2) != null ? Float.parseFloat(soundMatcher.group(2)) : DEFAULT_VOLUME;
                float pitch = soundMatcher.group(3) != null ? Float.parseFloat(soundMatcher.group(3)) : DEFAULT_PITCH;

                Location playerLoc = player.getLocation();
                player.playSound(playerLoc, sound, volume, pitch);
            } catch (IllegalArgumentException ignored) {}
        }

        return soundMatcher.reset().replaceAll("");
    }
}