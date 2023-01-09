package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class PerformCommandAction implements Action {

    private final String value;

    public PerformCommandAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        sender.performCommand(value);
    }
}
