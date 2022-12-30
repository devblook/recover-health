package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.listeners.DamageListener;
import org.bukkit.Bukkit;

import javax.inject.Inject;

public class ListenerService implements Service {

    @Inject
    private RecoverHealth recoverHealth;

    @Override
    public void init() {
        Bukkit.getPluginManager().registerEvents(new DamageListener(), recoverHealth);
        Bukkit.getLogger().info("[RecoverHealth] Events loaded!");
    }
}
