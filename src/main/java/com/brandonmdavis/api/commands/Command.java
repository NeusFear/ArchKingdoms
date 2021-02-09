package com.brandonmdavis.api.commands;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;

public abstract class Command {

    public static LiteralArgumentBuilder<ServerCommandSource> commandBuilder;

    public Command(LiteralArgumentBuilder<ServerCommandSource> builder) {
        commandBuilder = builder;
    }

    public void register() {
        Kingdoms.getCommandManager().registerCommand(commandBuilder);
    }
}
