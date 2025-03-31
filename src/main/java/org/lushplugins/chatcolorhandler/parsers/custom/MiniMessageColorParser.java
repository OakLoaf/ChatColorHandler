package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

import java.util.List;

public class MiniMessageColorParser implements Resolver {
    public static final MiniMessageColorParser INSTANCE = new MiniMessageColorParser();
    private static final TagResolver BASIC_COLORS = TagResolver.builder()
        .resolvers(
            MiniMessageMessenger.resolvers(List.of(
                StandardTags::color,
                StandardTags::decorations,
                StandardTags::gradient,
                StandardTags::rainbow,
                StandardTags::reset,
                StandardTags::transition,
                StandardTags::pride,
                StandardTags::shadowColor
            ))
        )
        .build();

    private MiniMessageColorParser() {}

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, BASIC_COLORS));
            }
            case MINI_MESSAGE -> string;
        };
    }

    @Override
    public @NotNull TagResolver getResolver() {
        return BASIC_COLORS;
    }
}
