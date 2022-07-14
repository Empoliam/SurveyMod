package dev.patchi.surveymod.item;


import dev.patchi.surveymod.SurveyMod;
import dev.patchi.surveymod.item.custom.SurveyStickItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SurveyMod.MOD_ID);

    public static final RegistryObject<Item> SURVEYSTICK = ITEMS.register("surveystick",
            ()-> new SurveyStickItem(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MISC)
                    .stacksTo(1)
            ));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
