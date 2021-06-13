package me.bryangaming.recoverhealth.commands;

import me.bryangaming.recoverhealth.PluginService;
import me.bryangaming.recoverhealth.manager.FileManager;
import me.bryangaming.recoverhealth.manager.SenderManager;
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
    private final SenderManager senderManager;

    public RecoverHealthCommand(PluginService pluginService){
        this.pluginService = pluginService;

        this.configFile = pluginService.getFiles().getConfig();
        this.senderManager = pluginService.getSender();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            System.out.println(TextUtils.colorize(configFile.getString("error.console")));
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1){
            senderManager.sendMessage(player, configFile.getStringList("command.help"));
            return true;
        }

        switch (args[0]) {
            case "give":
                String quantity;
                if (args.length < 2) {
                    quantity = "1";
                } else {
                    quantity = args[1];
                }
                if (!StringUtils.isNumeric(quantity)) {
                    senderManager.sendMessage(player, configFile.getString("error.unknown-number"));
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

                Inventory inventory = player.getInventory();
                int times = 0;

                while (times != Integer.parseInt(quantity)) {
                    inventory.addItem(itemStack);
                    times++;
                }
                player.updateInventory();

                senderManager.sendMessage(player, configFile.getString("command.give")
                        .replace("%qnty%", quantity));
                return true;
            case "reload":
                pluginService.getFiles().getConfig().reload();
                senderManager.sendMessage(player, configFile.getString("command.reload"));
                return true;
            default:
                senderManager.sendMessage(player, configFile.getString("error.unknown-args"));
                return true;
        }

    }
}
