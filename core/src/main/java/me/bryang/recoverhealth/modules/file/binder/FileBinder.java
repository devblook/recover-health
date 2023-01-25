package me.bryang.recoverhealth.modules.file.binder;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.modules.file.FileAnnotation;
import org.spongepowered.configurate.ConfigurateException;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.Provides;

import javax.inject.Singleton;
import java.nio.file.Path;
import java.util.Iterator;

public class FileBinder extends AbstractModule {

    @Provides @Singleton
    public void createFiles(@FileAnnotation Path path){

        for (Path value : path) {
            String newPath = value.toString();

            try {
                FileBinderManager.loadFile(path, newPath);

            } catch (ConfigurateException exception) {
                exception.printStackTrace();
            }
        }
    }
}
