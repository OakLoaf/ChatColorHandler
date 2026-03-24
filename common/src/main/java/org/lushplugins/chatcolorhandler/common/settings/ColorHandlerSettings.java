package org.lushplugins.chatcolorhandler.common.settings;

import org.lushplugins.chatcolorhandler.common.parser.Parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ColorHandlerSettings {
    private List<Parser> defaultParsers = Collections.emptyList();

    public List<Parser> defaultParsers() {
        return defaultParsers;
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param parsers The default parsers to use when none are specified
     */
    public ColorHandlerSettings defaultParsers(List<Parser> parsers) {
        this.defaultParsers = parsers;
        return this;
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param parsers The default parsers to use when none are specified
     */
    public ColorHandlerSettings defaultParsers(Parser... parsers) {
        return defaultParsers(Arrays.asList(parsers));
    }
}
