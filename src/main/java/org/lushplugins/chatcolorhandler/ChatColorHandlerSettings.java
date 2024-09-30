package org.lushplugins.chatcolorhandler;

import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ChatColorHandlerSettings {
    private List<Parser> defaultParsers = ParserTypes.all();
    private boolean debug = false;

    public List<Parser> defaultParsers() {
        return defaultParsers;
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param parsers The default parsers to use when none are specified
     */
    public ChatColorHandlerSettings defaultParsers(List<Parser> parsers) {
        this.defaultParsers = parsers;
        return this;
    }

    public boolean debug() {
        return debug;
    }

    /**
     * @param debug Whether to enable debug logs
     */
    public ChatColorHandlerSettings debug(boolean debug) {
        this.debug = debug;
        return this;
    }
}
