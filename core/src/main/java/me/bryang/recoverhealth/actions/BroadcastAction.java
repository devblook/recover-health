package me.bryang.recoverhealth.actions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BroadcastAction extends Action {

    @Override
    public void execute(Player sender) {
        Bukkit.broadcastMessage(getLine());
    }

}
