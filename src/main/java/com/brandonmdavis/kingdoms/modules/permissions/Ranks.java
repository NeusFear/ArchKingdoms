package com.brandonmdavis.kingdoms.modules.permissions;

import com.brandonmdavis.api.messages.Colors;

public class Ranks {

	public static Rank WANDERER;
	public static Rank BARON;
	public static Rank PUNK;
	public static Rank BELLUM;
	public static Rank BARON_LEADER;
	public static Rank PUNK_LEADER;
	public static Rank BELLUM_LEADER;
	public static Rank DEITY_OF_DESIGN;

	public static Rank getRank(String rankName) {
		switch (rankName) {
			case "baron":
				return Ranks.BARON;
			case "punk":
				return Ranks.PUNK;
			case "bellum":
				return Ranks.BELLUM;
			case "baron_leader":
				return Ranks.BARON_LEADER;
			case "punk_leader":
				return Ranks.PUNK_LEADER;
			case "bellum_leader":
				return Ranks.BELLUM_LEADER;
			case "deity_of_design":
				return Ranks.DEITY_OF_DESIGN;
			default:
				return Ranks.WANDERER;
		}
	}

	public static void register() {
		WANDERER = new Rank("wanderer", 0, "A Wanderer has no loyalty to any Kingdom.", Colors.DARK_GRAY, Colors.GRAY);
		BARON = new Rank("baron", 1, "Has declared loyalty to the Baron Leader.", Colors.LIME, Colors.WHITE);
		PUNK = new Rank("punk", 1, "Has declared loyalty to the Punk Leader.", Colors.LIGHT_BLUE, Colors.WHITE);
		BELLUM = new Rank("bellum", 1, "Has declared loyalty to the Bellum Leader.", Colors.CYAN, Colors.WHITE);
		BARON_LEADER = new Rank("baron_leader", 2, "Leads the Baron Kingdom.", Colors.LIME, Colors.GOLD, BARON);
		PUNK_LEADER = new Rank("punk_leader", 2, "Leads the Punk Kingdom.", Colors.LIGHT_BLUE, Colors.GOLD, PUNK);
		BELLUM_LEADER = new Rank("bellum_leader", 2, "Leads the Bellum Kingdom.", Colors.CYAN, Colors.GOLD, BELLUM);
		DEITY_OF_DESIGN = new Rank("deity_of_design", 3, "The default rank of any new player.", Colors.GOLD, Colors.DARK_GRAY);
	}

}
