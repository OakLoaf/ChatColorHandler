package org.lushplugins.chatcolorhandler.common.parser;

import org.jspecify.annotations.NonNull;
import org.lushplugins.chatcolorhandler.common.ColorHandler;

import java.io.Serializable;
import java.util.*;

public class Parsers implements Iterable<Parser> {
    private final ColorHandler<?> instance;
    private final Set<Parser> parsers;

    public Parsers(ColorHandler<?> instance, Set<Parser> parsers) {
        this.instance = instance;
        this.parsers = parsers;
    }

    public Parsers(ColorHandler<?> instance) {
        this(instance, new HashSet<>());
    }

    public Parsers parser(Parser parser) {
        parsers.add(parser);
        return this;
    }

    public Parsers parsers(Collection<Parser> parsers) {
        this.parsers.addAll(parsers);
        return this;
    }

    public Parsers ofType(ParserType type) {
        instance.allParsers().stream()
            .filter(parser -> Objects.equals(parser.getType(), type))
            .forEach(parsers::add);

        return this;
    }

    public Parsers color() {
        return ofType(ParserType.COLOR);
    }

    public Parsers sound() {
        return ofType(ParserType.SOUND);
    }

    public Parsers particle() {
        return ofType(ParserType.PARTICLE);
    }

    public Parsers interaction() {
        return ofType(ParserType.INTERACTION);
    }

    public Parsers placeholder() {
        return ofType(ParserType.PLACEHOLDER);
    }

    public Parsers textFormatting() {
        return ofType(ParserType.TEXT_FORMATTING);
    }

    public Parsers defaults() {
        return instance.settings().defaultParsers().apply(this);
    }

    public Parsers all() {
        parsers.addAll(instance.allParsers());
        return this;
    }

    public List<Parser> values() {
        return parsers.stream()
            .sorted(Parsers.comparingPriority())
            .toList();
    }

    public @NonNull Iterator<Parser> iterator() {
        return parsers.stream()
            .sorted(Parsers.comparingPriority())
            .iterator();
    }

    public static Comparator<Parser> comparingPriority() {
        return (c1, c2) -> Integer.compare(c2.getPriority(), c1.getPriority());
    }
}
