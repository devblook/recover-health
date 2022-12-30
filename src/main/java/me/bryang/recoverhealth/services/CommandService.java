package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.PluginService;
import me.bryang.recoverhealth.builder.CommandBuilder;
import me.bryang.recoverhealth.commands.RecoverHealthCommand;
import org.bukkit.Bukkit;

public class CommandService implements Service {

    private final PluginService pluginService;

    public CommandService(PluginService pluginService) {
        this.pluginService = pluginService;
    }

    @Override
    public void init() {
        registerCommands(
                CommandBuilder.get("recoverhealth", new RecoverHealthCommand(pluginService)));
        pluginService.getPlugin().getLogger().info("Commands loaded!");
    }

    public void registerCommands(CommandBuilder... commandBuilders) {

        for (CommandBuilder commandClass : commandBuilders) {
            Bukkit.getPluginCommand(commandClass.getCommandName()).setExecutor(commandClass.getCommandExecutor());
        }
    }

}
