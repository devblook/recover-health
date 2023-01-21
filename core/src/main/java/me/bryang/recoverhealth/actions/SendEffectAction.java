package me.bryang.recoverhealth.actions;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SendEffectAction extends Action {

    @Override
    public void execute(Player sender) {
        String[] effectPath = getLine()
                .replace(" ", "")
                .split(";");

        PotionEffectType effect = PotionEffectType.getByName(effectPath[0]);

        if (effect == null) {
            return;
        }

        sender.addPotionEffect(new PotionEffect(effect, Integer.parseInt(effectPath[1]) * 20, Integer.parseInt(effectPath[2])));
    }

}
