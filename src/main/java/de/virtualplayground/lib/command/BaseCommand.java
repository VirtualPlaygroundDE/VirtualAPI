package de.virtualplayground.lib.command;

import dev.jorel.commandapi.CommandAPICommand;

public abstract class BaseCommand {

    protected final CommandAPICommand command;
    private final String permission;

    public BaseCommand(String name) {
        this.command = new CommandAPICommand(name);
        this.permission = "vp.command." + name;
    }

    public void register() {
        command.register();
    }

    public String getPermission() {
        return this.permission;
    }
}