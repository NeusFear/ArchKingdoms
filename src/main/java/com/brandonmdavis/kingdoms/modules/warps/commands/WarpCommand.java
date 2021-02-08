package com.brandonmdavis.kingdoms.modules.warps.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
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
import net.minecraft.command.suggestion.SuggestionProviders;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class WarpCommand {
	public static void register() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("warp");
			builder.then(CommandManager.argument("name", StringArgumentType.string()).suggests(WarpCompletionHandler.WARPS).executes(context -> execute(context)));
			dispatcher.register(builder);
		});
	}

	private static int execute(CommandContext<ServerCommandSource> context) {

		ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());

		//TODO MAKE THIS NICER
		if (Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId()).getPermissionLevel() < Ranks.BUILDER.getPermissionLevel()) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("You dont have permission to warp places.").build()).build());
			return CommandResult.SUCCESS;
		}

		String name = StringArgumentType.getString(context, "name");
		Warp warp = ServerDataHandler.getServerDataHandler().getServerData().getWarp(name);

		if (warp != null) {
			player.teleport(warp.getX(), warp.getY(), warp.getZ());
			player.pitch = warp.getPitch();
			player.yaw = warp.getYaw();
			Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warping to " + name + ".").build()).build());
			return CommandResult.SUCCESS;
		} else {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warp " + name + " does not exist.").build()).build());
			return CommandResult.FAIL;
		}
	}

	public static class WarpCompletionHandler {
		// This provides suggestions of what biomes can be selected. Since this uses the registry, mods that add new biomes will work without modification.
		public static final SuggestionProvider<ServerCommandSource> WARPS = SuggestionProviders.register(new Identifier("warps"), (ctx, builder) -> {
			ServerDataHandler.getServerDataHandler().getServerData().getWarps().stream().forEach(warp -> builder.suggest(warp.getName()));
			return builder.buildFuture();
		});

	}
}
