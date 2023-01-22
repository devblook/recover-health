package me.bryang.recoverhealth.listeners;

import me.bryang.recoverhealth.actions.Action;
import me.bryang.recoverhealth.manager.FileManager;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import team.unnamed.inject.InjectAll;

import java.util.List;

@InjectAll
public class DamageListener implements Listener {

    private FileManager configFile;
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

        if (itemStack.getType() != Material.getMaterial(configFile.getString("item.id").toUpperCase())) {
            return;
        }

        if (itemStack.getItemMeta() == null) {
            return;
        }

        if (!itemStack.getItemMeta().getDisplayName()
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
