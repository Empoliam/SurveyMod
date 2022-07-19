package dev.patchi.surveymod;

import com.mojang.logging.LogUtils;
import dev.patchi.surveymod.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SurveyMod.MOD_ID)
public class SurveyMod {

    public static final String MOD_ID = "surveymod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static String surveyName = "output";
    public static String caveName = "cave";
    public static String surveyFileName;

    public static SurveyTrip activeTrip;
    public static List<SurveyTrip> recordedLegs = new ArrayList<SurveyTrip>();

    public SurveyMod() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(eventBus);

        eventBus.addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }

    public static void beginSurvey() throws IOException {

        FileWriter writer;
        String surveyDirectory = System.getProperty("user.home") + "/MinecraftSurveyTool";
        new File(surveyDirectory).mkdirs();

        surveyFileName = surveyDirectory + "/" + SurveyMod.surveyName + ".svx";

        writer = new FileWriter(surveyFileName);
        writer.write("*begin " + caveName + System.lineSeparator());
        writer.close();

        recordedLegs.add(new SurveyTrip(""));
        activeTrip = recordedLegs.get(recordedLegs.size()-1);

    }

    public static void saveTrip() throws IOException {

        FileWriter writer;

        writer = new FileWriter(surveyFileName, true);

        writer.write("*begin" + System.lineSeparator());
        writer.write("*data normal from to tape compass clino" + System.lineSeparator());

        for (SurveyLeg leg : activeTrip.getLegList()) {
            writer.write(leg.toString() + System.lineSeparator());
        }

        writer.write("*end" + System.lineSeparator());
        writer.close();

        activeTrip.clearList();

    }

    public static void endSurvey() throws IOException {

        FileWriter writer = new FileWriter(surveyFileName, true);

        writer.write("*end " + caveName + System.lineSeparator());
        writer.close();

    }

}
