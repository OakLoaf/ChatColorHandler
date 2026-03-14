package org.lushplugins.chatcolorhandlertest.paper.command;

import org.lushplugins.chatcolorhandler.paper.PaperColor;
import org.lushplugins.chatcolorhandlertest.paper.ChatColorHandlerTest;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.lushplugins.chatcolorhandlertest.paper.test.TestRunner;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;

@SuppressWarnings("unused")
@Command("chatcolorhandlerpapertest")
public class MainCommand {

    @Subcommand("parse")
    public void parse(CommandSender sender, String content) {
        Component message;
        if (sender instanceof Player player) {
            message = PaperColor.handler().translate(content, player);
        } else {
            message = PaperColor.handler().translate(content);
        }

        sender.sendMessage(message);
    }

    @Subcommand("run-tests")
    public void runTests(BukkitCommandActor actor) {
        TestRunner.runTest(actor.sender(), actor.asPlayer());
    }

    @Subcommand("version")
    public void version(CommandSender sender) {
        PaperColor.handler().sendMessage(sender, "&#a8e1ffYou are currently running ChatColorHandlerTest (paper) version &#58b1e0" + ChatColorHandlerTest.getInstance().getDescription().getVersion());
    }
}
