package dev.patchi.surveymod.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;


public class ClearSurveyCommand {

    public ClearSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey")
                .then(Commands.literal("clear")
                        .then(Commands.literal("active")
                                .executes((command) -> {
                                    return clearActiveSurvey(command.getSource());
                                }))
                        .then(Commands.literal("all")
                                .executes((command) -> {
                                    return clearAllSurvey(command.getSource());
                                }))
                )
        );
    }

    private int clearActiveSurvey(CommandSourceStack source) throws CommandSyntaxException {

        source.sendSuccess(Component.nullToEmpty("Clearing active survey trip data"), true);
        SurveyMod.activeTrip.clearList();

        return 0;
    }

    private int clearAllSurvey(CommandSourceStack source) throws CommandSyntaxException {

        source.sendSuccess(Component.nullToEmpty("Clearing all stored survey data"), true);
        SurveyMod.activeTrip = null;
        SurveyMod.recordedTrips.clear();

        return 0;
    }

}
