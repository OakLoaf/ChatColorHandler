package org.lushplugins.chatcolorhandler.paper.resolver;

import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

public class MiniPlaceholdersResolver implements Resolver {
    public static final MiniPlaceholdersResolver INSTANCE = new MiniPlaceholdersResolver();

    @Override
    public ParserType getType() {
        return ParserType.PLACEHOLDER;
    }

    @Override
    public @NotNull TagResolver getResolver(@Nullable Audience audience) {
        if (audience != null) {
            return MiniPlaceholders.audienceGlobalPlaceholders();
        } else {
            return MiniPlaceholders.globalPlaceholders();
        }
    }
}
