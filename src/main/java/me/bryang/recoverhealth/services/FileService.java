package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.manager.FileManager;

public class FileService implements Service {

    private final RecoverHealth plugin;

    private FileManager configFile;
    private FileManager messagesFile;

    public FileService(RecoverHealth plugin) {
        this.plugin = plugin;
    }

    @Override
    public void init() {
        configFile = new FileManager(plugin, "config.yml");
        messagesFile = new FileManager(plugin, "messages.yml");
    }

    public FileManager getConfigFile() {
        return configFile;
    }

    public FileManager getMessagesFile() {
        return messagesFile;
    }
}
