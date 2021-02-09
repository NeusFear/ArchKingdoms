package com.brandonmdavis.kingdoms.modules.command.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.ArrayList;

public class KingdomsCommandManager {

	public ArrayList<LiteralArgumentBuilder<ServerCommandSource>> commands;

	public KingdomsCommandManager() {
		commands = new ArrayList<>();
	}

	public void registerCommand(LiteralArgumentBuilder<ServerCommandSource> command) {
		commands.add(command);
	}

	public ArrayList<LiteralArgumentBuilder<ServerCommandSource>> getCommands() {
		return commands;
	}
}
