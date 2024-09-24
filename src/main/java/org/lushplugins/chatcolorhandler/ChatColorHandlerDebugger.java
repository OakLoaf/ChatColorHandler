package org.lushplugins.chatcolorhandler;

public class ChatColorHandlerDebugger {
    private static boolean debug = false;

    protected static boolean debug() {
        return debug;
    }

    public static void debug(boolean debug) {
        ChatColorHandlerDebugger.debug = debug;
    }
}
