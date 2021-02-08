package com.brandonmdavis.kingdoms.modules.discord.handlers;

import com.brandonmdavis.kingdoms.modules.discord.BotCommandRegistry;
import com.brandonmdavis.kingdoms.modules.discord.commands.BotPlayerDataCommand;
import com.brandonmdavis.kingdoms.modules.discord.commands.BotStatusCommand;

public class BotCommandHandler {

    public BotCommandHandler() {
        BotCommandRegistry.register(new BotStatusCommand(), "status", "online", "info");
        BotCommandRegistry.register(new BotPlayerDataCommand(), "playerdata", "pd");
    }
}
