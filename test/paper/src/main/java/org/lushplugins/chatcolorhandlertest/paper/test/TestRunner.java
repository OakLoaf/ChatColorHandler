package org.lushplugins.chatcolorhandlertest.paper.test;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import org.lushplugins.chatcolorhandler.common.parser.Parsers;
import org.lushplugins.chatcolorhandler.paper.PaperColor;
import org.lushplugins.chatcolorhandlertest.paper.ChatColorHandlerTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;

public class TestRunner {
    private int testIndex = 0;
    private boolean ran = false;
    private final @Nullable Player player;
    private final List<Test> tests = new ArrayList<>();

    public TestRunner() {
        this(null);
    }

    public TestRunner(@Nullable Player player) {
        this.player = player;
    }

    public TestRunner addTest(String input, @Nullable String expectedOutput, Function<String, String> translate) {
        this.tests.add(new Test(String.valueOf(++testIndex), input, expectedOutput, translate));
        return this;
    }

    public void run() {
        if (ran) {
            throw new IllegalStateException("This test has already been ran");
        }
        ran = true;

        for (Test test : tests) {
            test.run(player);
        }
    }

    record Test(String id, String input, @Nullable String expectedOutput, Function<String, String> translate) {

        private String run() {
            return run(null);
        }

        private String run(Player player) {
            String output = "";
            try {
                output = translate.apply(input);
            } catch (Exception e) {
                Bukkit.getLogger().log(Level.WARNING, e.getMessage(), e);
            }

            if (expectedOutput == null || expectedOutput.equals(output)) {
                ChatColorHandlerTest.getInstance().getLogger().info("""
                    Test %s Passed:
                      Input '%s' passed tests.
                      Received: '%s'
                    """.formatted(id, input, output));

                if (player != null) {
                    player.sendMessage("§a✔§r: " + output);
                }
            } else {
                ChatColorHandlerTest.getInstance().getLogger().info("""
                    Test %s Failed:
                      Input '%s' failed tests.
                      Expected: '%s'
                      Received: '%s'
                    """.formatted(id, input, expectedOutput, output));

                if (player != null) {
                    player.sendMessage("§c✕§r: " + output);
                }
            }

            return output;
        }
    }

    public static void runTest(CommandSender sender, @Nullable Player player) {
        String inputOne = "&#A4D3F9This is a test string &rusing &lbold, <u>underline</u>&r and shows your name: <#F9CAA4>%player_name%</#F9CAA4>";

        new TestRunner(player)
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r and shows your name: ",
                (input) -> PaperColor.handler().translateRaw(input, null, Parsers::defaults))
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r and shows your name: §x§f§9§c§a§a§4" + (player != null ? player.getName() : "%player_name%"),
                (input) -> PaperColor.handler().translateRaw(input, player, Parsers::defaults))
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r and shows your name: §x§f§9§c§a§a§4%player_name%",
                (input) -> PaperColor.handler().translateRaw(input, null, Parsers::color))
            .addTest(
                inputOne,
                "§x§a§4§d§3§f§9This is a test string §rusing §lbold, §nunderline§r and shows your name: §x§f§9§c§a§a§4%player_name%",
                (input) -> PaperColor.handler().translateRaw(input, player, Parsers::color))
            .addTest(
                inputOne,
                "&#A4D3F9This is a test string &rusing &lbold, <u>underline</u>&r and shows your name: <#F9CAA4>" + (player != null ? player.getName() : "%player_name%") + "</#F9CAA4>",
                (input) -> PaperColor.handler().translateRaw(input, player, Parsers::placeholder))
            .run();

        sender.sendMessage(" ");
        sender.sendMessage(PaperColor.handler().translate(inputOne));
        sender.sendMessage(PaperColor.handler().translate(inputOne, player));
        sender.sendMessage(PaperColor.handler().translate(inputOne, Parsers::color));
        sender.sendMessage(PaperColor.handler().translate(inputOne, player, Parsers::color));
        sender.sendMessage(PaperColor.handler().translate(inputOne, player, Parsers::placeholder));
    }
}
