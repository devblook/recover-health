package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;

public abstract class Action implements Cloneable{

    private String line = "";

    public void execute(Player sender){

    }

    public String getLine(){
        return line;
    }

    public void setLine(String line){
        this.line = line;
    }

    public Action clone() {
        Action action;
        try {
            action = (Action) super.clone();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return action;
    }

}
