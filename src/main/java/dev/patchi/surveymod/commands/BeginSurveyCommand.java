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

public class BeginSurveyCommand {

    public BeginSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("begin").executes((command) -> {
            return beginSurvey(command.getSource());
        })));
    }

    private int beginSurvey(CommandSourceStack source) throws CommandSyntaxException {

        source.sendSuccess(Component.nullToEmpty("Beginning new survey"), true);
        try {
            SurveyMod.beginSurvey();
        } catch (IOException e) {
            source.sendFailure(Component.nullToEmpty("Failed to create survey file"));
            e.printStackTrace();
        }

        return 0;
    }

}
