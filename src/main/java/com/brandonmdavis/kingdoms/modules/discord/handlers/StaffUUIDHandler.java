package com.brandonmdavis.kingdoms.modules.discord.handlers;

import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;

public class StaffUUIDHandler {

    public static String getUUIDFromDiscordID(String id) {
        switch (id) {
            case "204020433920458752": return PlayerDataHandler.getPlayerDataHandler().getPlayerDataFromUsername("NeusFear").getUuid();
            case "106551183228805120": return PlayerDataHandler.getPlayerDataHandler().getPlayerDataFromUsername("TonyMaster21").getUuid();
            case "83761733558075392": return PlayerDataHandler.getPlayerDataHandler().getPlayerDataFromUsername("Jhwx").getUuid();
            default: return "none";
        }
    }

}
