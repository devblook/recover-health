package me.bryang.recoverhealth.services.commands;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.commands.RecoverHealthCommand;
import me.bryang.recoverhealth.services.Service;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

import javax.inject.Inject;

public class CommandService implements Service {

    @Inject
    private RecoverHealth plugin;
    @Inject
    private RecoverHealthCommand recoverHealthCommand;

    @Override
    public void init() {

        BukkitCommandManager commandManager = new BukkitCommandManager("recoverhealth");
        commandManager.getTranslator().setProvider(new CommandTranslator());

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);
        commandManager.registerCommands(builder.fromClass(recoverHealthCommand));
        plugin.getLogger().info("Commands loaded!");
    }
}
