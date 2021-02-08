package com.brandonmdavis.kingdoms.modules.data.serverdata;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.util.concurrent.CompletableFuture;

public class ServerDataHandler {

	public static ServerDataHandler serverDataHandler;
	private ServerData serverData;
	private static final File SERVER_DATA_DIRECTORY = new File(FabricLoader.getInstance().getConfigDirectory(), Kingdoms.MOD_ID + File.separator + "serverdata");

	public ServerDataHandler() {
		serverDataHandler = this;
		createServerDataDirectory();
		loadServerData();
	}

	private void createServerDataDirectory() {
		if (!SERVER_DATA_DIRECTORY.exists()) {
			SERVER_DATA_DIRECTORY.mkdirs();
		}
	}

	private File getServerDataFile() {
		return new File(SERVER_DATA_DIRECTORY.toString() + File.separator + "serverdata.json");
	}

	private void loadServerData() {
		File file = getServerDataFile();
		try {
			if (file.exists()) {
				Gson gson = new Gson();
				BufferedReader br = new BufferedReader(new FileReader(file));
				this.serverData = gson.fromJson(br, ServerData.class);
				saveServerData(false);
			} else {
				createNewServerData();
				saveServerData(false);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void createNewServerData() {
		this.serverData = new ServerData();
	}

	private void writeServerData() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String jsonString = gson.toJson(this.serverData);
		try(FileWriter fileWriter = new FileWriter(getServerDataFile())) {
			fileWriter.write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveServerData() {
		saveServerData(true);
	}

	public void saveServerData(boolean useAsync) {
		if (!useAsync) {
			writeServerData();
		} else {
			CompletableFuture<Void> thread = CompletableFuture.runAsync(this::writeServerData);
		}
	}

	public static ServerDataHandler getServerDataHandler() {
		return serverDataHandler;
	}

	public ServerData getServerData() {
		return serverData;
	}
}
