package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParticleParser implements Parser {

    private static final Pattern PARTICLE_PATTERN = Pattern.compile("<particle:([a-zA-Z_]+)(?::([~\\d.-]+, [~\\d.-]+, [~\\d.-]+))?(?::(\\d+))?>");

    public  static final ParticleParser INSTANCE = new ParticleParser();

    private ParticleParser() {}

    @Override
    public String getType() {
        return ParserTypes.PARTICLE;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return string;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull Parser.OutputType outputType, Player player) {
        if (!string.contains("<particle:")) {
            return string;
        }

        Matcher particleMatcher = PARTICLE_PATTERN.matcher(string);

        if (!particleMatcher.find()) {
            return string;
        }

        particleMatcher.reset();

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
            } catch (IllegalArgumentException ignored) {
                // ignore IllegalArgumentException
            }
        }

        return particleMatcher.reset().replaceAll("");
    }

    private Location parseLocation(String locationStr, Player player) {
        Location playerLoc = player.getLocation();
        String[] parts = locationStr.split(",\\s*", 3);

        if (parts.length != 3) {
            return playerLoc;
        }

        double x = parseCoordinate(parts[0], playerLoc.getX());
        double y = parseCoordinate(parts[1], playerLoc.getY());
        double z = parseCoordinate(parts[2], playerLoc.getZ());
        return new Location(player.getWorld(), x, y, z);
    }

    private double parseCoordinate(String coordinate, double playerCoord) {
        if (coordinate.isEmpty()) {
            return playerCoord;
        }

        if (coordinate.charAt(0) == '~') {
            return coordinate.length() == 1 ?
                    playerCoord :
                    playerCoord + Double.parseDouble(coordinate.substring(1));
        }

        return Double.parseDouble(coordinate);
    }
}
