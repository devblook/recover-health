package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public class PerformCommandAction extends Action {


    @Override
    public void execute(Player sender) {
        sender.performCommand(getLine());
    }


}
