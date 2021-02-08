package com.brandonmdavis.api.commands;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class SourceUtil {

	public static ServerPlayerEntity getPlayerFromSource(ServerCommandSource source) {
		try {
			return source.getPlayer();
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

}
