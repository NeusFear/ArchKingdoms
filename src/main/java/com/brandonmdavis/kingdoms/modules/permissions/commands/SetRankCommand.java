package com.brandonmdavis.kingdoms.modules.permissions.commands;

import com.brandonmdavis.api.commands.Command;
import com.brandonmdavis.api.commands.CommandResult;
import com.brandonmdavis.api.commands.SourceUtil;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerData;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;
import com.brandonmdavis.kingdoms.modules.messenger.Messenger;
import com.brandonmdavis.kingdoms.modules.permissions.Rank;
import com.brandonmdavis.kingdoms.modules.permissions.Ranks;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class SetRankCommand extends Command {

	public SetRankCommand() {
		super(CommandManager.literal("setrank")
				.then(CommandManager.argument("name", StringArgumentType.word())
						.then(CommandManager.argument("rank", StringArgumentType.word())
								.executes(SetRankCommand::execute)
						)
				));
	}

	private static int execute(CommandContext<ServerCommandSource> context) {

		ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());

		//TODO MAKE THIS NICER
		if (Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId()).getPermissionLevel() < Ranks.DEITY_OF_DESIGN.getPermissionLevel()) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("You dont have permission to set ranks.").build()).build());
			return CommandResult.SUCCESS;
		}

		Rank rank = Ranks.getRank(StringArgumentType.getString(context, "rank"));
		PlayerData playerData = PlayerDataHandler.getPlayerDataHandler().getPlayerDataFromUsername(StringArgumentType.getString(context, "name"));

		if (rank == null) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("That rank does not exist.").build()).build());
			return CommandResult.FAIL;
		}
		if (playerData == null) {
			Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("That player does not exist.").build()).build());
			return CommandResult.FAIL;
		}
		playerData.setRankId(rank.getName());
		Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text(playerData.getMostRecentUsername() + "'s rank updated to " + rank.getName()).build()).build());
		PlayerDataHandler.getPlayerDataHandler().savePlayerData(playerData);
		return CommandResult.SUCCESS;
	}
}
