package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.ParserType;

import java.util.ArrayList;
import java.util.List;

public class MiniMessageColorResolver implements Resolver {
    public static final MiniMessageColorResolver INSTANCE = new MiniMessageColorResolver();
    private static final TagResolver BASIC_COLORS = TagResolver.builder()
        .resolvers(tagResolvers())
        .build();

    @Override
    public ParserType getType() {
        return ParserType.COLOR;
    }

    @Override
    public TagResolver getResolver(@Nullable Audience audience) {
        return BASIC_COLORS;
    }

    private static List<TagResolver> tagResolvers() {
        List<TagResolver> resolvers = new ArrayList<>();

        try {
            // Resolvers are ordered in order of their addition to MiniMessage
            resolvers.add(StandardTags.color());
            resolvers.add(StandardTags.decorations());
            resolvers.add(StandardTags.gradient());
            resolvers.add(StandardTags.rainbow());
            resolvers.add(StandardTags.reset());
            resolvers.add(StandardTags.transition());
            resolvers.add(StandardTags.pride());
            resolvers.add(StandardTags.shadowColor());
            resolvers.add(StandardTags.sequentialHead());
            resolvers.add(StandardTags.sprite());
        } catch (NoSuchMethodError ignored) {}

        return resolvers;
    }
}
