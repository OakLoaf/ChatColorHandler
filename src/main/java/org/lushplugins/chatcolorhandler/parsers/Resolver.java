package org.lushplugins.chatcolorhandler.parsers;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.logging.Level;

public interface Resolver extends Parser {

    @Override
    default String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return string;
    }

    @NotNull TagResolver getResolver();

    default @NotNull TagResolver getResolver(Audience audience) {
        return getResolver();
    }

    static TagResolver combineResolvers(@Nullable Audience audience, @NotNull List<Resolver> resolvers) {
        TagResolver.Builder tagResolver = TagResolver.builder();
        for (Resolver resolver : resolvers) {
            try {
                tagResolver.resolver(audience != null ? resolver.getResolver(audience) : resolver.getResolver());
            } catch (Throwable e) {
                Bukkit.getLogger().log(Level.WARNING, "[ChatColorHandler] Failed to combine resolver: ", e);
            }
        }

        return tagResolver.build();
    }
}
