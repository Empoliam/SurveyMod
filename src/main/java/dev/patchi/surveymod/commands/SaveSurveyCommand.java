package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.io.IOException;

public class SaveSurveyCommand {

    public SaveSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("save").executes((command) -> {
            return saveTrip(command.getSource());
        })));
    }

    private int saveTrip(CommandSourceStack source) throws CommandSyntaxException {

        try {

            SurveyMod.saveSurvey();


        } catch (IOException e) {
            e.printStackTrace();
            source.sendFailure(Component.nullToEmpty("Failed to save leg"));
            return 1;
        }

        source.sendSuccess(Component.nullToEmpty("Saved stored data"), true);
        return 0;
    }

}
