package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

import java.util.*;
import java.util.logging.Level;

public class ResolverRegistry {
    private final Set<Resolver> resolvers = new HashSet<>();

    /**
     * @param resolver resolver to register
     */
    public void register(Resolver resolver) {
        resolvers.add(resolver);
    }

    /**
     * @param resolvers resolver to register
     */
    public void register(Resolver... resolvers) {
        for (Resolver resolver : resolvers) {
            register(resolver);
        }
    }

    public TagResolver asTagResolver(@Nullable Audience audience, @NotNull List<Resolver> resolvers) {
        return TagResolver.builder()
            .resolvers(resolvers.stream()
                .map(resolver -> {
                    try {
                        return resolver.getResolver(audience);
                    } catch (Throwable e) {
                        Bukkit.getLogger().log(Level.WARNING, "[ChatColorHandler] Failed to combine resolver: ", e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .toList())
            .build();
    }

    public Set<Resolver> values() {
        return resolvers;
    }

    public List<Resolver> ofTypes(@NotNull String... types) {
        return values().stream()
            .filter(resolver -> {
                ParserType resolverType = resolver.getType();
                if (resolverType == null) {
                    return true;
                }

                for (String type : types) {
                    if (resolver.getType().equals(type)) {
                        return true;
                    }
                }

                return false;
            })
            .toList();
    }

    public List<Resolver> ofType(@NotNull String type) {
        return ofTypes(type);
    }
}
