package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import net.kyori.adventure.text.minimessage.tag.standard.*;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Resolver;

import java.util.ArrayList;
import java.util.List;

public class MiniMessageColorParser implements Resolver {
    public static final MiniMessageColorParser INSTANCE = new MiniMessageColorParser();
    private static final TagResolver BASIC_COLORS = TagResolver.builder()
        .resolvers(tagResolvers())
        .build();

    private MiniMessageColorParser() {}

    @Override
    public String getType() {
        return ParserTypes.COLOR;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, BASIC_COLORS));
            }
            case MINI_MESSAGE -> string;
        };
    }

    @Override
    public @NotNull TagResolver getResolver() {
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
        } catch (NoSuchMethodError ignored) {}

        return resolvers;
    }
}
