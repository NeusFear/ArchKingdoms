package com.brandonmdavis.kingdoms.modules.messenger;

import com.brandonmdavis.api.messages.ActionMessageComponent;
import com.brandonmdavis.kingdoms.Kingdoms;
import com.brandonmdavis.api.commands.CommandResult;
import com.brandonmdavis.api.messages.Colors;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class Messenger {

	public static final Message INFO_PREFIX = Message.builder()
			.addComponent(MessageComponent.builder()
					.text("[").color(Colors.GRAY)
					.build())
			.addComponent(MessageComponent.builder()
					.text(Kingdoms.MOD_ID.substring(0,1).toUpperCase() + Kingdoms.MOD_ID.substring(1))
					.color(Colors.GOLD)
					.build())
			.addComponent(MessageComponent.builder()
					.text("] ").color(Colors.GRAY)
					.build()).build();

	public static final Message WARNING_PREFIX = Message.builder()
			.addComponent(MessageComponent.builder()
					.text("[").color(Colors.GRAY)
					.build())
			.addComponent(MessageComponent.builder()
					.text(Kingdoms.MOD_ID.substring(0,1).toUpperCase() + Kingdoms.MOD_ID.substring(1))
					.color(Colors.RED)
					.build())
			.addComponent(MessageComponent.builder()
					.text("] ").color(Colors.GRAY)
					.build()).build();

	public static final Message ERROR_PREFIX = Message.builder()
			.addComponent(MessageComponent.builder()
					.text("[").color(Colors.RED)
					.build())
			.addComponent(MessageComponent.builder()
					.text(Kingdoms.MOD_ID.substring(0,1).toUpperCase() + Kingdoms.MOD_ID.substring(1))
					.color(Colors.DARK_RED)
					.build())
			.addComponent(MessageComponent.builder()
					.text("] ").color(Colors.RED)
					.build()).build();

	public static Text fromDiscord(String name, String message) {
		return Message.builder()
				.addComponent(MessageComponent.builder().text("[").color(Colors.GRAY).hoverEvent(ActionMessageComponent.Hover.TOOLTIP, "Message sent from our discord (Click to join)").clickEvent(ActionMessageComponent.Click.OPEN_URL, Kingdoms.DISCORD_URL).build())
				.addComponent(MessageComponent.builder().text("D").color(Colors.PURPLE).hoverEvent(ActionMessageComponent.Hover.TOOLTIP, "Message sent from our discord (Click to join)").clickEvent(ActionMessageComponent.Click.OPEN_URL, Kingdoms.DISCORD_URL).build())
				.addComponent(MessageComponent.builder().text("]").color(Colors.GRAY).hoverEvent(ActionMessageComponent.Hover.TOOLTIP, "Message sent from our discord (Click to join)").clickEvent(ActionMessageComponent.Click.OPEN_URL, Kingdoms.DISCORD_URL).build())
				.addComponent(MessageComponent.builder().text(" " + name.split("#")[0]).color(Colors.WHITE).hoverEvent(ActionMessageComponent.Hover.TOOLTIP, "Message sent from our discord (Click to join)").clickEvent(ActionMessageComponent.Click.OPEN_URL, Kingdoms.DISCORD_URL).build())
				.addComponent(MessageComponent.builder().text(": " + message).color(Colors.GRAY).build())
				.build().getAsText();
	}

	public static int messageFormatted(ServerCommandSource source, Message.Builder message) {
		source.sendFeedback(message.build().getAsText(), false);
		return CommandResult.SUCCESS;
	}

	public static int info(ServerCommandSource source, Message message) {
		source.sendFeedback(INFO_PREFIX.appendMessage(message).getAsText(), false);
		return CommandResult.SUCCESS;
	}

	public static int warn(ServerCommandSource source, Message message) {
		source.sendFeedback(WARNING_PREFIX.appendMessage(message).getAsText(), false);
		return CommandResult.SUCCESS;
	}

	public static int error(ServerCommandSource source, Message message) {
		source.sendFeedback(ERROR_PREFIX.appendMessage(message).getAsText(), false);
		return CommandResult.SUCCESS;
	}

	//TODO add boolean to send to console using a message.toPlain method

}
