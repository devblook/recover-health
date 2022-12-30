package me.bryang.recoverhealth;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.manager.FileManager;
import me.bryang.recoverhealth.services.ListenerService;
import me.bryang.recoverhealth.services.Service;
import me.bryang.recoverhealth.services.commands.CommandService;
import team.unnamed.inject.Binder;
import team.unnamed.inject.Module;

public class PluginModule implements Module {

    private final RecoverHealth recoverHealth;

    public PluginModule(RecoverHealth recoverHealth){
        this.recoverHealth = recoverHealth;
    }

    @Override
    public void configure(Binder binder) {

        binder.bind(RecoverHealth.class)
                        .toInstance(recoverHealth);

        binder.multibind(Service.class)
                .asSet()
                .to(CommandService.class)
                .to(ListenerService.class);

        binder.bind(FileManager.class)
                .toInstance(new FileManager(recoverHealth, "config.yml"));
        binder.bind(FileManager.class).named("messages").
                toInstance(new FileManager(recoverHealth, "messages.yml"));


    }
}
