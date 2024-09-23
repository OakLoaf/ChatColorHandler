package org.lushplugins.chatcolorhandler.resolvers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;

public interface Resolver {

    @NotNull TagResolver getResolver();

    default @NotNull TagResolver getResolver(Audience audience) {
        return getResolver();
    }
}
