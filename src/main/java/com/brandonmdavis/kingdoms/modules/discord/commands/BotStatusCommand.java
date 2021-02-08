package com.brandonmdavis.kingdoms.modules.discord.commands;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.brandonmdavis.kingdoms.modules.discord.BotArgs;
import com.brandonmdavis.kingdoms.modules.discord.BotCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class BotStatusCommand extends BotCommand {
    @Override
    public void process(BotArgs args) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Server Status");
        eb.setColor(Color.ORANGE);
        eb.setDescription("Gives current information about the server");
        eb.addField(new MessageEmbed.Field("Online", "yes", true));
        eb.addField(new MessageEmbed.Field("Tick Time", "" + Kingdoms.getServer().getTickTime(), true));
        eb.addField(new MessageEmbed.Field("Players", Kingdoms.getServer().getPlayerManager().getCurrentPlayerCount() + "/" +
                Kingdoms.getServer().getPlayerManager().getMaxPlayerCount(), true));
        args.getChannel().sendMessage(eb.build()).queue();
    }
}
