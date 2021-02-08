package com.brandonmdavis.kingdoms.modules.permissions;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.brandonmdavis.api.messages.Colors;
import com.brandonmdavis.api.messages.Message;
import com.brandonmdavis.api.messages.MessageComponent;
import com.brandonmdavis.api.messages.ActionMessageComponent;

public class Ranks {

	public static final String PREFIX_PREFIX = "[";
	public static final String PREFIX_SUFFIX = "]";

	public static Rank GUEST;
	public static Rank MEMBER;
	public static Rank NOBLE;
	public static Rank HERO;
	public static Rank ROYAL;
	public static Rank TITAN;
	public static Rank LEGEND;
	public static Rank BUILDER;
	public static Rank TRIAL_MOD;
	public static Rank MODERATOR;
	public static Rank ADMINISTRATOR;

	public static Rank getRank(String rankName) {
		switch (rankName) {
			case "member":
				return Ranks.MEMBER;
			case "noble":
				return Ranks.NOBLE;
			case "hero":
				return Ranks.HERO;
			case "royal":
				return Ranks.ROYAL;
			case "titan":
				return Ranks.TITAN;
			case "legend":
				return Ranks.LEGEND;
			case "builder":
				return Ranks.BUILDER;
			case "trialmod":
				return Ranks.TRIAL_MOD;
			case "moderator":
				return Ranks.MODERATOR;
			case "administrator":
				return Ranks.ADMINISTRATOR;
			default:
				return Ranks.GUEST;
		}
	}

	public static void register() {
		GUEST = new Rank("guest", 0, "The default rank of any new player.", Kingdoms.URL + "/guest");
		GUEST.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Guest").color(Colors.DARK_GRAY)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, GUEST.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, GUEST.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		MEMBER = new Rank("member", 1, "The default rank of any player who has joined our website.", Kingdoms.URL + "/member");
		MEMBER.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Member").color(Colors.WHITE)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, MEMBER.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, MEMBER.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		NOBLE = new Rank("noble", 2, "A donation rank.", Kingdoms.URL + "/noble");
		NOBLE.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Noble").color(Colors.LIGHT_BLUE)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, NOBLE.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, NOBLE.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		HERO = new Rank("hero", 3, "A donation rank.", Kingdoms.URL + "/hero");
		HERO.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Hero").color(Colors.GREEN)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, HERO.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, HERO.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		ROYAL = new Rank("royal", 4, "A donation rank.", Kingdoms.URL + "/royal");
		ROYAL.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Royal").color(Colors.PURPLE)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, ROYAL.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, ROYAL.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		TITAN = new Rank("titan", 5, "A donation rank.", Kingdoms.URL + "/titan");
		TITAN.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Titan").color(Colors.RED)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, TITAN.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, TITAN.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		LEGEND = new Rank("legend", 6, "The highest donation rank.", Kingdoms.URL + "/legend");
		LEGEND.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.GRAY).build())
				.addComponent(MessageComponent.builder()
						.text("Legend").color(Colors.BLUE)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, LEGEND.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, LEGEND.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.GRAY).build()));

		BUILDER = new Rank("builder", 7, "These people bring the map to life.", Kingdoms.URL + "/builder");
		BUILDER.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.WHITE).build())
				.addComponent(MessageComponent.builder()
						.text("Builder").color(Colors.GOLD)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, BUILDER.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, BUILDER.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.WHITE).build()));

		TRIAL_MOD = new Rank("trialmod", 8, "These are staff members in training.", Kingdoms.URL + "/trial_moderator");
		TRIAL_MOD.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.WHITE).build())
				.addComponent(MessageComponent.builder()
						.text("TrialMod").color(Colors.YELLOW)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, TRIAL_MOD.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, TRIAL_MOD.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.WHITE).build()));

		MODERATOR = new Rank("moderator", 8, "These staff members manage the chat and keep the server free of rule-breakers.", Kingdoms.URL + "/moderator");
		MODERATOR.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.WHITE).build())
				.addComponent(MessageComponent.builder()
						.text("Mod").color(Colors.GOLD)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, MODERATOR.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, MODERATOR.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.WHITE).build()));

		ADMINISTRATOR = new Rank("administrator", 10, "Top of command, these people manage the server and it's inner workings.", Kingdoms.URL + "/administrator");
		ADMINISTRATOR.setPrefix(Message.builder()
				.addComponent(MessageComponent.builder().text(PREFIX_PREFIX).color(Colors.WHITE).build())
				.addComponent(MessageComponent.builder()
						.text("Admin").color(Colors.GOLD)
						.hoverEvent(ActionMessageComponent.Hover.TOOLTIP, ADMINISTRATOR.getDescription())
						.clickEvent(ActionMessageComponent.Click.OPEN_URL, ADMINISTRATOR.getLink())
						.build())
				.addComponent(MessageComponent.builder().text(PREFIX_SUFFIX).color(Colors.WHITE).build()));
	}

}
