package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EndSurveyCommand {

    public EndSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("end").executes((command) -> {
            return endSurvey(command.getSource());
        })));
    }

    private int endSurvey(CommandSourceStack source) throws CommandSyntaxException {


        try {

            SurveyMod.endSurvey();

        } catch (IOException e) {
            source.sendFailure(Component.nullToEmpty("Failed to save survey file."));
            e.printStackTrace();
            return 1;
        }

        source.sendSuccess(Component.nullToEmpty("Ending survey"), true);
        return 0;
    }

}
