package me.bryang.recoverhealth.actions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SendConsoleAction extends Action {

    @Override
    public void execute(Player sender) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getLine());
    }

}
