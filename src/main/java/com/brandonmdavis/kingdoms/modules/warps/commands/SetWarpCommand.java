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

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;

public class SetWarpCommand {
	public static void register() {
		CommandRegistry.INSTANCE.register(true, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("setwarp")
					.then(argument("name", word()).executes(SetWarpCommand::execute));
			dispatcher.register(builder);
		});
	}

	private static int execute(CommandContext<ServerCommandSource> context) {

		ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());

		//TODO MAKE THIS NICER
		if (Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId()).getPermissionLevel() < Ranks.DEITY_OF_DESIGN.getPermissionLevel()) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("You dont have permission to set warps.").build()).build());
			return CommandResult.SUCCESS;
		}

		String name = StringArgumentType.getString(context, "name");
		Warp warp = ServerDataHandler.getServerDataHandler().getServerData().getWarp(name);

		if (warp != null) {
			ServerDataHandler.getServerDataHandler().getServerData().removeWarp(name);
			ServerDataHandler.getServerDataHandler().getServerData().addWarp(new Warp(name, player.getX(), player.getY(), player.getZ(), player.pitch, player.yaw));
			Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warp " + name + " moved to your current position.").build()).build());
			ServerDataHandler.getServerDataHandler().saveServerData();
			return CommandResult.SUCCESS;
		}

		ServerDataHandler.getServerDataHandler().getServerData().addWarp(new Warp(name, player.getX(), player.getY(), player.getZ(), player.pitch, player.yaw));
		Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warp " + name + " set to your current position.").build()).build());
		ServerDataHandler.getServerDataHandler().saveServerData();
		return CommandResult.SUCCESS;
	}
}
