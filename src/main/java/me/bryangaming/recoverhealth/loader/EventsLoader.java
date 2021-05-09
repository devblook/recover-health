package me.bryangaming.recoverhealth.loader;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.listeners.DamageListener;
import org.bukkit.Bukkit;

public class EventsLoader implements Loader{

    private PluginService pluginService;

    public EventsLoader(PluginService pluginService){
        this.pluginService = pluginService;
        load();
    }

    @Override
    public void load() {
        Bukkit.getPluginManager().registerEvents(new DamageListener(pluginService), pluginService.getPlugin());
        pluginService.getPlugin().getLogger().info("Events loaded!");
    }
}
