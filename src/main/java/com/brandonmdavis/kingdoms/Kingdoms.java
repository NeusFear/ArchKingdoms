package com.brandonmdavis.kingdoms;

import com.brandonmdavis.api.events.PlayerChatCallback;
import com.brandonmdavis.api.events.PlayerJoinCallback;
import com.brandonmdavis.api.events.PlayerLeaveCallback;
import com.brandonmdavis.api.messages.Colors;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import com.brandonmdavis.kingdoms.modules.command.commands.KingdomsCommandHandler;
import com.brandonmdavis.kingdoms.modules.command.commands.KingdomsCommandManager;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;
import com.brandonmdavis.kingdoms.modules.data.serverdata.ServerDataHandler;
import com.brandonmdavis.kingdoms.modules.discord.Bot;
import com.brandonmdavis.kingdoms.modules.discord.chatlink.DiscordChatLink;
import com.brandonmdavis.kingdoms.modules.permissions.Ranks;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static net.minecraft.util.Util.NIL_UUID;

public class Kingdoms implements ModInitializer {

	public static final String MOD_ID = "kindgoms";
	public static final String MOD_NAME = "Arch Kingdoms";
	public static final String URL = "https://pleodia.com";
	public static final String DISCORD_URL = "https://discord.gg/PxQjwh9";

	private static final Logger LOGGER = LogManager.getLogger(MOD_ID);

	private static PlayerDataHandler playerdataHandler;
	private static ServerDataHandler serverDataHandler;
	private static KingdomsCommandManager commandManager;

	public static void log(Level level, String message){
		LOGGER.log(level, "["+MOD_NAME+"] " + message);
	}

	public static MinecraftDedicatedServer getServer() {
		return  (MinecraftDedicatedServer)FabricLoader.getInstance().getGameInstance();
	}

	public static KingdomsCommandManager getCommandManager() {
		return commandManager;
	}

	@Override
	public void onInitialize() {

		Ranks.register();
		serverDataHandler = new ServerDataHandler();
		playerdataHandler = new PlayerDataHandler();
		commandManager = new KingdomsCommandManager();
		KingdomsCommandHandler.registerCommands();

		PlayerJoinCallback.EVENT.register(((connection, player) -> {
			String joinMessage = " joined the game.";
			DiscordChatLink.sendStatusMessage(true, player.getName().asString() + " joined the game.");
			if (playerdataHandler.getPlayerData(player.getUuidAsString()) == null) {
				Kingdoms.log(Level.INFO, "Creating playerdata for user with ID: " + player.getUuidAsString());
				playerdataHandler.createNewPlayerData(player.getUuidAsString(), player.getDisplayName().asString());
			} else {
				if (!player.getGameProfile().getName().equalsIgnoreCase(playerdataHandler.getPlayerData(player.getUuidAsString()).getMostRecentUsername())) {
					joinMessage = joinMessage.concat(" ( previously " + playerdataHandler.getPlayerData(player.getUuidAsString()).getMostRecentUsername()) + " )";
				}
			}
			Objects.requireNonNull(player.getServer()).getPlayerManager().broadcastChatMessage(Message.builder()
					.addComponent(MessageComponent.builder()
							.text(player.getName().asString() + joinMessage)
							.build()
					).build().getAsText(), MessageType.CHAT, NIL_UUID);
			return ActionResult.PASS;
		}));

		PlayerLeaveCallback.EVENT.register(((connection, player) -> {
			Objects.requireNonNull(player.getServer()).getPlayerManager().broadcastChatMessage(Message.builder()
					.addComponent(MessageComponent.builder()
							.text(player.getName().asString() + " left the game.")
							.build()
					).build().getAsText(), MessageType.CHAT, NIL_UUID);
			DiscordChatLink.sendStatusMessage(false, player.getName().asString() + " left the game.");
			return ActionResult.PASS;
		}));

		PlayerChatCallback.EVENT.register(((server, player, messageText) -> {
			Text message = Ranks.getRank(PlayerDataHandler.getPlayerDataHandler().getPlayerData(player.getUuidAsString()).getRankId()).getPrefix().build()
					.appendMessage(Message.builder()
							.addComponent(MessageComponent.builder().text(" " + player.getDisplayName().asString()).build())
							.addComponent(MessageComponent.builder().text(" > ").color(Colors.GRAY).build())
							.addComponent(MessageComponent.builder().text(messageText).build()).build())
					.getAsText();
			server.getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, player.getUuid());
			DiscordChatLink.sendToDiscord(player.getDisplayName().asString(), messageText, false);
			return ActionResult.PASS;
		}));

		ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
			DiscordChatLink.sendStatusMessage(false, "Stopping Server...");
			PlayerDataHandler.backupPlayerData(false);
			ServerDataHandler.getServerDataHandler().saveServerData(false);
			DiscordChatLink.sendStatusMessage(false, "Server Closed.");
		});

		CommandRegistrationCallback.EVENT.register(((commandDispatcher, b) -> {
			for (LiteralArgumentBuilder<ServerCommandSource> command : commandManager.getCommands()) {
				commandDispatcher.register(command);
			}
		}));

		ScheduledExecutorService backupScheduler = Executors.newSingleThreadScheduledExecutor();
		backupScheduler.scheduleAtFixedRate(PlayerDataHandler::backupPlayerData, 5, 5, TimeUnit.MINUTES);

		Bot.startBot();
		DiscordChatLink.sendStatusMessage(true, "Server Started.");
		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			DiscordChatLink.sendStatusMessage(true, "Server started in development Environment.");
		}
	}
}
