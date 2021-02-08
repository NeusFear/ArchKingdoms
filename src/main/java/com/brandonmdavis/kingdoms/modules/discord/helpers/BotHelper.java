package com.brandonmdavis.kingdoms.modules.discord.helpers;

import com.brandonmdavis.api.utilities.Time;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;

public class BotHelper {

    public static MessageEmbed makeEmbed(Color color, String title, String footer, String description) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(color);
        eb.setTitle(title);
        eb.setFooter(footer, null);
        eb.setDescription(description);

        return eb.build();
    }

    public static MessageEmbed makePlayerReportEmbed(String reporter, String reportee, String reason) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(255, 72, 56));
        eb.setTitle("New Player Report");
        eb.setFooter("Time reported: " + Time.getNow().toString(), null);
        eb.setDescription("A player has been reported.");
        eb.addField("Reporter", reporter, true);
        eb.addField("Suspect", reportee, true);
        eb.addField("Reason", reason, false);

        return eb.build();
    }

    public static MessageEmbed makeBugReportEmbed(String reporter, String reason) {
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(new Color(255, 72, 56));
        eb.setTitle("New Bug Report");
        eb.setFooter("Time reported: " + Time.getNow().toString(), null);
        eb.setDescription("A new bug report has been filed.");
        eb.addField("Reporter", reporter, true);
        eb.addField("Discription of bug", reason, false);

        return eb.build();
    }
}
