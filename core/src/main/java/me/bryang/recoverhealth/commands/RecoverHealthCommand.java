package me.bryang.recoverhealth.commands;

import me.bryang.recoverhealth.manager.FileManager;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;
import java.util.List;

@InjectAll
@Command(names = {"recoverhealth", "rh", "rhealth"})
public class RecoverHealthCommand implements CommandClass {

    private FileManager configFile;
    @Named("messages")
    private FileManager messagesFile;

    @Command(names = "")
    public void mainSubCommand(@Sender Player sender) {
        messagesFile.getStringList("command.help").forEach(sender::sendMessage);

    }

    @Command(names = "give", permission = "recoverhealth.give")
    public void reloadSubCommand(@Sender Player sender, @OptArg("1") int quantity) {

        Material material = Material.getMaterial(configFile.getString("item.id").toUpperCase());

        if (material == null) {
            Bukkit.getLogger().info("Unknown material!");
            return;
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

        while (times != quantity) {
            inventory.addItem(itemStack);
            times++;
        }

        sender.updateInventory();

        sender.sendMessage(messagesFile.getString("command.give")
                .replace("%number%", String.valueOf(quantity)));

    }

    @Command(names = "reload", permission = "recoverhealth.reload")
    public void reloadSubCommand(@Sender Player sender) {
        configFile.reload();
        messagesFile.reload();

        sender.sendMessage(messagesFile.getString("command.reload"));
    }
}
