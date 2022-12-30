package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.listeners.DamageListener;
import org.bukkit.Bukkit;
import team.unnamed.inject.InjectAll;

import javax.inject.Inject;

@InjectAll
public class ListenerService implements Service {


    private RecoverHealth recoverHealth;
    private DamageListener damageListener;

    @Override
    public void init() {
        Bukkit.getPluginManager().registerEvents(damageListener, recoverHealth);
        Bukkit.getLogger().info("[RecoverHealth] Events loaded!");
    }
}
