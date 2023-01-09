package me.bryang.recoverhealth.services.commands;

import me.bryang.recoverhealth.actions.*;
import me.bryang.recoverhealth.api.CacheRegistry;
import me.bryang.recoverhealth.manager.FileManager;
import me.bryang.recoverhealth.services.Service;
import team.unnamed.inject.InjectAll;

@InjectAll
public class ActionService implements Service {

    private CacheRegistry<Action> actionCacheRegistry;
    private FileManager configFile;

    @Override
    public void init() {
        for (String message : configFile.getStringList("event.actions")) {

            if (message.startsWith("[MESSAGE]")) {
                actionCacheRegistry.add(new SendMessageAction(message.substring(9)));
                continue;
            }

            if (message.startsWith("[COMMAND]")) {
                actionCacheRegistry.add(new PerformCommandAction(message.substring(9)));
                continue;
            }

            if (message.startsWith("[CONSOLECMD]")) {
                actionCacheRegistry.add(new SendConsoleAction(message.substring(12)));
                continue;
            }

            if (message.startsWith("[LIGHTING]")) {
                actionCacheRegistry.add(new SendLightingAction(""));
                continue;
            }

            if (message.startsWith("[BROADCAST]")) {
                actionCacheRegistry.add(new BroadcastAction(message.substring(11)));
                continue;
            }

            if (message.startsWith("[BROADCASTWORLD]")) {
                actionCacheRegistry.add(new BroadcastWorldAction(message.substring(16)));
                continue;
            }

            if (message.startsWith("[SOUND]")) {
                actionCacheRegistry.add(new SendSoundAction(message.substring(7)));
                continue;
            }

            if (message.startsWith("[BROADCASTSOUND]")) {
                actionCacheRegistry.add(new BroadcastSoundAction(message.substring(7)));
                continue;
            }

            if (message.startsWith("[EFFECT]")) {
                actionCacheRegistry.add(new SendEffectAction(message.substring(8)));
            }

        }
    }

}
