package org.lushplugins.chatcolorhandler.common.parser;

import java.util.List;

public enum ParserType {
    /**
     * Color and decoration based parsers
     */
    COLOR("color"),
    /**
     * Sound based parsers
     */
    SOUND("sound"),
    /**
     * Particle based parsers
     */
    PARTICLE("particle"),
    /**
     * Interaction based parsers, like hover, click and nbt elements
     */
    INTERACTION("interaction"),
    /**
     * Placeholder based parsers
     */
    PLACEHOLDER("placeholder"),
    /**
     * Text formatting based parsers, this includes font and newlines
     */
    TEXT_FORMATTING("text-formatting");

    private final String id;

    ParserType(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    public boolean equals(String id) {
        return this.id.equals(id);
    }
}
