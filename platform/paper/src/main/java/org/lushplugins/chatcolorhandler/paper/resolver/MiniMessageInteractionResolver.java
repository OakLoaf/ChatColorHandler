package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

public class MiniMessageInteractionResolver implements Resolver {
    public static final MiniMessageInteractionResolver INSTANCE = new MiniMessageInteractionResolver();
    private static final TagResolver INTERACTION = TagResolver.builder()
        .resolvers(
            StandardTags.hoverEvent(),
            StandardTags.clickEvent(),
            StandardTags.insertion()
        )
        .build();

    @Override
    public ParserType getType() {
        return ParserType.INTERACTION;
    }

    @Override
    public @NotNull TagResolver getResolver(@Nullable Audience audience) {
        return INTERACTION;
    }
}
