package me.bryangaming.recoverhealth.builder;

import org.bukkit.command.CommandExecutor;

public class CommandBuilder {

    private final String commandName;
    private final CommandExecutor commandExecutor;

    private CommandBuilder(String commandName, CommandExecutor commandExecutor) {
        this.commandName = commandName;
        this.commandExecutor = commandExecutor;
    }

    public static CommandBuilder get(String commandName, CommandExecutor commandExecutor) {
        return new CommandBuilder(commandName, commandExecutor);
    }

    public String getCommandName() {
        return commandName;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }
}
