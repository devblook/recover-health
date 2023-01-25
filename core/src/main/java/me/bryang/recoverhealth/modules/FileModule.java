package me.bryang.recoverhealth.modules;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.RecoverHealth;
import team.unnamed.inject.AbstractModule;

import javax.inject.Inject;

public class FileModule extends AbstractModule {

    private final RecoverHealth plugin;

    public FileModule(RecoverHealth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void configure() {

        bind(FileCreator.class)
                .toInstance(new FileCreator(plugin,"config"));
        bind(FileCreator.class)
                .named("messages")
                .toInstance(new FileCreator(plugin, "messages"));

        plugin.getLogger().info("Files loaded!");
    }
}
