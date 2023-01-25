package me.bryang.recoverhealth.modules.file.binder;

import me.bryang.recoverhealth.FileCreator;
import org.spongepowered.configurate.ConfigurateException;

import javax.naming.ConfigurationException;
import java.nio.file.Path;

public class FileBinderManager {

    public static FileCreator loadFile(Path path, String fileName) throws ConfigurateException {

        FileCreator fileCreator  = new FileCreator();
        fileCreator.start(path, fileName);

        return fileCreator;
    }

}
