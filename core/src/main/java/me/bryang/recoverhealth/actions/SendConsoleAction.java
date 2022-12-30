package me.bryang.recoverhealth.actions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SendConsoleAction implements Action {

    private final String value;

    public SendConsoleAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), value);
    }
}
