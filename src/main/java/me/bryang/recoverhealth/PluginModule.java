package me.bryang.recoverhealth;

import me.bryang.recoverhealth.commands.RecoverHealthCommand;
import me.bryang.recoverhealth.listeners.DamageListener;
import me.bryang.recoverhealth.manager.FileManager;
import me.bryang.recoverhealth.services.ListenerService;
import me.bryang.recoverhealth.services.Service;
import me.bryang.recoverhealth.services.commands.CommandService;
import me.fixeddev.commandflow.annotated.CommandClass;
import org.bukkit.event.Listener;
import team.unnamed.inject.AbstractModule;

public class PluginModule extends AbstractModule {

    private final RecoverHealth recoverHealth;

    public PluginModule(RecoverHealth recoverHealth) {
        this.recoverHealth = recoverHealth;
    }

    @Override
    public void configure() {
        bind(RecoverHealth.class)
                .toInstance(recoverHealth);

        multibind(Service.class)
                .asSet()
                .to(CommandService.class)
                .to(ListenerService.class);

        bind(FileManager.class)
                .toInstance(new FileManager(recoverHealth, "config.yml"));
        bind(FileManager.class)
                .named("messages")
                .toInstance(new FileManager(recoverHealth, "messages.yml"));

        bind(RecoverHealthCommand.class)
                .singleton();

        bind(DamageListener.class)
                .singleton();
    }
}
