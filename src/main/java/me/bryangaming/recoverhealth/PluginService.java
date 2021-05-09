package me.bryangaming.recoverhealth;

import me.bryangaming.recoverhealth.loader.CommandsLoader;
import me.bryangaming.recoverhealth.loader.EventsLoader;
import me.bryangaming.recoverhealth.loader.FilesLoader;
import me.bryangaming.recoverhealth.manager.SenderManager;

public class PluginService {

    private final RecoverHealth recoverHealth;
    private FilesLoader fileLoader;

    private SenderManager senderManager;
    private CommandsLoader commandsLoader;
    private EventsLoader eventsLoader;

    public PluginService(RecoverHealth recoverHealth){
        this.recoverHealth = recoverHealth;
        setup();
    }

    public void setup(){
        fileLoader = new FilesLoader(this);
        senderManager = new SenderManager(this);
        commandsLoader = new CommandsLoader(this);
        eventsLoader = new EventsLoader(this);
    }

    public RecoverHealth getPlugin() {
        return recoverHealth;
    }

    public FilesLoader getFiles(){
        return fileLoader;
    }

    public SenderManager getSender(){
        return senderManager;
    }

}
