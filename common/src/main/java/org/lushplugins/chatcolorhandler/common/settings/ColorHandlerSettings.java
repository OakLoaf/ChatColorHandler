package org.lushplugins.chatcolorhandler.common.settings;

import org.lushplugins.chatcolorhandler.common.parser.Parser;
import org.lushplugins.chatcolorhandler.common.parser.Parsers;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class ColorHandlerSettings {
    private UnaryOperator<Parsers> defaultParsers = Parsers::all;

    public UnaryOperator<Parsers> defaultParsers() {
        return defaultParsers;
    }

    /**
     * Set the default parsers operator to use, when not overridden all registered parsers are used
     * @param defaultParsers The default parsers to use when none are specified
     */
    public ColorHandlerSettings defaultParsers(UnaryOperator<Parsers> defaultParsers) {
        this.defaultParsers = defaultParsers;
        return this;
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param defaultParsers The default parsers to use when none are specified
     */
    public ColorHandlerSettings defaultParsers(List<Parser> defaultParsers) {
        return defaultParsers((parsers) -> parsers.parsers(defaultParsers));
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param parsers The default parsers to use when none are specified
     */
    public ColorHandlerSettings defaultParsers(Parser... parsers) {
        return defaultParsers(Arrays.asList(parsers));
    }
}
