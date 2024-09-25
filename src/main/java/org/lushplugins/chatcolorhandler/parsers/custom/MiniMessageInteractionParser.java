package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

public class MiniMessageInteractionParser implements Resolver {
    public static final MiniMessageInteractionParser INSTANCE = new MiniMessageInteractionParser();
    private static final TagResolver INTERACTION = TagResolver.builder()
        .resolvers(
            StandardTags.hoverEvent(),
            StandardTags.clickEvent(),
            StandardTags.insertion()
        )
        .build();

    private MiniMessageInteractionParser() {}

    @Override
    public String getType() {
        return ParserTypes.INTERACTION;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, INTERACTION));
            }
            case MINI_MESSAGE -> string;
        };
    }

    @Override
    public @NotNull TagResolver getResolver() {
        return INTERACTION;
    }
}
