package me.dave.chatcolorhandler;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class PlaceholderAPIParser {

    public String parseString(Player player, String string) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }

    public String parseStrings(Player player, String... strings) {
        return PlaceholderAPI.setPlaceholders(player, String.join(" ", strings));
    }
}
