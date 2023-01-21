package me.bryang.recoverhealth.modules;

import com.sun.tools.javac.Main;
import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.actions.Action;
import me.bryang.recoverhealth.commands.RecoverHealthCommand;
import me.bryang.recoverhealth.listeners.DamageListener;
import org.bukkit.Server;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.key.TypeReference;

import java.util.*;

public class MainModule extends AbstractModule {

    private final RecoverHealth recoverHealth;

    public MainModule(RecoverHealth recoverHealth) {
        this.recoverHealth = recoverHealth;
    }

    @Override
    public void configure() {
        bind(RecoverHealth.class)
                .toInstance(recoverHealth);

        install(new FileModule(recoverHealth));
        install(new ServiceModule());
        bind(new TypeReference<LinkedList<Action>>(){})
                .toInstance(new LinkedList<>());

        bind(RecoverHealthCommand.class)
                .singleton();
        bind(DamageListener.class)
                .singleton();
    }
}
