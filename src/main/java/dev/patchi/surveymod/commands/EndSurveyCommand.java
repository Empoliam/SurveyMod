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

        source.sendSuccess(Component.nullToEmpty("Ending survey"), true);
        String savedir = System.getProperty("user.home") + "/MinecraftSurveyTool";
        new File(savedir).mkdirs();

        FileWriter writer = null;
        try {

            writer = new FileWriter(savedir + "/" + SurveyMod.surveyName + ".svx", true);

            writer.write("*end" + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
