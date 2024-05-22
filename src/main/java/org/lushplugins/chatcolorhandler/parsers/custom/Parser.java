package org.lushplugins.chatcolorhandler.parsers.custom;

import org.bukkit.entity.Player;

public interface Parser {
    String parseString(String string);
    String parseString(String string, Player player);
}
