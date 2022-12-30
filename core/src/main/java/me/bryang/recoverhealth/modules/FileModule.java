package me.bryang.recoverhealth.modules;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.manager.FileManager;
import team.unnamed.inject.AbstractModule;

public class FileModule extends AbstractModule {

    private final RecoverHealth recoverHealth;

    public FileModule(RecoverHealth recoverHealth) {
        this.recoverHealth = recoverHealth;
    }

    @Override
    public void configure() {

        bind(FileManager.class)
                .toInstance(new FileManager(recoverHealth, "config.yml"));
        bind(FileManager.class)
                .named("messages")
                .toInstance(new FileManager(recoverHealth, "messages.yml"));
    }
}
