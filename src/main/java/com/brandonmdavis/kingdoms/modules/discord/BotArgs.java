package com.brandonmdavis.kingdoms.modules.discord;

import net.dv8tion.jda.api.entities.*;

public class BotArgs {

    private Message message;
    private User user;
    private Guild guild;
    private MessageChannel channel;
    private Member member;

    public BotArgs(Message message, User author, Guild guild, MessageChannel channel, Member member) {
        this.message = message;
        this.user = author;
        this.guild = guild;
        this.channel = channel;
        this.member = member;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getAuthor() {
        return user;
    }

    public void setAuthor(User author) {
        this.user = author;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
