package me.bryangaming.recoverhealth.services;

import me.bryangaming.recoverhealth.RecoverHealth;
import me.bryangaming.recoverhealth.manager.FileManager;

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
