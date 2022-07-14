package dev.patchi.surveymod.events;


import dev.patchi.surveymod.SurveyMod;
import dev.patchi.surveymod.commands.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = SurveyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new SaveSurveyCommand(event.getDispatcher());
        new ClearSurveyCommand(event.getDispatcher());
        new SetSurveyNameCommand(event.getDispatcher());
        new BeginSurveyCommand(event.getDispatcher());
        new EndSurveyCommand(event.getDispatcher());
        new SetCaveNameCommand(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

}
