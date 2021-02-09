package com.brandonmdavis.kingdoms.modules.command.commands;

import com.brandonmdavis.kingdoms.modules.kingdom.commands.KingdomCommand;

public class KingdomsCommandHandler {

    public static void registerCommands() {
        new TestCommand().register();
        new KingdomCommand().register();
    }
}
