package com.brandonmdavis.kingdoms.modules.data.playerdata;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.brandonmdavis.kingdoms.modules.permissions.Ranks;
import com.brandonmdavis.api.utilities.Time;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PlayerDataHandler {

	private static PlayerDataHandler playerDataHandler;
	private ArrayList<PlayerData> playerDataStorage = new ArrayList<>();
	private static final int VERSION = 2;
	private static final File PLAYERDATA_DIRECTORY = new File(FabricLoader.getInstance().getConfigDirectory(), Kingdoms.MOD_ID + File.separator + "playerdata");

	public PlayerDataHandler() {
		playerDataHandler = this;
		createPlayerdataDirectory();
		loadAllPlayerData();
	}

	private void createPlayerdataDirectory() {
		if (!PLAYERDATA_DIRECTORY.exists()) {
			PLAYERDATA_DIRECTORY.mkdirs();
		}
	}

	public void createNewPlayerData(String uuid, String username) {
		PlayerData playerData = new PlayerData();
		playerData.setVersion(VERSION);
		playerData.setUuid(uuid);
		playerData.addUsername(username);
		playerData.setIndex(playerDataStorage.size());
		playerData.setRankId(Ranks.WANDERER.getName());
		playerData.setFirstJoin(Time.getNow().toString());
		playerData.setSeen(Time.getNow().toString());
		playerData.setOnTime(0);
		playerData.setAfkLevel(0);
		playerData.setOnline(false);

		addPlayerData(playerData);
		savePlayerData(playerData, false);
	}

	public PlayerData getPlayerDataFromUsername(String username) {
		for (PlayerData playerdata : playerDataStorage) {
			if (playerdata.getMostRecentUsername().equalsIgnoreCase(username)) {
				return playerdata;
			}
			for (String name : playerdata.getUsernames()) {
				if (name.equalsIgnoreCase(username)) {
					return playerdata;
				}
			}
		}
		return null;
	}

	public PlayerData getPlayerData(String uuid) {
		for (PlayerData playerData : playerDataStorage) {
			if (playerData.getUuid().equals(uuid)) return playerData;
		}
		return null;
	}

	public static boolean hasJoined(String uuid) {
		return getPlayerDataHandler().getPlayerData(uuid) != null;
	}

	public static boolean hasUserJoined(String username) {
		return getPlayerDataHandler().getPlayerDataFromUsername(username) != null;
	}

	private File getPlayerDataFile(String uuid) {
		return new File(PLAYERDATA_DIRECTORY.toString() + File.separator + uuid + ".json");
	}

	private void writePlayerData(PlayerData playerdata) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(playerdata);
		try(FileWriter fileWriter = new FileWriter(getPlayerDataFile(playerdata.getUuid()))) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void savePlayerData(PlayerData playerdata) {
		savePlayerData(playerdata, true);
	}

	private void savePlayerData(PlayerData playerData, boolean useAsync) {
		if (!useAsync) {
			writePlayerData(playerData);
		} else {
			CompletableFuture<Void> thread = CompletableFuture.runAsync(() -> {
				writePlayerData(playerData);
			});
		}
	}

	private static void addPlayerData(PlayerData playerData) {
		getPlayerDataHandler().playerDataStorage.add(playerData);
	}

	private void loadPlayerData(String uuid) {
		File file = getPlayerDataFile(uuid);
		try {
			if (file.exists()) {
				Gson gson = new Gson();
				BufferedReader br = new BufferedReader(new FileReader(file));
				PlayerData playerData = gson.fromJson(br, PlayerData.class);
				testAndUpdatePlayerdata(playerData);
				addPlayerData(playerData);
			} else {
				return;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void testAndUpdatePlayerdata(PlayerData playerData) {
		if (playerData.getVersion() < VERSION) {
			if (playerData.getVersion() == 1) {
				playerData.setVersion(VERSION);
			}
			if (playerData.getVersion() == VERSION) {
				getPlayerDataHandler().savePlayerData(playerData);
				return;
			}
			Kingdoms.log(Level.ERROR, "Error updating playerdata for " + playerData.getUuid());
		}
	}

	private void loadAllPlayerData() {
		File[] playerDataFiles = new File(PLAYERDATA_DIRECTORY + File.separator).listFiles();
		if (playerDataFiles == null) {
			Kingdoms.log(Level.INFO, "No player data to load.");
			return;
		}
		for (int i = 0; i < playerDataFiles.length; i++) {
			if (playerDataFiles[i].isFile()) {
				loadPlayerData(playerDataFiles[i].getName().replace(".json", ""));
			}
		}
	}

	private static void saveAllPlayerData(boolean useAsync) {
		for (PlayerData playerData : getPlayerDataHandler().playerDataStorage) {
			getPlayerDataHandler().savePlayerData(playerData, useAsync);
		}
	}

	public static void backupPlayerData() {
		saveAllPlayerData(true);
	}

	public static void backupPlayerData(boolean useAsync) {
		Kingdoms.log(Level.INFO, "Saving all playerdata...");
		saveAllPlayerData(useAsync);
		Kingdoms.log(Level.INFO, "Backup complete.");
	}

	public static PlayerDataHandler getPlayerDataHandler() {
		return playerDataHandler;
	}
}
