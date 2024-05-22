package org.lushplugins.chatcolorhandler.resolvers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Resolvers {
    private static final HashSet<Resolver> resolvers = new HashSet<>();

    public static void register(Resolver resolver) {
        resolvers.add(resolver);
    }

    public static void register(TagResolver tagResolver) {
        resolvers.add(() -> tagResolver);
    }

    public static TagResolver getResolver(@Nullable Audience audience, @Nullable List<Class<? extends Resolver>> resolvers) {
        TagResolver.Builder tagResolver = TagResolver.builder();
        for (Resolver resolver : Resolvers.resolvers) {
            if (resolvers == null || resolvers.contains(resolver.getClass())) {
                try {
                    tagResolver.resolver(audience != null ? resolver.getResolver(audience) : resolver.getResolver());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        return tagResolver.build();
    }
}
