package me.bryang.recoverhealth.services.commands;

import me.bryang.recoverhealth.commands.RecoverHealthCommand;
import me.bryang.recoverhealth.services.Service;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

public class CommandService implements Service {

    @Override
    public void init() {

        BukkitCommandManager commandManager = new BukkitCommandManager("recover-health");
        commandManager.getTranslator().setProvider(new CommandTranslator());

        PartInjector partInjector = PartInjector.create();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandTreeBuilder builder = new AnnotatedCommandTreeBuilderImpl(partInjector);

        commandManager.registerCommands(builder.fromClass(new RecoverHealthCommand()));
    }

}
