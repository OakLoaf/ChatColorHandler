package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

public class MiniMessageTextFormattingResolver implements Resolver {
    public static final MiniMessageTextFormattingResolver INSTANCE = new MiniMessageTextFormattingResolver();
    private static final TagResolver TEXT_FORMATTING = TagResolver.builder()
        .resolvers(
            StandardTags.font(),
            StandardTags.newline()
        )
        .build();

    @Override
    public ParserType getType() {
        return ParserType.TEXT_FORMATTING;
    }

    @Override
    public @NotNull TagResolver getResolver(@Nullable Audience audience) {
        return TEXT_FORMATTING;
    }
}
