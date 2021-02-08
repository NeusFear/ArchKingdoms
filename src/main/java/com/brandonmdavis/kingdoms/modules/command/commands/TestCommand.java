package com.brandonmdavis.kingdoms.modules.command.commands;

import com.brandonmdavis.api.messages.ActionMessageComponent;
import com.brandonmdavis.kingdoms.modules.messenger.Messenger;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.brandonmdavis.api.commands.CommandResult;
import com.brandonmdavis.api.messages.Colors;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import net.fabricmc.fabric.api.registry.CommandRegistry;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class TestCommand {
	public static void register() {
		CommandRegistry.INSTANCE.register(false, dispatcher -> {
			LiteralArgumentBuilder<ServerCommandSource> builder = CommandManager.literal("testcommand")
					.executes(context -> execute(context.getSource()));
			dispatcher.register(builder);
		});
	}

	private static int execute(ServerCommandSource source) {
		Messenger.messageFormatted(source,
				Message.builder()
						.addComponent(MessageComponent.builder()
								.text("hello")
								.color(Colors.RED)
								.underlined()
								.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, "hello")
								.clickEvent(ActionMessageComponent.Click.RUN_COMMAND, "say hi")
								.build())
						.addComponent(MessageComponent.builder()
								.text(" there")
								.build()
				));
		try {
			source.getPlayer().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.RESET, null));
			source.getPlayer().networkHandler.sendPacket(new TitleS2CPacket(10, 30, 10));
			source.getPlayer().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.SUBTITLE, Message.builder().addComponent(MessageComponent.builder().text("TESTSUBTITLE").color(Colors.RED).build()).build().getAsText()));
			source.getPlayer().networkHandler.sendPacket(new TitleS2CPacket(TitleS2CPacket.Action.TITLE, Message.builder().addComponent(MessageComponent.builder().text("TESTTIELE").color(Colors.GOLD).build()).build().getAsText()));
		} catch (CommandSyntaxException e) {
			e.printStackTrace();
		}
		return CommandResult.SUCCESS;
	}
}
