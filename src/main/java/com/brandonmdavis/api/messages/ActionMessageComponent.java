package com.brandonmdavis.api.messages;

public class ActionMessageComponent {

	private String action;
	private String value;

	public ActionMessageComponent(String action, String value) {
		this.action = action;
		this.value = value;
	}

	public static class Click {

		public static final String OPEN_URL = "open_url";
		public static final String RUN_COMMAND = "run_command";
		public static final String SUGGEST_COMMAND = "suggest_command";

	}

	public static class Hover {

		public static final String TOOLTIP = "show_text";

	}
}
