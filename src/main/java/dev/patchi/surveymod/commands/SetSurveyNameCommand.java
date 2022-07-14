package dev.patchi.surveymod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.patchi.surveymod.SurveyMod;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class SetSurveyNameCommand {

    public SetSurveyNameCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("survey")
                .then(Commands.literal("name")
                        .then(Commands.argument("name", StringArgumentType.string()).executes((command) -> {

                            SurveyMod.surveyName = StringArgumentType.getString(command,"name");
                            command.getSource().sendSuccess(Component.nullToEmpty("Set survey filename: " + SurveyMod.surveyName), true);
                            return 0;

        }))));
    }

}
