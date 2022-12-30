package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectAction implements Action {

    private final String value;

    public EffectAction(String value) {
        this.value = value;
    }

    @Override
    public void execute(Player sender) {
        String[] effectPath = value.split(";");

        PotionEffectType effect = PotionEffectType.getByName(effectPath[0]);

        if (effect == null) {
            return;
        }

        sender.addPotionEffect(new PotionEffect(effect, Integer.parseInt(effectPath[1]), Integer.parseInt(effectPath[2])));
    }
}
