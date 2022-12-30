package me.bryang.recoverhealth.actions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastAction implements Action {

    private final String value;

    public BroadcastAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        Bukkit.broadcastMessage(value);
    }
}
