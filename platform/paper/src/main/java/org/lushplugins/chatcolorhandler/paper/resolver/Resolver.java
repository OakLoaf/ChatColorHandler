package org.lushplugins.chatcolorhandler.paper.resolver;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.Parser;

public interface Resolver extends Parser {

    @Override
    default String parseString(String string, @Nullable Player player) {
        return string;
    }

    TagResolver getResolver(@Nullable Audience audience);
}
