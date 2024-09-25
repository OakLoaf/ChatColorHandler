package org.lushplugins.chatcolorhandler;

import org.lushplugins.chatcolorhandler.messengers.Messenger;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;

import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ChatColorHandlerSettings {
    private Messenger messenger;
    private List<Parser> defaultParsers = ParserTypes.all();
    private boolean debug = false;

    public Messenger messenger() {
        if (messenger == null) {
            ChatColorHandler.init();
        }

        return messenger;
    }

    /**
     * Adjust the ChatColorHandler messenger to be used. This is handled automatically by ChatColorHandler but can be overridden for custom uses.
     * @param messenger The messenger to use
     */
    public ChatColorHandlerSettings messenger(Messenger messenger) {
        this.messenger = messenger;
        return this;
    }

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
