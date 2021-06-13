package me.bryangaming.recoverhealth.builder;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import javax.print.DocFlavor;

public class CommandBuilder {

    private final String commandName;
    private final CommandExecutor commandExecutor;

    public static CommandBuilder get(String commandName, CommandExecutor commandExecutor){
        return new CommandBuilder(commandName, commandExecutor);
    }

    private CommandBuilder(String commandName, CommandExecutor commandExecutor){
        this.commandName = commandName;
        this.commandExecutor = commandExecutor;
    }

    public String getCommandName(){
        return commandName;
    }

    public CommandExecutor getCommandExecutor(){
        return commandExecutor;
    }
}
