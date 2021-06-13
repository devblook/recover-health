package me.bryangaming.recoverhealth.loader;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.builder.CommandBuilder;
import me.bryangaming.recoverhealth.commands.RecoverHealthCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;

public class CommandsLoader implements Loader{

    private final PluginService pluginService;

    public CommandsLoader(PluginService pluginService){
        this.pluginService = pluginService;
        load();
    }

    @Override
    public void load() {
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
