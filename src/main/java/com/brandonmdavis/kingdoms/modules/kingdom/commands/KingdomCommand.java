package com.brandonmdavis.kingdoms.modules.kingdom.commands;

import com.brandonmdavis.api.commands.Command;
import com.brandonmdavis.api.commands.CommandResult;
import com.brandonmdavis.api.commands.SourceUtil;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;
import com.brandonmdavis.kingdoms.modules.data.serverdata.ServerDataHandler;
import com.brandonmdavis.kingdoms.modules.kingdom.KingdomHome;
import com.brandonmdavis.kingdoms.modules.messenger.Messenger;
import com.brandonmdavis.kingdoms.modules.permissions.Rank;
import com.brandonmdavis.kingdoms.modules.permissions.Ranks;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.mojang.brigadier.arguments.StringArgumentType.word;
import static net.minecraft.server.command.CommandManager.argument;

public class KingdomCommand extends Command {

    public KingdomCommand() {
        super(CommandManager.literal("kingdom").then(argument("argument", word()).executes(KingdomCommand::execute)));
    }

    private static int execute(CommandContext<ServerCommandSource> context) {

        String argument = StringArgumentType.getString(context, "argument");

        if (argument.equalsIgnoreCase("home")) {
            return executeGoHome(context);
        }

        if (argument.equalsIgnoreCase("setHome")) {
            return executeSetHome(context);
        }

        Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Unknown kindgom sub-command.").build()).build());

        return CommandResult.SUCCESS;
    }

    private static int executeSetHome(CommandContext<ServerCommandSource> context) {

        ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());
        Rank rank = Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId());

        if (rank.getPermissionLevel() > 1) {
            ServerDataHandler.getServerDataHandler().getServerData().setHome(rank, new KingdomHome(player.getX(), player.getY(), player.getZ(), player.pitch, player.yaw));
            Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Kingdom home location set to your current position.").build()).build());
            ServerDataHandler.getServerDataHandler().saveServerData();
        } else {
            Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Only the kingdom leader can set the home location.").build()).build());
        }

        return CommandResult.SUCCESS;
    }

    private static int executeGoHome(CommandContext<ServerCommandSource> context) {

        ServerPlayerEntity player = SourceUtil.getPlayerFromSource(context.getSource());
        Rank rank = Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId());
        KingdomHome warp = ServerDataHandler.getServerDataHandler().getServerData().getWarp(rank);

        if (warp == null) {
            Messenger.error(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("There was an error retrieving your kingdom home location.").build()).build());
            return CommandResult.SUCCESS;
        }

        player.teleport(warp.getX(), warp.getY(), warp.getZ());
        player.pitch = warp.getPitch();
        player.yaw = warp.getYaw();

        Messenger.info(context.getSource(), Message.builder().addComponent(MessageComponent.builder().text("Warping to your kingdom home.").build()).build());

        return CommandResult.SUCCESS;
    }
}
