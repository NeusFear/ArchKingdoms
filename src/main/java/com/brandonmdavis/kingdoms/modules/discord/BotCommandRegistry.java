package com.brandonmdavis.kingdoms.modules.discord;

import java.util.ArrayList;

public class BotCommandRegistry {

    private static ArrayList<BotCommandExecutable> commands = new ArrayList<>();

    public static void register(BotCommand command, String... aliases) {
        for (String alias : aliases) {
            commands.add(new BotCommandExecutable(alias, command));
        }
    }

    public static void execute(String command, BotArgs args) {

        command = command.split("!")[1];
        for (BotCommandExecutable executable : commands) {
            if (executable.getAlias().equalsIgnoreCase(command.split(" ")[0])) {
                executable.getCommand().process(args);
            }
        }

    }
}
