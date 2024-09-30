package org.lushplugins.chatcolorhandler.parsers;

import org.lushplugins.chatcolorhandler.ChatColorHandler;

import java.util.List;

public class ParserTypes {
    public static final String COLOR = "color";
    public static final String INTERACTION = "interaction";
    public static final String PLACEHOLDER = "placeholder";
    public static final String TEXT_FORMATTING = "text-formatting";

    /**
     * @return A list of color and decoration based parsers
     */
    public static List<Parser> color() {
        return ChatColorHandler.parsers().ofType(COLOR);
    }

    /**
     * @return A list of interaction based parsers, this includes hover, click and nbt elements
     */
    public static List<Parser> interact() {
        return ChatColorHandler.parsers().ofType(INTERACTION);
    }

    /**
     * @return A list of placeholder based parsers
     */
    public static List<Parser> placeholder() {
        return ChatColorHandler.parsers().ofType(PLACEHOLDER);
    }

    /**
     * @return A list of text formatting based parsers, this includes font and newlines
     */
    public static List<Parser> textFormatting() {
        return ChatColorHandler.parsers().ofType(TEXT_FORMATTING);
    }


    /**
     * @return A list of all parsers
     */
    public static List<Parser> all() {
        return ChatColorHandler.parsers().getRegisteredParsers();
    }
}
