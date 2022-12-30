package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class SendLightingAction implements Action {

    private final String value;

    public SendLightingAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        sender.getWorld().strikeLightning(sender.getLocation());
    }
}
