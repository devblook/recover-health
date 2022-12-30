package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class BroadcastWorldAction implements Action {

    private final String value;

    public BroadcastWorldAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {

        sender.getWorld().getPlayers()
                .forEach(target -> target.sendMessage(value));
    }
}
