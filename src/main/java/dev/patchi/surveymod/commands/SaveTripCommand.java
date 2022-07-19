package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyLeg;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveTripCommand {

    public SaveTripCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("save").executes((command) -> {
            return saveTrip(command.getSource());
        })));
    }

    private int saveTrip(CommandSourceStack source) throws CommandSyntaxException {

        try {

            SurveyMod.saveTrip();


        } catch (IOException e) {
            e.printStackTrace();
            source.sendFailure(Component.nullToEmpty("Failed to save leg"));
            return 1;
        }

        source.sendSuccess(Component.nullToEmpty("Saved stored data"), true);
        return 0;
    }

}
