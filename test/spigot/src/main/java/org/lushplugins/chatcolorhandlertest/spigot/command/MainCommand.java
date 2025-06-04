package org.lushplugins.chatcolorhandlertest.spigot.command;

import org.lushplugins.chatcolorhandlertest.spigot.ChatColorHandlerTest;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lushplugins.chatcolorhandler.ChatColorHandler;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;

@SuppressWarnings("unused")
@Command("chatcolorhandlerpapertest")
public class MainCommand {

    @Subcommand("parse")
    public void parse(CommandSender sender, String content) {
        String message;
        if (sender instanceof Player player) {
            message = ChatColorHandler.translate(content, player);
        } else {
            message = ChatColorHandler.translate(content);
        }

        sender.sendMessage(message);
    }

    @Subcommand("version")
    public void version(CommandSender sender) {
        ChatColorHandler.sendMessage(sender, "&#a8e1ffYou are currently running ChatColorHandlerTest (spigot) version &#58b1e0" + ChatColorHandlerTest.getInstance().getDescription().getVersion());
    }
}
