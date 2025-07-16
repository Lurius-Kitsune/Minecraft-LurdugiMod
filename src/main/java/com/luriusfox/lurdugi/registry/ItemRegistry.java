package com.luriusfox.lurdugi.registry;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.luriusfox.lurdugi.LurDuGiMod;
import com.luriusfox.lurdugi.items.LuriusItem;
import com.luriusfox.lurdugi.items.SionixItem;
import com.luriusfox.lurdugi.items.SionixLockItem;
import com.luriusfox.lurdugi.items.TestTNT;
import com.luriusfox.lurdugi.registry.TabsRegistry.TabType;
import com.luriusfox.lurdugi.test.TestItem;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LurDuGiMod.MODID);

    public static final RegistryObject<Item> TEST_ITEM = RegisterItems(TestItem.id, () -> new TestItem());

    public static final RegistryObject<Item> SIONIX_LOCK_ITEM = RegisterItems(SionixLockItem.id, () -> new SionixLockItem(), TabType.LURDUGI);
    public static final RegistryObject<Item> LURIUS_ITEM = RegisterItems(LuriusItem.id, () -> new LuriusItem(), TabType.LURDUGI);
    public static final RegistryObject<Item> SIONIX_ITEM = RegisterItems(SionixItem.id,  () -> new SionixItem(), TabType.LURDUGI);
    public static final RegistryObject<Item> TEST_TNT = RegisterItems(TestTNT.id, () -> new TestTNT(), TabType.LURDUGI);


    public static RegistryObject<Item> RegisterItems(String _name, Supplier<? extends Item> _item) {
        return ITEMS.register(_name, _item);
    }

    public static RegistryObject<Item> RegisterItems(String _name, Supplier<? extends Item> _item, TabType _tabType) {
        RegistryObject<Item> _itemObject = RegisterItems(_name, _item);
        TabsRegistry.addToTab(_tabType, () -> _itemObject.get());
        return _itemObject;
    }

    public static Set<Item> getItems() {
        return ITEMS.getEntries().stream()
                .map(RegistryObject::get)
                .collect(Collectors.toSet());
    }
}
