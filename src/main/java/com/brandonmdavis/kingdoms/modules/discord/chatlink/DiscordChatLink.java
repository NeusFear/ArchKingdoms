package com.brandonmdavis.kingdoms.modules.discord.chatlink;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.brandonmdavis.kingdoms.modules.discord.Bot;
import com.brandonmdavis.kingdoms.modules.discord.BotArgs;
import com.brandonmdavis.kingdoms.modules.messenger.Messenger;
import net.dv8tion.jda.api.EmbedBuilder;
import net.minecraft.network.MessageType;

import java.awt.*;

import static net.minecraft.util.Util.NIL_UUID;

public class DiscordChatLink {

    public static void sendMessage(BotArgs args) {
        args.getMessage().delete().queue();
        sendToDiscord(args.getAuthor().getName(), args.getMessage().getContentRaw(), true);
        Kingdoms.getServer().getPlayerManager().broadcastChatMessage(Messenger.fromDiscord(args.getAuthor().getAsTag(), args.getMessage().getContentRaw()), MessageType.CHAT, NIL_UUID);
    }

    public static void sendStatusMessage(boolean positive, String message) {
        EmbedBuilder eb = new EmbedBuilder();
        if (positive) {
            eb.setColor(Color.GREEN);
        } else {
            eb.setColor(Color.RED);
        }
        eb.setDescription(message);
        Bot.getJda().getTextChannelById(Bot.getLinkChannel()).sendMessage(eb.build()).queue();
    }

    public static void sendToDiscord(String name, String message, boolean fromDiscord) {
        EmbedBuilder eb = new EmbedBuilder();
        if (fromDiscord) {
            eb.setColor(Color.MAGENTA);
        } else {
            eb.setColor(Color.ORANGE);
        }
        eb.setDescription(name + ": " + message);
        Bot.getJda().getTextChannelById(Bot.getLinkChannel()).sendMessage(eb.build()).queue();
    }
}
