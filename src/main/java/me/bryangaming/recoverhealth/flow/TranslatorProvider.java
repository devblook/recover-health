package me.bryangaming.recoverhealth.flow;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.bukkit.BukkitDefaultTranslationProvider;

import java.util.HashMap;
import java.util.Map;

public class TranslatorProvider extends BukkitDefaultTranslationProvider {

    private final PluginService pluginService;

    protected Map<String, String> translations;

    private final FileManager configFile;

    public TranslatorProvider(PluginService pluginService) {
        this.pluginService = pluginService;
        this.configFile = pluginService.getFiles().getConfig();
        translations = new HashMap<>();
        setup();
    }

    public void setup() {
        translations.put("command.subcommand.invalid", "1. The subcommand %s doesn't exist!");
        translations.put("command.no-permission", configFile.getString("error.no-perms"));
        translations.put("argument.no-more", "3. No more arguments were found, size: %s position: %s");
        translations.put("player.offline", "4. The player %s is offline!");
        translations.put("sender.unknown", "5. Unknown command sender!");
        translations.put("sender.only-player", configFile.getString("error.console"));
    }

    public String getTranslation(String key) {
        return translations.get(key);
    }

    @Override
    public String getTranslation(Namespace namespace, String key) {
        return getTranslation(key);
    }
}
