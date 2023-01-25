package me.bryang.recoverhealth.modules.file;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.modules.file.binder.FileBinder;
import team.unnamed.inject.AbstractModule;

import javax.inject.Inject;
import java.nio.file.Path;

public class FileModule extends AbstractModule {

    private final RecoverHealth plugin;

    public FileModule(RecoverHealth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void configure() {

        bind(Path.class)
                .markedWith(FileAnnotation.class)
                        .toInstance(Path.of(plugin.getDataFolder().getPath()));

        install(new FileBinder());
        plugin.getLogger().info("Files loaded!");
    }
}
