package me.bryang.recoverhealth.services.action;

import me.bryang.recoverhealth.TextUtils;
import me.bryang.recoverhealth.actions.*;
import me.bryang.recoverhealth.manager.FileManager;
import me.bryang.recoverhealth.services.Service;
import org.bukkit.Bukkit;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionService implements Service {

    @Inject
    private List<Action> actionCacheRegistry;

    @Inject
    private FileManager configFile;


    private final Map<ActionType, Action> actionManager = new HashMap<>();

    @Override
    public void init() {

        actionManager.put(ActionType.MESSAGE, new SendMessageAction());
        actionManager.put(ActionType.COMMAND, new PerformCommandAction());
        actionManager.put(ActionType.CONSOLECMD, new SendConsoleAction());
        actionManager.put(ActionType.LIGHTING, new SendLightingAction());
        actionManager.put(ActionType.BROADCAST, new BroadcastAction());
        actionManager.put(ActionType.BROADCASTWORLD, new BroadcastWorldAction());
        actionManager.put(ActionType.BROADCASTSOUND, new BroadcastSoundAction());
        actionManager.put(ActionType.SOUND, new SendSoundAction());
        actionManager.put(ActionType.EFFECT, new SendEffectAction());


        for (String message : configFile.getStringList("event.actions")) {

            String newMessage = TextUtils
                    .replaceFirst(message, "[", "");
            newMessage = TextUtils.replaceFirst(newMessage, "]", " ");

            String format = newMessage.split(" ")[0].toUpperCase();
            String line = newMessage.substring(format.length());

            Action action = actionManager.get(ActionType.valueOf(format));

            if (actionCacheRegistry.contains(action)){
                action = action.clone();
            }

            action.setLine(line);
            actionCacheRegistry.add(action);

        }

    }

}
