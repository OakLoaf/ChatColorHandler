package org.lushplugins.chatcolorhandler.parsers.custom;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.resolvers.Resolvers;

import java.util.Collections;

public class MiniMessageResolverParser implements Parser {

    @Override
    public String getType() {
        return ParserTypes.PLACEHOLDER;
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType) {
        return parseString(string, outputType, null);
    }

    @Override
    public String parseString(@NotNull String string, @NotNull OutputType outputType, Player player) {
        return switch (outputType) {
            case SPIGOT -> {
                string = string.replace('§', '&');

                TagResolver resolver = Resolvers.getResolver(player != null ? (Audience) player : null, Collections.emptyList());
                yield MiniMessageMessenger.LEGACY_COMPONENT_SERIALIZER.serialize(MiniMessageMessenger.MINI_MESSAGE.deserialize(string, resolver));
            }
            case MINI_MESSAGE -> string;
        };
    }
}
