package me.bryang.recoverhealth.modules.file;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.modules.file.FileAnnotation;
import org.spongepowered.configurate.ConfigurateException;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Named;
import javax.inject.Singleton;
import java.nio.file.Path;
import java.util.Iterator;

public class FileBinder extends AbstractModule {

    @Provides @Singleton
    public FileCreator getConfigFile(@FileAnnotation Path path){
        return new FileCreator(path, "config");
    }

    @Provides @Singleton @Named("messages")
    public FileCreator getMessagesFile(@FileAnnotation Path path){

        return new FileCreator(path, "messages");
    }
}
