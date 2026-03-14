package org.lushplugins.chatcolorhandler.common.parser;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticleParser implements Parser {
    public  static final ParticleParser INSTANCE = new ParticleParser();
    private static final Pattern PARTICLE_PATTERN = Pattern.compile("<particle:([a-zA-Z_]+)(?::([~\\d.-]+, [~\\d.-]+, [~\\d.-]+))?(?::(\\d+))?>");

    @Override
    public ParserType getType() {
        return ParserType.PARTICLE;
    }

    @Override
    public String parseString(String string, @Nullable Player player) {
        if (player == null || !string.contains("<particle:")) {
            return string;
        }

        Matcher particleMatcher = PARTICLE_PATTERN.matcher(string);
        if (!particleMatcher.lookingAt()) {
            return string;
        }

        while (particleMatcher.find()) {
            try {
                String particleName = particleMatcher.group(1).toUpperCase();
                Particle particle = Particle.valueOf(particleName);

                String locationStr = particleMatcher.group(2);
                Location location = locationStr != null ?
                        parseLocation(locationStr, player) :
                        player.getLocation();

                int count = particleMatcher.group(3) != null ?
                        Integer.parseInt(particleMatcher.group(3)) : 1;

                player.spawnParticle(particle, location, count);
            } catch (IllegalArgumentException ignored) {}
        }

        return particleMatcher.reset().replaceAll("");
    }

    private Location parseLocation(String locationStr, Player player) {
        Location playerLoc = player.getLocation();
        String[] parts = locationStr.split(",\\s?", 3);

        if (parts.length != 3) {
            return playerLoc;
        }

        double x = parseCoordinate(parts[0], playerLoc.getX());
        double y = parseCoordinate(parts[1], playerLoc.getY());
        double z = parseCoordinate(parts[2], playerLoc.getZ());
        return new Location(player.getWorld(), x, y, z);
    }

    private double parseCoordinate(String coordinate, double def) {
        if (coordinate.isEmpty()) {
            return def;
        }

        if (coordinate.charAt(0) == '~') {
            return coordinate.length() == 1 ? def : def + Double.parseDouble(coordinate.substring(1));
        }

        return Double.parseDouble(coordinate);
    }
}
