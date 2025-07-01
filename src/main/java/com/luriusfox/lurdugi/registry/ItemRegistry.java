package com.luriusfox.lurdugi.registry;

import com.luriusfox.lurdugi.LurDuGiMod;
import com.luriusfox.lurdugi.items.TestItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LurDuGiMod.MODID);

    public static final RegistryObject<Item> TEST_ITEM = ITEMS.register(TestItem.id, () -> new TestItem());
}
