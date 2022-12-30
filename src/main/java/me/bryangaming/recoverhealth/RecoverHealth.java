package me.bryangaming.recoverhealth;

import org.bukkit.plugin.java.JavaPlugin;

public final class RecoverHealth extends JavaPlugin {

    @Override
    public void onEnable() {

        PluginService pluginService = new PluginService(this);

        getLogger().info("Plugin created by " + getDescription().getAuthors() + "");
        getLogger().info("You are using version " + getDescription().getVersion() + ".");
        getLogger().info("If you want support, you can join in:  https://discord.devblook.team/");

    }

    @Override
    public void onDisable() {
        getLogger().info("Thx for using this plugin <3. Goodbye!");
    }
}
