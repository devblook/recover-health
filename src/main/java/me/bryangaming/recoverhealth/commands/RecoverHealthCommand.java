package me.bryangaming.recoverhealth.commands;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;
import me.bryangaming.recoverhealth.utils.TextUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


import java.util.List;

public class RecoverHealthCommand implements CommandExecutor {

    private final PluginService pluginService;

    private final FileManager configFile;
    private final FileManager messagesFile;

    public RecoverHealthCommand(PluginService pluginService){
        this.pluginService = pluginService;

        this.configFile = pluginService.getFiles().getConfigFile();
        this.messagesFile = pluginService.getFiles().getMessagesFile();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

            return true;
        }


        if (args.length < 1){
            return true;
        }

        switch (args[0]) {
            case "give":


                if (!sender.hasPermission("recoverhealth.give")){
                    sender.sendMessage(messagesFile.getString("error.no-permissions"));
                    return true;
                }

                String quantity;
                if (args.length < 2) {
                    quantity = "1";
                } else {
                    quantity = args[1];
                }
                if (!StringUtils.isNumeric(quantity)) {
                    sender.sendMessage(messagesFile.getString("error.unknown-number")
                            .replace("%number%", quantity));
                    return true;
                }

                Material material = Material.getMaterial(configFile.getString("item.id").toUpperCase());

                if (material == null) {
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

                int times = 0;

                while (times != Integer.parseInt(quantity)) {
                    inventory.addItem(itemStack);
                    times++;
                }

                sender.sendMessage(messagesFile.getString("command.give")
                        .replace("%number%", quantity));
                return true;
            case "reload":

                if (!sender.hasPermission("recoverhealth.reload")){
                    sender.sendMessage(messagesFile.getString("error.no-permissions"));
                    return true;
                }

                configFile.reload();
                messagesFile.reload();

                sender.sendMessage(messagesFile.getString("command.reload"));
                return true;
            default:
                sender.sendMessage(messagesFile.getString("error.unknown-args"));
                return true;
        }

    }
}
