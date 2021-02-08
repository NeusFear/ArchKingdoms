package com.brandonmdavis.kingdoms.modules.discord.commands;

import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerData;
import com.brandonmdavis.kingdoms.modules.data.playerdata.PlayerDataHandler;
import com.brandonmdavis.kingdoms.modules.discord.BotArgs;
import com.brandonmdavis.kingdoms.modules.discord.BotCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class BotPlayerDataCommand extends BotCommand {
    @Override
    public void process(BotArgs args) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.ORANGE);
        if (args.getMessage().getContentRaw().contains(" ")) {
            String player = args.getMessage().getContentRaw().split(" ")[1];
            if (player != null && PlayerDataHandler.hasUserJoined(player)) {
                PlayerData playerData = PlayerDataHandler.getPlayerDataHandler().getPlayerDataFromUsername(player);

                eb.setTitle(playerData.getMostRecentUsername() + "'s PlayerData");
                eb.addField(new MessageEmbed.Field("Joined", playerData.getFirstJoin(), true));
                eb.addBlankField(true);
                eb.setFooter("UUID: " + playerData.getUuid(), null);

            } else {
                eb.setTitle("That player has never joined before.");
            }
        } else {
            eb.setDescription("You must specify a player.");
        }
        args.getChannel().sendMessage(eb.build()).queue();
    }
}
