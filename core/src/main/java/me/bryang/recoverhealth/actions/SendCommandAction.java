package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class SendCommandAction implements Action {

    private final String value;

    public SendCommandAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        sender.performCommand(value);
    }
}
