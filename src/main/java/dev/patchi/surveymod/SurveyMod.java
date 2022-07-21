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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public static List<SurveyTrip> recordedTrips = new ArrayList<SurveyTrip>();

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

        activeTrip = null;
        recordedTrips.clear();

        FileWriter writer;
        String surveyDirectory = System.getProperty("user.home") + "/MinecraftSurveyTool";
        new File(surveyDirectory).mkdirs();

        surveyFileName = surveyDirectory + "/" + SurveyMod.surveyName + ".svx";

        writer = new FileWriter(surveyFileName);
        writer.write("*begin " + caveName + System.lineSeparator());
        writer.close();

    }

    public static void saveSurvey() throws IOException {

        FileWriter writer = new FileWriter(surveyFileName, true);

        for(SurveyTrip T : recordedTrips) {

            if(T.saved) continue;

            writer.write("*begin " + T.tripName + System.lineSeparator());
            writer.write("*data normal from to tape compass clino" + System.lineSeparator());

            for (SurveyLeg leg : T.getLegList()) {
                writer.write(leg.toString() + System.lineSeparator());
            }

            writer.write("*end " + T.tripName + System.lineSeparator());
            T.saved = true;

        }

        writer.close();

    }

    public static void newTrip(String name) {

        if(name == "") {
            name = "trip" + recordedTrips.size();
        }

        recordedTrips.add(new SurveyTrip(name));
        activeTrip = recordedTrips.get(recordedTrips.size()-1);

    }

    public static void endSurvey() throws IOException {

        FileWriter writer = new FileWriter(surveyFileName, true);

        Set<String> allStations = new HashSet<>();

        for(SurveyTrip T : recordedTrips) {

            for(String s : T.stationList) {
                writer.write("*equate " + T.tripName + "." + s + " " + s + System.lineSeparator());
                allStations.add(s);
            }

        }

        writer.write("*end " + caveName + System.lineSeparator());

        for(String s : allStations) {
            writer.write("*equate " + caveName + "." + s + " " + s + System.lineSeparator());
        }

        writer.close();

    }

}
