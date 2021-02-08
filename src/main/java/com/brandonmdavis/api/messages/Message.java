package com.brandonmdavis.api.messages;

import com.google.gson.Gson;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class Message {

	private String jsonString;

	private Message(ArrayList<MessageComponent> components) {
		this.jsonString = "[\"\",";
		for (MessageComponent component : components) {
			this.jsonString = this.jsonString.concat(new Gson().toJson(component)).concat(",");
		}
		this.jsonString = this.jsonString.substring(0, this.jsonString.length() - 1);
		this.jsonString = this.jsonString.concat("]");
	}

	private Message(String jsonString) {
		this.jsonString = jsonString;
	}

	public String getAsJsonString() {
		return jsonString;
	}

	public Text getAsText() {
		return Text.Serializer.fromJson(getAsJsonString());
	}

	public Message appendMessage(Message message) {
		return new Message(this.getAsJsonString().substring(0, this.getAsJsonString().length() - 1) + message.getAsJsonString().split("\"\"")[1]);
	}

	public static Message.Builder builder() {
		return new Message.Builder();
	}

	public static class Builder {

		private ArrayList<MessageComponent> components = new ArrayList<>();

		private Builder() { }

		public Builder addComponent(MessageComponent component) {
			components.add(component);
			return this;
		}

		public Message build() {
			return new Message(components);
		}

		public ArrayList<MessageComponent> getComponents() {
			return components;
		}
	}

}
