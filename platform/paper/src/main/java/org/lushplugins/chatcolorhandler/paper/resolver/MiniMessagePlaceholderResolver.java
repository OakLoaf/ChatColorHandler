package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

public class MiniMessagePlaceholderResolver implements Resolver {
    public static final MiniMessagePlaceholderResolver INSTANCE = new MiniMessagePlaceholderResolver();
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

    @Override
    public ParserType getType() {
        return ParserType.PLACEHOLDER;
    }

    @Override
    public @NotNull TagResolver getResolver(@Nullable Audience audience) {
        return VANILLA_PLACEHOLDERS;
    }
}
