package com.luriusfox.lurdugi.registry;

import java.util.function.Supplier;

import com.luriusfox.lurdugi.LurDuGiMod;
import com.luriusfox.lurdugi.items.TestItem;
import com.luriusfox.lurdugi.registry.TabsRegistry.TabType;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LurDuGiMod.MODID);

    public static final RegistryObject<Item> TEST_ITEM = RegisterItems(TestItem.id, () -> new TestItem(), TabType.LURDUGI);


    public static RegistryObject<Item> RegisterItems(String _name, Supplier<? extends Item> _item) {
        return ITEMS.register(_name, _item);
    }

    public static RegistryObject<Item> RegisterItems(String _name, Supplier<? extends Item> _item, TabType _tabType) {
        RegistryObject<Item> _itemObject = RegisterItems(_name, _item);
        TabsRegistry.addToTab(_tabType, () -> _itemObject.get());
        return _itemObject;
    }
}
