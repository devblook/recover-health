package me.bryang.recoverhealth.modules;

import me.bryang.recoverhealth.services.ListenerService;
import me.bryang.recoverhealth.services.Service;
import me.bryang.recoverhealth.services.commands.ActionService;
import me.bryang.recoverhealth.services.commands.CommandService;
import team.unnamed.inject.AbstractModule;

public class ServiceModule extends AbstractModule {

    @Override
    public void configure() {
        multibind(Service.class)
                .asSet()
                .to(CommandService.class)
                .to(ListenerService.class)
                .to(ActionService.class);
    }
}
