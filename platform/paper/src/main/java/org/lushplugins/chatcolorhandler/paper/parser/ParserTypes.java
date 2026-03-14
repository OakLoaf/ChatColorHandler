package org.lushplugins.chatcolorhandler.paper.parser;

import org.lushplugins.chatcolorhandler.common.parser.Parser;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;
import org.lushplugins.chatcolorhandler.paper.PaperColor;

import java.util.List;

import java.util.stream.Stream;

public class ParserTypes {

    private static List<Parser> ofType(ParserType type) {
        return all().stream()
            .filter(parser -> {
                ParserType parserType = parser.getType();
                return parserType != null && parserType.equals(type);
            })
            .toList();
    }

    public static List<Parser> color() {
        return ofType(ParserType.COLOR);
    }

    public static List<Parser> sound() {
        return ofType(ParserType.SOUND);
    }

    public static List<Parser> particle() {
        return ofType(ParserType.PARTICLE);
    }

    public static List<Parser> interaction() {
        return ofType(ParserType.INTERACTION);
    }

    public static List<Parser> placeholder() {
        return ofType(ParserType.PLACEHOLDER);
    }

    public static List<Parser> textFormatting() {
        return ofType(ParserType.TEXT_FORMATTING);
    }

    public static List<Parser> all() {
        return Stream.concat(
            PaperColor.handler().parsers().values().stream(),
            PaperColor.handler().resolvers().values().stream()
        ).toList();
    }
}
