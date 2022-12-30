package me.bryang.recoverhealth.commands;

import me.bryang.recoverhealth.PluginService;
import me.bryang.recoverhealth.manager.FileManager;
import me.bryang.recoverhealth.utils.TextUtils;
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

    public RecoverHealthCommand(PluginService pluginService) {
        this.pluginService = pluginService;

        this.configFile = pluginService.getFiles().getConfigFile();
        this.messagesFile = pluginService.getFiles().getMessagesFile();
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!(commandSender instanceof Player)) {
            System.out.println(messagesFile.getString("error.console"));
            return true;
        }

        Player sender = (Player) commandSender;


        if (args.length < 1) {
            messagesFile.getStringList("command.help")
                    .forEach(sender::sendMessage);
            return true;
        }

        switch (args[0]) {
            case "give":


                if (!sender.hasPermission("recoverhealth.give")) {
                    sender.sendMessage(messagesFile.getString("error.no-permissions"));
                    return true;
                }

                String quantity;
                if (args.length < 2) {
                    quantity = "1";
                } else {
                    quantity = args[1];
                }
                if (!TextUtils.isNumeric(quantity)) {
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
                itemMeta.setDisplayName(configFile.getString("item.name"));

                if (!configFile.getStringList("item.lore").isEmpty()) {
                    List<String> lore = configFile.getStringList("item.lore");
                    itemMeta.setLore(lore);
                }

                itemStack.setItemMeta(itemMeta);

                Inventory inventory = sender.getInventory();
                int times = 0;

                while (times != Integer.parseInt(quantity)) {
                    inventory.addItem(itemStack);
                    times++;
                }
                sender.updateInventory();

                sender.sendMessage(messagesFile.getString("command.give")
                        .replace("%number%", quantity));
                return true;
            case "reload":

                if (!sender.hasPermission("recoverhealth.reload")) {
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
