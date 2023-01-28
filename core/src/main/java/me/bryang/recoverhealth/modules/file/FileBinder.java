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

    @Provides
    public FileCreator getConfigFile(@FileAnnotation Path path){

        FileCreator fileCreator = new FileCreator(path, "config");

        try{
            fileCreator.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return fileCreator;
    }

    @Provides @Named("messages")
    public FileCreator getMessagesFile(@FileAnnotation Path path){

        FileCreator fileCreator = new FileCreator(path, "messages");

        try{
            fileCreator.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return fileCreator;
    }
}
