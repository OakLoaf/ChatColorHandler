package org.lushplugins.chatcolorhandler.parsers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public interface Resolver extends Parser {

    @Override
    default String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return string;
    }

    @NotNull TagResolver getResolver();

    default @NotNull TagResolver getResolver(Audience audience) {
        return getResolver();
    }
}
