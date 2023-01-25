package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.RecoverHealth;
import me.bryang.recoverhealth.listeners.DamageListener;
import org.bukkit.Bukkit;
import team.unnamed.inject.InjectAll;

@InjectAll
public class ListenerService implements Service {


    private RecoverHealth plugin;
    private DamageListener damageListener;

    @Override
    public void init() {

        Bukkit.getPluginManager().registerEvents(damageListener, plugin);

        plugin.getLogger().info("Events loaded!");
    }
}
