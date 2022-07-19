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

        FileWriter writer = null;
        try {


            String savedir = System.getProperty("user.home") + "/MinecraftSurveyTool";
            new File(savedir).mkdirs();

            writer = new FileWriter(savedir + "/" + SurveyMod.surveyName + ".svx", true);

            writer.write("*begin" + System.lineSeparator());
            writer.write("*data normal from to tape compass clino" + System.lineSeparator());

            for (SurveyLeg leg : SurveyMod.activeTrip.getLegList()) {
                writer.write(leg.toString() + System.lineSeparator());
            }

            writer.write("*end" + System.lineSeparator());
            writer.close();

            SurveyMod.activeTrip.clearList();

            source.sendSuccess(Component.nullToEmpty("Saving stored data"), true);

        } catch (IOException e) {
            e.printStackTrace();
            source.sendFailure(Component.nullToEmpty("Failed to save leg"));
        }

        return 0;
    }

}
