package org.lushplugins.chatcolorhandler;

import org.bukkit.Bukkit;

public class ChatColorHandlerDebugger {
    private static boolean debug = false;

    protected static boolean debug() {
        return debug;
    }

    public static void debug(boolean debug) {
        ChatColorHandlerDebugger.debug = debug;
    }

    protected static void debugLog(String log) {
        if (ChatColorHandlerDebugger.debug()) {
            Bukkit.getLogger().info("[ChatColorHandler] " + log);
        }
    }
}
