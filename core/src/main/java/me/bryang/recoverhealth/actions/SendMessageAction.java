package me.bryang.recoverhealth.actions;

import me.bryang.recoverhealth.TextUtils;
import org.bukkit.entity.Player;

public class SendMessageAction extends Action {

    @Override
    public void execute(Player sender) {
        String line = TextUtils.replaceFirstFromIndex(getLine(), 0, "");

        sender.sendMessage(line);


    }

}
