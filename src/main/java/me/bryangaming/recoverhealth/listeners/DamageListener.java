package me.bryangaming.recoverhealth.listeners;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;
import me.bryangaming.recoverhealth.manager.SenderManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener{

    private PluginService pluginService;

    private final FileManager configFile;
    private final SenderManager senderManager;

    public DamageListener(PluginService pluginService){
        this.pluginService = pluginService;

        this.configFile = pluginService.getFiles().getConfig();
        this.senderManager = pluginService.getSender();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event){

        if (event.getEntityType() != EntityType.PLAYER){
            return;
        }

        Player player = (Player) event.getEntity();

        if (player.getHealth() > event.getDamage()){
            return;
        }

        ItemStack itemStack = player.getInventory().getItemInMainHand();

        if (itemStack.getType() != Material.getMaterial(configFile.getString("item.id").toUpperCase())){
            return;
        }

        if (itemStack.getItemMeta() == null){
            return;
        }

        if (!itemStack.getItemMeta().getDisplayName()
                .replace("§", "&")
                .equalsIgnoreCase(configFile.getString("item.name"))){
            return;
        }

        event.setCancelled(true);
        player.getInventory().clear(player.getInventory().getHeldItemSlot());
        player.setHealth(player.getHealth() + configFile.getInt("options.add-health"));

        for (String actions : configFile.getStringList("event.actions")){
            if (actions.startsWith("[MESSAGE]")){
                senderManager.sendMessage(player, actions.substring(9));
                continue;
            }

            if (actions.startsWith("[COMMAND]")){
                player.performCommand(actions.substring(9));
                continue;
            }

            if (actions.startsWith("[CONSOLECMD]")){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), actions.substring(12));
                continue;
            }

            if (actions.startsWith("[LIGHTING]")){
                player.getWorld().strikeLightning(player.getLocation());
                continue;
            }

            if (actions.startsWith("[BROADCAST]")){
                Bukkit.broadcastMessage(actions.substring(11));
                continue;
            }

            if (actions.startsWith("[BROADCASTWORLD]")){
                player.getWorld().getPlayers().forEach(worldPlayer -> {
                    worldPlayer.sendMessage(actions.substring(16));
                });
                continue;
            }

            if (actions.startsWith("[SOUND]")){
                String[] soundPath = actions.substring(7).split(";");

                Sound sound;
                try {
                    sound = Sound.valueOf(soundPath[0]);
                }catch (IllegalArgumentException illegalArgumentException){
                    pluginService.getPlugin().getLogger().info("Error! The sound no exists: " + soundPath[0]);
                    continue;
                }
                player.playSound(player.getLocation(), sound, Float.parseFloat(soundPath[1]), Float.parseFloat(soundPath[2]));
                continue;
            }

            if (actions.startsWith("[BROADCASTSOUND]")) {
                String[] soundPath = actions.substring(7).split(";");

                Sound sound;

                try {
                    sound = Sound.valueOf(soundPath[0]);
                } catch (IllegalArgumentException illegalArgumentException) {
                    pluginService.getPlugin().getLogger().info("Error! The sound no exists: " + soundPath[0]);
                    continue;
                }
                Bukkit.getServer().getOnlinePlayers().forEach(onlinePlayer -> {
                    onlinePlayer.playSound(player.getLocation(), sound, Float.parseFloat(soundPath[1]), Float.parseFloat(soundPath[2]));
                });
            }
        }
    }
}
