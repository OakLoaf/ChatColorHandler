package me.dave.chatcolorhandler.resolvers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public interface Resolver {
    TagResolver getResolver();
    TagResolver getResolver(Audience audience);
}
