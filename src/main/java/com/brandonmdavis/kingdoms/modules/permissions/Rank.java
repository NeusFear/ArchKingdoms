package com.brandonmdavis.kingdoms.modules.permissions;

import com.brandonmdavis.api.messages.ActionMessageComponent;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;

public class Rank {

	public static final String PREFIX_PREFIX = "[";
	public static final String PREFIX_SUFFIX = "]";

	private String name;
	private int permissionLevel;
	private Message.Builder prefix;
	private String description;

	public Rank(String name, int permissionLevel, String description, String color, String bracketColor) {
		this.name = name;
		this.permissionLevel = permissionLevel;
		this.description = description;
		this.prefix = Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(bracketColor).build())
				.addComponent(MessageComponent.builder()
						.text(name.replace("_", " ").toUpperCase()).color(color)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, description)
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(bracketColor).build());
	}

	public String getName() {
		return name;
	}

	public int getPermissionLevel() {
		return permissionLevel;
	}

	public String getDescription() {
		return description;
	}

	public Message.Builder getPrefix() {
		return prefix;
	}
}
