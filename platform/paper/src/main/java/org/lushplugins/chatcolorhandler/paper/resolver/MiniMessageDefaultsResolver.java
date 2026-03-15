package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.StandardTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

public class MiniMessageDefaultsResolver implements Resolver {
    public static final MiniMessageDefaultsResolver INSTANCE = new MiniMessageDefaultsResolver();

    @Override
    public @Nullable ParserType getType() {
        return null;
    }

    @Override
    public @NotNull TagResolver getResolver(@Nullable Audience audience) {
        return StandardTags.defaults();
    }
}
