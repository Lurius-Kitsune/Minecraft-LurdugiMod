package com.luriusfox.lurdugi.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.luriusfox.lurdugi.LurDuGiMod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class TabsRegistry {
    
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LurDuGiMod.MODID);
    
    public static List<Supplier<? extends ItemLike>> LURDUGI_TAB_LIST = new ArrayList<Supplier<? extends ItemLike>>();

    public static final RegistryObject<CreativeModeTab> COPPER_TAB = CREATIVE_MODE_TABS.register("lurdugi_tab",
    () -> CreativeModeTab.builder()
    .icon(() -> new ItemStack(ItemRegistry.TEST_ITEM.get()))
    .title(Component.translatable("lurdugi_tab"))
    .displayItems((_itemDisplayParameters, _output) -> {
        for (Supplier<? extends ItemLike> _item : LURDUGI_TAB_LIST) {
            _output.accept(_item.get());
        }
    })
    .build()
    );


    public static enum TabType {
        LURDUGI
    }

    public static void addToTab(TabType type, Supplier<? extends ItemLike> item) {
        switch (type) {
            case LURDUGI:
                LURDUGI_TAB_LIST.add(item);
                break;
            default:
                throw new IllegalArgumentException("Unknown tab type: " + type);
        }
    }
}