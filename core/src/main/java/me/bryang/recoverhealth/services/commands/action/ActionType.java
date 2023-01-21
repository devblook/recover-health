package me.bryang.recoverhealth.services.commands.action;

public enum ActionType {
    MESSAGE(7),
    COMMAND(7),
    CONSOLECMD(10),
    LIGHTING(8),
    BROADCAST(9),
    BROADCASTWORLD(14),
    BROADCASTSOUND(14),
    SOUND(5),
    EFFECT(6);


    private final int formatSize;

    ActionType(int formatSize){
        this.formatSize = formatSize;
    }

    public int getFormatSize() {
        return formatSize;
    }
}
