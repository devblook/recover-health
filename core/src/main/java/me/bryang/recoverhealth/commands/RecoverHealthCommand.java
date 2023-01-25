package me.bryang.recoverhealth.commands;

import me.bryang.recoverhealth.FileCreator;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import team.unnamed.inject.InjectAll;

import javax.inject.Named;

@InjectAll
@Command(names = {"recoverhealth", "rh", "rhealth"})
public class RecoverHealthCommand implements CommandClass {

    private FileCreator configFile;

    @Named("messages")
    private FileCreator messagesFile;

    @Command(names = {"", "help"})
    public void mainSubCommand(@Sender Player sender) {
        messagesFile.getStringList("command.help")
                .forEach(sender::sendMessage);

    }

    @Command(names = "give", permission = "recoverhealth.give")
    public void reloadSubCommand(@Sender Player sender, @OptArg("1") int quantity, @OptArg Player target) {

        Player senderTarget;

        if (target != null){
            senderTarget = target;
        }else{
            senderTarget = sender;
        }

        ItemStack itemStack = configFile.getItemStack("item");

        Inventory inventory = senderTarget.getInventory();
        int times = 0;

        while (times != quantity) {
            inventory.addItem(itemStack);
            times++;
        }

        senderTarget.updateInventory();

        if (senderTarget.getName().equalsIgnoreCase(sender.getName())) {
            sender.sendMessage(messagesFile.getString("command.give.player")
                    .replace("%number%", String.valueOf(quantity)));
        }else{

            sender.sendMessage(messagesFile.getString("command.give.target")
                    .replace("%number%", String.valueOf(quantity))
                    .replace("%target%", senderTarget.getName()));
        }


    }

    @Command(names = "reload", permission = "recoverhealth.reload")
    public void reloadSubCommand(@Sender Player sender) {

        configFile.reload();
        messagesFile.reload();

        sender.sendMessage(messagesFile.getString("command.reload"));
    }
}
