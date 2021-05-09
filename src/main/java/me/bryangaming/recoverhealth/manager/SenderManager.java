package me.bryangaming.recoverhealth.manager;

import me.bryangaming.recoverhealth.PluginService;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;

public class SenderManager {

    private PluginService pluginService;

    public SenderManager(PluginService pluginService){
        this.pluginService = pluginService;
    }

    public void sendMessage(Player player, String message){
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void sendMessage(Player player, List<String> messages){
        for (String message : messages) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }
    }
}
