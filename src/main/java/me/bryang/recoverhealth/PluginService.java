package me.bryang.recoverhealth;

import me.bryang.recoverhealth.services.CommandService;
import me.bryang.recoverhealth.services.FileService;
import me.bryang.recoverhealth.services.ListenerService;
import me.bryang.recoverhealth.services.Service;

public class PluginService {

    private final RecoverHealth recoverHealth;
    private FileService fileServices;

    public PluginService(RecoverHealth recoverHealth) {
        this.recoverHealth = recoverHealth;
        setup();
    }

    public void setup() {

        fileServices = new FileService(recoverHealth);

        initServices(
                fileServices,
                new CommandService(this),
                new ListenerService(this));
    }

    public void initServices(Service... services) {
        for (Service service : services) {
            service.init();
        }
    }

    public RecoverHealth getPlugin() {
        return recoverHealth;
    }

    public FileService getFiles() {
        return fileServices;
    }


}
