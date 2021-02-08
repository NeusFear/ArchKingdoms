package com.brandonmdavis.api.messages;

public class MessageComponent {

	private String text;
	private String color;
	private boolean bold;
	private boolean italic;
	private boolean underlined;
	private boolean strikethrough;
	private boolean obfuscated;
	private ActionMessageComponent clickEvent;
	private ActionMessageComponent hoverEvent;

	private MessageComponent(String text, String color, boolean bold, boolean italic, boolean underlined, boolean strikethrough, boolean obfuscated, ActionMessageComponent clickEvent, ActionMessageComponent hoverEvent) {
		this.text = text;
		this.color = color;
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.strikethrough = strikethrough;
		this.obfuscated = obfuscated;
		this.clickEvent = clickEvent;
		this.hoverEvent = hoverEvent;
	}

	public static MessageComponent.Builder builder() {
		return new MessageComponent.Builder();
	}

	public static class Builder {

		private String text;
		private String color;
		private boolean bold;
		private boolean italic;
		private boolean underlined;
		private boolean strikethrough;
		private boolean obfuscated;
		private ActionMessageComponent clickEvent;
		private ActionMessageComponent hoverEvent;

		private Builder() {
			text = "";
			color = null;
			clickEvent = null;
			hoverEvent = null;
		}

		public Builder text(String text) {
			this.text = text;
			return this;
		}

		public Builder color(String color) {
			this.color = color;
			return this;
		}

		public Builder bold() {
			this.bold = true;
			return this;
		}

		public Builder italic() {
			this.italic = true;
			return this;
		}

		public Builder underlined() {
			this.underlined = true;
			return this;
		}

		public Builder strikethrough() {
			this.strikethrough = true;
			return this;
		}

		public Builder scramble() {
			this.obfuscated = true;
			return this;
		}

		public Builder clickEvent(String action, String value) {
			this.clickEvent = new ActionMessageComponent(action, value);
			return this;
		}

		public Builder hoverEvent(String action, String value) {
			this.hoverEvent = new ActionMessageComponent(action, value);
			return this;
		}

		public MessageComponent build() {
			return new MessageComponent(this.text, this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent);
		}

	}

}
