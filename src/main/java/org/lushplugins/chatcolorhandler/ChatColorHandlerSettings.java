package org.lushplugins.chatcolorhandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.lushplugins.chatcolorhandler.messengers.LegacyMessenger;
import org.lushplugins.chatcolorhandler.messengers.Messenger;
import org.lushplugins.chatcolorhandler.messengers.MiniMessageMessenger;
import org.lushplugins.chatcolorhandler.parsers.Parser;
import org.lushplugins.chatcolorhandler.parsers.ParserTypes;
import org.lushplugins.chatcolorhandler.parsers.Parsers;
import org.lushplugins.chatcolorhandler.parsers.custom.*;

import java.util.List;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public class ChatColorHandlerSettings {
    private boolean initialised = false;
    private Messenger messenger;
    private List<Parser> defaultParsers = ParserTypes.all();
    private boolean debug = false;

    public Messenger messenger() {
        if (!initialised) {
            init();
        }

        return messenger;
    }

    /**
     * Adjust the ChatColorHandler messenger to be used. This is handled automatically by ChatColorHandler but can be overridden for custom uses.
     * @param messenger The messenger to use
     */
    public ChatColorHandlerSettings messenger(Messenger messenger) {
        if (!initialised) {
            init();
        }

        this.messenger = messenger;
        return this;
    }

    public List<Parser> defaultParsers() {
        if (!initialised) {
            init();
        }

        return defaultParsers;
    }

    /**
     * Set the default parsers to use, when not overridden all registered parsers are used
     * @param parsers The default parsers to use when none are specified
     */
    public ChatColorHandlerSettings defaultParsers(List<Parser> parsers) {
        this.defaultParsers = parsers;
        return this;
    }

    public boolean debug() {
        return debug;
    }

    /**
     * @param debug Whether to enable debug logs
     */
    public ChatColorHandlerSettings debug(boolean debug) {
        this.debug = debug;
        return this;
    }

    private void init() {
        initialised = true;

        Parsers.register(LegacyHexParser.INSTANCE, 85);
        Parsers.register(LegacySpigotParser.INSTANCE, 84);
        Parsers.register(HexParser.INSTANCE, 83);

        try {
            Class.forName("net.kyori.adventure.text.minimessage.MiniMessage").getMethod("miniMessage");
            messenger(new MiniMessageMessenger());

            Parsers.register(MiniMessageResolverParser.INSTANCE, 89);
            Parsers.register(MiniMessageColorParser.INSTANCE, 73);
            Parsers.register(MiniMessageInteractionParser.INSTANCE, 72);
            Parsers.register(MiniMessagePlaceholderParser.INSTANCE, 71);
            Parsers.register(MiniMessageTextFormattingParser.INSTANCE, 70);
            ChatColorHandler.debugLog("Found MiniMessage in Server. MiniMessage support enabled.");
        } catch (ClassNotFoundException | NoSuchMethodException ignored) {
            messenger(new LegacyMessenger());
            ChatColorHandler.debugLog("Unable to find MiniMessage. MiniMessage support not enabled.");
        }

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        if (pluginManager.getPlugin("PlaceholderAPI") != null && pluginManager.isPluginEnabled("PlaceholderAPI")) {
            Parsers.register(PlaceholderAPIParser.INSTANCE, 90);
            ChatColorHandler.debugLog("Found plugin \"PlaceholderAPI\". PlaceholderAPI support enabled.");
        }

        if (pluginManager.getPlugin("MiniPlaceholders") != null && pluginManager.isPluginEnabled("MiniPlaceholders")) {
            Parsers.register(MiniPlaceholdersParser.INSTANCE, -1);
            ChatColorHandler.debugLog("Found plugin \"MiniPlaceholders\". MiniPlaceholders support enabled.");
        }

        defaultParsers(ParserTypes.all());
    }
}
