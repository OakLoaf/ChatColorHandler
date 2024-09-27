package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

public class MiniMessageTextFormattingParser implements Resolver {
    public static final MiniMessageTextFormattingParser INSTANCE = new MiniMessageTextFormattingParser();
    private static final TagResolver TEXT_FORMATTING = TagResolver.builder()
        .resolvers(
            StandardTags.font(),
            StandardTags.newline()
        )
        .build();

    private MiniMessageTextFormattingParser() {}

    @Override
    public String getType() {
        return ParserTypes.TEXT_FORMATTING;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, TEXT_FORMATTING))
                    .replace('&', '§');
            }
            case MINI_MESSAGE -> string;
        };
    }

    @Override
    public @NotNull TagResolver getResolver() {
        return TEXT_FORMATTING;
    }
}
