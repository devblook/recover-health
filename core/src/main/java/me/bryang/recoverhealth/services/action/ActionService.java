package me.bryang.recoverhealth.services.action;

import me.bryang.recoverhealth.FileCreator;
import me.bryang.recoverhealth.TextUtils;
import me.bryang.recoverhealth.actions.*;
import me.bryang.recoverhealth.services.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActionService implements Service {

    @Inject
    private List<Action> actionList;

    @Inject
    private FileCreator configFile;

    private final Map<ActionType, Action> actionManager = new HashMap<>();

    @Override
    public void init() {

        List<String> actionMessageList = configFile.getStringList("event.actions");

        if (actionMessageList == null){
            return;
        }

        actionManager.put(ActionType.MESSAGE, new SendMessageAction());
        actionManager.put(ActionType.COMMAND, new PerformCommandAction());
        actionManager.put(ActionType.CONSOLECMD, new SendConsoleAction());
        actionManager.put(ActionType.LIGHTING, new SendLightingAction());
        actionManager.put(ActionType.BROADCAST, new BroadcastAction());
        actionManager.put(ActionType.BROADCASTWORLD, new BroadcastWorldAction());
        actionManager.put(ActionType.BROADCASTSOUND, new BroadcastSoundAction());
        actionManager.put(ActionType.SOUND, new SendSoundAction());
        actionManager.put(ActionType.EFFECT, new SendEffectAction());


        for (String message : actionMessageList) {

            String newMessage = TextUtils
                    .replaceFirst(message, "[", "");
            newMessage = TextUtils.replaceFirst(newMessage, "]", " ");

            String format = newMessage.split(" ")[0].toUpperCase();
            String line = newMessage.substring(format.length());

            Action action = actionManager.get(ActionType.valueOf(format));

            if (actionList.contains(action)){
                action = action.clone();
            }

            action.setLine(line);
            actionList.add(action);

        }

    }

}
