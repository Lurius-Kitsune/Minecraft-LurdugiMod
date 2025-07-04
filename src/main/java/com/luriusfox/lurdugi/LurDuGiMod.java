package com.luriusfox.lurdugi;

import java.util.List;
import java.util.Vector;

import com.luriusfox.lurdugi.registry.BlocksRegistry;
import com.luriusfox.lurdugi.registry.ItemRegistry;
import com.luriusfox.lurdugi.registry.TabsRegistry;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import org.slf4j.Logger;

@Mod(LurDuGiMod.MODID)
public final class LurDuGiMod {
    public static final String MODID = "lurdugi";

    public static Logger LOGGER = LogUtils.getLogger();

    private static Vector<DeferredRegister<?>> deferredRegisters = new Vector<DeferredRegister<?>>(
        List.of(ItemRegistry.ITEMS,
        BlocksRegistry.BLOCKS,
        TabsRegistry.CREATIVE_MODE_TABS)
    );

    public LurDuGiMod(FMLJavaModLoadingContext _context) {
        LOGGER.info("LurDuGiMod is loading...");
        BusGroup _eventBus = _context.getModBusGroup();

        // Register the commonSetup method for modloading
        FMLCommonSetupEvent.getBus(_eventBus).addListener(this::commonSetup);
        

        RegisterRegistry(_eventBus);

        // Register the item to a creative tab
        BuildCreativeModeTabContentsEvent.getBus(_eventBus).addListener(LurDuGiMod::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        _context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        LOGGER.info("LurDuGiMod has loaded successfully.");
    }


    private void RegisterRegistry(BusGroup _eventBus)
    {
        for (DeferredRegister<?> _deferredRegister : deferredRegisters) {
            _deferredRegister.register(_eventBus);
        }
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add custom item to default creative tab
    private static void addCreative(BuildCreativeModeTabContentsEvent event) {
        // if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
        //     event.accept(EXAMPLE_BLOCK_ITEM);
    }
    
    // list of all event

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        // This class is empty, but it is used to register client-side events
        // You can add methods here to handle client-side events if needed

         @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

}