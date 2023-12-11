package me.dave.chatcolorhandler.resolvers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class Resolvers {
    private static final HashSet<Resolver> resolvers = new HashSet<>();

    public static void register(Resolver resolver) {
        resolvers.add(resolver);
    }

    public static TagResolver[] getResolvers(@Nullable Audience audience, @Nullable List<Class<? extends Resolver>> resolvers) {
        List<TagResolver> requiredResolvers = new ArrayList<>();
        for (Resolver resolver : Resolvers.resolvers) {
            if (resolvers == null || resolvers.contains(resolver.getClass())) {
                try {
                    requiredResolvers.add(audience != null ? resolver.getResolver(audience) : resolver.getResolver());
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }

        return requiredResolvers.toArray(new TagResolver[0]);
    }
}
