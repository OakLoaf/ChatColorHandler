package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

public class MiniMessagePlaceholderParser implements Resolver {
    public static final MiniMessagePlaceholderParser INSTANCE = new MiniMessagePlaceholderParser();
    private static final TagResolver VANILLA_PLACEHOLDERS = TagResolver.builder()
        .resolvers(
            StandardTags.keybind(),
            StandardTags.translatable(),
            StandardTags.translatableFallback(),
            StandardTags.selector(),
            StandardTags.score(),
            StandardTags.nbt()
        )
        .build();

    private MiniMessagePlaceholderParser() {}

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, VANILLA_PLACEHOLDERS))
                    .replace('&', '§');
            }
            case MINI_MESSAGE -> string;
        };
    }

    @Override
    public @NotNull TagResolver getResolver() {
        return VANILLA_PLACEHOLDERS;
    }
}
