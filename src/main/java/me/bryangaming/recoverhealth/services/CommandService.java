package me.bryangaming.recoverhealth.services;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.builder.CommandBuilder;
import me.bryangaming.recoverhealth.commands.RecoverHealthCommand;
import org.bukkit.Bukkit;

public class CommandService implements Service {

    private final PluginService pluginService;

    public CommandService(PluginService pluginService){
        this.pluginService = pluginService;
    }

    @Override
    public void init() {
        registerCommands(
                CommandBuilder.get("recoverhealth", new RecoverHealthCommand(pluginService)));
        pluginService.getPlugin().getLogger().info("Commands loaded!");
    }

    public void registerCommands(CommandBuilder... commandBuilders){

        for (CommandBuilder commandClass : commandBuilders){
            Bukkit.getPluginCommand(commandClass.getCommandName()).setExecutor(commandClass.getCommandExecutor());
        }
    }

}
