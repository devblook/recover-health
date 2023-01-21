package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public abstract class Action {

    private String line = "";

    public void execute(Player sender){

    }

    public String getLine(){
        return line;
    }

    public void setLine(String line){
        this.line = line;
    }


}
