package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class SendMessageAction extends Action {

    @Override
    public void execute(Player sender) {
        sender.sendMessage(getLine());

    }

}
