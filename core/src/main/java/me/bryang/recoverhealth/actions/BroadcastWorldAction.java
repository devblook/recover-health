package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class BroadcastWorldAction extends Action {

    @Override
    public void execute(Player sender) {

        sender.getWorld().getPlayers()
                .forEach(target -> target.sendMessage(getLine()));
    }

}
