package me.bryangaming.recoverhealth.commands;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;
import me.bryangaming.recoverhealth.manager.SenderManager;
import me.bryangaming.recoverhealth.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@Command(names = {"recoverhealth", "rh"})
public class RecoverHealthCommand implements CommandClass{

    private final PluginService pluginService;

    private final FileManager configFile;
    private final SenderManager senderManager;

    public RecoverHealthCommand(PluginService pluginService){
        this.pluginService = pluginService;

        this.configFile = pluginService.getFiles().getConfig();
        this.senderManager = pluginService.getSender();
    }

    @Command(names = "")
    public boolean mainSubCommand(@Sender Player player){
        senderManager.sendMessage(player, configFile.getStringList("command.help"));
        return true;
    }

    @Command(names = "give", permission = "recoverhealth.give")
    public boolean onGiveSubCommand(@Sender Player player, @OptArg("1") String qnty){

        if (!StringUtils.isNumeric(qnty)){
            senderManager.sendMessage(player, configFile.getString("error.unknown-number"));
            return true;
        }

        Material material = Material.getMaterial(configFile.getString("item.id").toUpperCase());

        if (material == null){
            pluginService.getPlugin().getLogger().info("Unknown material!");
            return true;
        }

        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(TextUtils.colorize(configFile.getString("item.name")));

        if (!configFile.getStringList("item.lore").isEmpty()) {
            List<String> lore = configFile.getStringList("item.lore");
            lore.replaceAll(TextUtils::colorize);
            itemMeta.setLore(lore);
        }

        itemStack.setItemMeta(itemMeta);

        Inventory inventory = player.getInventory();
        int times = 0;

        while (times != Integer.parseInt(qnty)){
            inventory.addItem(itemStack);
            times++;
        }
        player.updateInventory();

        senderManager.sendMessage(player, configFile.getString("command.give")
                .replace("%qnty%", qnty));
        return true;
    }

    @Command(names = "reload", permission = "recoverhealth.reload")
    public boolean reloadSubCommand(@Sender Player player){

        pluginService.getFiles().getConfig().reload();
        senderManager.sendMessage(player, configFile.getString("command.reload"));
        return true;
    }

}
