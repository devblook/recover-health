package me.bryang.recoverhealth.services.commands;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.services.Service;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;
import org.bukkit.Bukkit;

import javax.inject.Inject;
import java.util.Set;

public class CommandService implements Service {

    @Inject
    private RecoverHealth plugin;
    @Inject
    private Set<CommandClass> commands;

    @Override
    public void init() {
        BukkitCommandManager commandManager = new BukkitCommandManager("recoverhealth");
        commandManager.getTranslator().setProvider(new CommandTranslator());

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        for (CommandClass command : commands) {
            commandManager.registerCommands(builder.fromClass(command));
        }
        plugin.getLogger().info("[RecoverHealth] Commands loaded!");
    }
}
