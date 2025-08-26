package org.lushplugins.chatcolorhandler.parsers.custom;

import io.github.miniplaceholders.api.MiniPlaceholders;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

public class MiniPlaceholdersParser implements Resolver {
    public static final MiniPlaceholdersParser INSTANCE = new MiniPlaceholdersParser();

    private MiniPlaceholdersParser() {}

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public @NotNull TagResolver getResolver() {
        return MiniPlaceholders.globalPlaceholders();
    }

    @Override
    public @NotNull TagResolver getResolver(Audience audience) {
        return MiniPlaceholders.audienceGlobalPlaceholders();
    }
}
