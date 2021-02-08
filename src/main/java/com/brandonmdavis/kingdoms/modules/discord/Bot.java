package com.brandonmdavis.kingdoms.modules.discord;

import com.brandonmdavis.kingdoms.Kingdoms;
import com.brandonmdavis.kingdoms.modules.discord.chatlink.DiscordChatLink;
import com.brandonmdavis.kingdoms.modules.discord.handlers.BotCommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.logging.log4j.Level;

import javax.security.auth.login.LoginException;

public class Bot extends ListenerAdapter {

    private static final String TOKEN = "MzQzMDU4MDU4MDg1NDAwNTc3.Xp4ucA.PP6oBRI3EvTP1c2SU3zVEEGeONk";
    private static final String LINK_CHANNEL = "569279826658197526";
    private static JDA jda;

    public static void startBot() {
        Kingdoms.log(Level.INFO, "Loading Discord Bot.");
        try {
            jda = JDABuilder.createDefault(TOKEN)
                    .addEventListeners(new Bot())
                    .build();
            jda.awaitReady();
            new BotCommandHandler();
            Kingdoms.log(Level.INFO, "Finished loading Discord Bot.");
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static JDA getJda() {
        return jda;
    }

    public static String getLinkChannel() {
        return LINK_CHANNEL;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().startsWith("!")) {
            BotCommandRegistry.execute(event.getMessage().getContentRaw(),
                    new BotArgs(
                            event.getMessage(),
                            event.getAuthor(),
                            event.getGuild(),
                            event.getChannel(),
                            event.getMember()
                    ));
            return;
        }
        if (event.getChannel().getId().equals(LINK_CHANNEL) && !event.getAuthor().isBot()) {
            DiscordChatLink.sendMessage(
                    new BotArgs(
                    event.getMessage(),
                    event.getAuthor(),
                    event.getGuild(),
                    event.getChannel(),
                    event.getMember()
            ));
        }
    }
}

