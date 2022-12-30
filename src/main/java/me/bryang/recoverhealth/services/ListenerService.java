package me.bryang.recoverhealth.services;

import me.bryang.recoverhealth.PluginService;
import me.bryang.recoverhealth.listeners.DamageListener;
import org.bukkit.Bukkit;

public class ListenerService implements Service {

    private final PluginService pluginService;

    public ListenerService(PluginService pluginService) {
        this.pluginService = pluginService;
    }

    @Override
    public void init() {
        Bukkit.getPluginManager().registerEvents(new DamageListener(pluginService), pluginService.getPlugin());
        pluginService.getPlugin().getLogger().info("Events loaded!");
    }
}
