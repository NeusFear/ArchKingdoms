package com.brandonmdavis.kingdoms.modules.permissions;

import com.brandonmdavis.api.messages.Message;

public class Rank {

	private String name;
	private int permissionLevel;
	private Message.Builder prefix;
	private String description;
	private String link;

	public Rank(String name, int permissionLevel, String description, String link) {
		this.name = name;
		this.permissionLevel = permissionLevel;
		this.description = description;
		this.link = link;
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

	public String getLink() {
		return link;
	}

	public Message.Builder getPrefix() {
		return prefix;
	}

	public void setPrefix(Message.Builder prefix) {
		this.prefix = prefix;
	}
}
