package me.bryangaming.recoverhealth.loader;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.commands.RecoverHealthCommand;
import me.bryangaming.recoverhealth.flow.TranslatorProvider;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.SimpleCommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

public class CommandsLoader implements Loader{

    private PluginService pluginService;

    private CommandManager commandManager;
    private AnnotatedCommandTreeBuilder builder;

    public CommandsLoader(PluginService pluginService){
        this.pluginService = pluginService;
        load();
    }

    @Override
    public void load() {
        createCommandManager();
        registerCommands(new RecoverHealthCommand(pluginService));
        pluginService.getPlugin().getLogger().info("Commands loaded!");
    }

    public void registerCommands(CommandClass... commandClasses){
        for (CommandClass commandClass : commandClasses){
            commandManager.registerCommands(builder.fromClass(commandClass));
        }
    }

    private void createCommandManager() {
        commandManager = new BukkitCommandManager("RecoverHealth");
        commandManager.getTranslator().setProvider(new TranslatorProvider(pluginService));

        PartInjector injector = PartInjector.create();
        injector.install(new DefaultsModule());
        injector.install(new BukkitModule());

        builder = new AnnotatedCommandTreeBuilderImpl(injector);

    }
}
