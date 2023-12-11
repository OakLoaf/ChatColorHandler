package me.dave.chatcolorhandler.resolvers;

import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class MiniPlaceholdersResolver implements Resolver {

    @Override
    public TagResolver getResolver() {
        return MiniPlaceholders.getGlobalPlaceholders();
    }

    @Override
    public TagResolver getResolver(Audience audience) {
        return MiniPlaceholders.getAudienceGlobalPlaceholders(audience);
    }
}
