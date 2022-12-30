package me.bryang.recoverhealth.actions;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAction implements Action {

    private final String value;

    public SoundAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        String[] soundPath = value.split(";");

        Sound sound;

        try {
            sound = Sound.valueOf(soundPath[0]);
        } catch (IllegalArgumentException illegalArgumentException) {
            Bukkit.getLogger().info("Error! The sound no exists: " + soundPath[0]);
            return;
        }

        sender.playSound(sender.getLocation(), sound, Float.parseFloat(soundPath[1]), Float.parseFloat(soundPath[2]));

    }
}
