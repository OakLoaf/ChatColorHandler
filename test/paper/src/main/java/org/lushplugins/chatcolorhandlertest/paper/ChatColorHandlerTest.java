package org.lushplugins.chatcolorhandlertest.paper;

import org.lushplugins.chatcolorhandlertest.paper.command.MainCommand;
import org.bukkit.plugin.java.JavaPlugin;
import revxrsal.commands.Lamp;
import revxrsal.commands.bukkit.BukkitLamp;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

public final class ChatColorHandlerTest extends JavaPlugin {
    private static ChatColorHandlerTest plugin;

    @Override
    public void onEnable() {
        plugin = this;

        BukkitLamp.builder(this).build()
            .register(new MainCommand());
    }

    @Override
    public void onDisable() {
        plugin = null;
    }

    public static ChatColorHandlerTest getInstance() {
        return plugin;
    }
}
