package com.brandonmdavis.kingdoms.modules.data.serverdata;

import com.brandonmdavis.kingdoms.modules.kingdom.KingdomHome;
import com.brandonmdavis.kingdoms.modules.permissions.Rank;

import java.util.HashMap;

public class ServerData {

	private HashMap<Rank, KingdomHome> homes = new HashMap<>();

	public ServerData() {

	}

	public ServerData(HashMap<Rank, KingdomHome> homes) {
		this.homes = homes;
	}

	public void setHome(Rank rank, KingdomHome home) {
		homes.get(rank).update(home);
	}

	public KingdomHome getWarp(Rank rank) {
		if (homes.containsKey(rank)) {
			return homes.get(rank);
		} else {
			if (rank.hasChildRank() && homes.containsKey(rank.getChildRank())) {
				return homes.get(rank.getChildRank());
			}
			return null;
		}
	}
}
