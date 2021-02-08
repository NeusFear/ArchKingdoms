package com.brandonmdavis.kingdoms.modules.data.playerdata;

import java.util.ArrayList;

public class PlayerData {

	private int version;
	private String uuid;
	private ArrayList<String> usernames = new ArrayList<>();
	private int index;
	private String rankId;
	private String firstJoin;
	private String seen;
	private long onTime;
	private int afkLevel;
	private boolean online;

	public PlayerData() { }

	public PlayerData(int version, String uuid, ArrayList<String> usernames, int index, String rankId,
					  String firstJoin, String seen, long onTime, int afkLevel, boolean online) {
		this.version = version;
		this.uuid = uuid;
		this.usernames = usernames;
		this.index = index;
		this.rankId = rankId;
		this.firstJoin = firstJoin;
		this.seen = seen;
		this.onTime = onTime;
		this.afkLevel = afkLevel;
		this.online = online;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public ArrayList<String> getUsernames() {
		return usernames;
	}

	public String getMostRecentUsername() {
		return  usernames.get(usernames.size() - 1);
	}

	public void addUsername(String username) {
		usernames.add(username);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRankId() {
		return rankId;
	}

	public void setRankId(String rankId) {
		this.rankId = rankId;
	}

	public String getFirstJoin() {
		return firstJoin;
	}

	public void setFirstJoin(String firstJoin) {
		this.firstJoin = firstJoin;
	}

	public String getSeen() {
		return seen;
	}

	public void setSeen(String seen) {
		this.seen = seen;
	}

	public long getOnTime() {
		return onTime;
	}

	public void setOnTime(long onTime) {
		this.onTime = onTime;
	}

	public int getAfkLevel() {
		return afkLevel;
	}

	public void setAfkLevel(int afkLevel) {
		this.afkLevel = afkLevel;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
