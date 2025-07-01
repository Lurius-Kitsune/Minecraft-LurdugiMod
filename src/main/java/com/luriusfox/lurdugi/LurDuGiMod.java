package com.luriusfox.lurdugi;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import com.luriusfox.lurdugi.registry.BlocksRegistry;
import com.luriusfox.lurdugi.registry.ItemRegistry;
import com.luriusfox.lurdugi.registry.TabsRegistry;

import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;

@Mod(LurDuGiMod.MODID)
public final class LurDuGiMod {
    public static final String MODID = "lurdugi";

    public static Logger LOGGER = Logger.getLogger(MODID);

    private static Vector<DeferredRegister<?>> deferredRegisters = new Vector<DeferredRegister<?>>(
        List.of(ItemRegistry.ITEMS,
        BlocksRegistry.BLOCKS,
        TabsRegistry.CREATIVE_MODE_TABS)
    );

    public LurDuGiMod(FMLJavaModLoadingContext _context) {
        LOGGER.info("LurDuGiMod is loading...");
        RegisterRegistry(_context);

        //RegisterRegistry(_context);
        LOGGER.info("LurDuGiMod has loaded successfully.");
    }


    private void RegisterRegistry(FMLJavaModLoadingContext _context)
    {
        BusGroup _eventBus = _context.getModBusGroup();
        for (DeferredRegister<?> _deferredRegister : deferredRegisters) {
            _deferredRegister.register(_eventBus);
        }
    }
}