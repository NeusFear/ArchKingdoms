package com.brandonmdavis.kingdoms.modules.discord;

public class BotCommandExecutable {

    private String alias;
    private BotCommand command;

    public BotCommandExecutable(String alias, BotCommand command) {
        this.alias = alias;
        this.command = command;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public BotCommand getCommand() {
        return command;
    }

    public void setCommand(BotCommand command) {
        this.command = command;
    }
}
