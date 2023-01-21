package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class SendLightingAction extends Action {


    @Override
    public void execute(Player sender) {
        sender.getWorld().strikeLightning(sender.getLocation());
    }

}
