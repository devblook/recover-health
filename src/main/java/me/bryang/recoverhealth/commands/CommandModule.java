package me.bryang.recoverhealth.commands;

import me.fixeddev.commandflow.annotated.CommandClass;
import team.unnamed.inject.AbstractModule;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        multibind(CommandClass.class)
                .asSet()
                .to(RecoverHealthCommand.class)
                .singleton();
    }
}
