package com.luriusfox.lurdugi.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class TestTNT extends BaseItem {

    public static final String id = "test_tnt";

    public TestTNT() {
        super(id, new Item.Properties().stacksTo(64));
    }

    @Override    public boolean isFoil(ItemStack stack) {
        return true; // Always show as enchanted
    }

}
