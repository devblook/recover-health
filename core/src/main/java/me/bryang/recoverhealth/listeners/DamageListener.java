package me.bryang.recoverhealth.listeners;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.actions.Action;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import team.unnamed.inject.InjectAll;

import java.util.List;

@InjectAll
public class DamageListener implements Listener {

    private FileCreator configFile;
    private List<Action> actionList;


    @EventHandler
    public void onDamage(EntityDamageEvent event) {

        if (event.getEntityType() != EntityType.PLAYER) {
            return;
        }

        Player sender = (Player) event.getEntity();

        if (sender.getHealth() > event.getDamage()) {
            return;
        }

        ItemStack itemStack = sender.getInventory().getItemInMainHand();

        if (itemStack.getType() != configFile.getMaterial("item.id")){
            return;
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        if (!itemMeta.getDisplayName()
                .equalsIgnoreCase(configFile.getString("item.name"))) {
            return;
        }

        event.setCancelled(true);

        PlayerInventory senderInventory = sender.getInventory();

        if (itemStack.getAmount() < 2) {
            senderInventory.clear(senderInventory.getHeldItemSlot());
        } else {
            itemStack.setAmount(itemStack.getAmount() - 1);
            senderInventory.setItem(senderInventory.getHeldItemSlot(), itemStack);
        }

        sender.setHealth(configFile.getInt("options.add-health"));

        actionList
                .forEach(action -> action.execute(sender));
    }
}
