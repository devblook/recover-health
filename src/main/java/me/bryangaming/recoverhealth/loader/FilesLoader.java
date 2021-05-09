package me.bryangaming.recoverhealth.loader;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;

public class FilesLoader implements Loader{

    private PluginService pluginService;
    private FileManager configFile;

    public FilesLoader(PluginService pluginService){
        this.pluginService = pluginService;
        load();
    }

    @Override
    public void load() {
        configFile = new FileManager(pluginService.getPlugin(), "config.yml");
    }

    public FileManager getConfig(){
        return configFile;
    }
}
