package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import dev.patchi.surveymod.SurveyMod;
import dev.patchi.surveymod.item.custom.SurveyStickItem;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class SaveSurveyCommand {

    public SaveSurveyCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey").then(Commands.literal("save").executes((command) -> {
            return saveSurvey(command.getSource());
        })));
    }

    private int saveSurvey(CommandSourceStack source) throws CommandSyntaxException {

        source.sendSuccess(Component.nullToEmpty("Saving survey"), true);
        String savedir = System.getProperty("user.home") + "/MinecraftSurveyTool";
        new File(savedir).mkdirs();

        FileWriter writer = null;
        try {

            writer = new FileWriter(savedir + "/" + SurveyMod.surveyName + ".svx", true);

            writer.write("*begin" + System.lineSeparator());
            writer.write("*data normal from to tape compass clino" + System.lineSeparator());

            for (String str : SurveyMod.surveyPoints) {
                writer.write(str + System.lineSeparator());
            }

            writer.write("*end" + System.lineSeparator());
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
