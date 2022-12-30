package me.bryang.recoverhealth.services.commands;

import me.bryang.recoverhealth.manager.FileManager;
import me.fixeddev.commandflow.Namespace;
import me.fixeddev.commandflow.translator.TranslationProvider;

import javax.inject.Inject;
import javax.inject.Named;

public class CommandTranslator implements TranslationProvider {

    @Inject
    @Named("messages")
    private FileManager messagesFile;

    @Override
    public String getTranslation(Namespace namespace, String key) {

        switch (key) {
            case "command.subcommand.invalid":
                return messagesFile.getString("error.unknown-argument");

            case "sender.only-player":
                return messagesFile.getString("error.no-console");

            case "invalid.integer":
            case "invalid.double":
                return messagesFile.getString("error.unknown-number")
                        .replace("%number%", "%s");

            case "command.no-permission":
                return messagesFile.getString("error.no-permission");

        }
        return "Si ves este mensaje, contacta con el programador.";
    }

}
