package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyMod;
import dev.patchi.surveymod.SurveyTrip;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class ClearSurveyCommand {

    public ClearSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("clear").executes((command) -> {
            return clearSurvey(command.getSource());
        })));
    }

    private int clearSurvey(CommandSourceStack source) throws CommandSyntaxException {

        source.sendSuccess(Component.nullToEmpty("Clearing stored survey data"), true);
        SurveyMod.activeTrip.clearList();

        return 0;
    }

}
