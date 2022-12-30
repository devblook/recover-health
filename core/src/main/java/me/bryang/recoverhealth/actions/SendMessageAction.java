package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class SendMessageAction implements Action {

    private final String value;

    public SendMessageAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        sender.sendMessage(value);

    }
}
