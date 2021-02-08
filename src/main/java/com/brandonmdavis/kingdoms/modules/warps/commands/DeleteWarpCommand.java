package com.brandonmdavis.kingdoms.modules.warps.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.brandonmdavis.api.commands.CommandResult;
import com.brandonmdavis.api.commands.SourceUtil;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;
import com.brandonmdavis.kingdoms.modules.data.serverdata.ServerDataHandler;
import com.brandonmdavis.kingdoms.modules.messenger.Messenger;
import com.brandonmdavis.kingdoms.modules.permissions.Ranks;
import com.brandonmdavis.kingdoms.modules.warps.Warp;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class DeleteWarpCommand {
	public static void register() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("delwarp")
					.then(CommandManager.argument("name", StringArgumentType.string()).executes(context -> execute(context)));
			dispatcher.register(builder);
		});
	}

	private static int execute(CommandContext<ServerCommandSource> context) {

		ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());

		//TODO MAKE THIS NICER
		if (Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId()).getPermissionLevel() < Ranks.ADMINISTRATOR.getPermissionLevel()) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("You dont have permission to delete warps.").build()).build());
			return CommandResult.SUCCESS;
		}

		String name = StringArgumentType.getString(context, "name");
		Warp warp = ServerDataHandler.getServerDataHandler().getServerData().getWarp(name);

		if (warp != null) {
			ServerDataHandler.getServerDataHandler().getServerData().removeWarp(name);
			Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warp " + name + " deleted.").build()).build());
			ServerDataHandler.getServerDataHandler().saveServerData();
			return CommandResult.SUCCESS;
		} else {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warp " + name + " does not exist.").build()).build());
			return CommandResult.SUCCESS;
		}
	}
}
