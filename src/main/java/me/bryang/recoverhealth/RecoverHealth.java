package me.bryang.recoverhealth;

import me.bryang.recoverhealth.services.Service;
import org.bukkit.plugin.java.JavaPlugin;
import team.unnamed.inject.Injector;

import javax.inject.Inject;
import java.util.Set;

public final class RecoverHealth extends JavaPlugin {

    @Inject
    private Set<Service> pluginServices;

    @Override
    public void onEnable() {

        Injector injector = Injector.create(new PluginModule(this));
        injector.injectMembers(this);

        pluginServices.forEach(Service::init);

        getLogger().info("Plugin created by " + getDescription().getAuthors() + "");
        getLogger().info("You are using version " + getDescription().getVersion() + ".");
        getLogger().info("If you want support, you can join in: https://discord.devblook.team/");

    }

    @Override
    public void onDisable() {
        getLogger().info("Thx for using this plugin <3. Goodbye!");
    }
}
