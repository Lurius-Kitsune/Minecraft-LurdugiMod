package com.luriusfox.lurdugi.items;

import com.luriusfox.lurdugi.registry.ItemRegistry;
import net.minecraft.world.item.Item;

public class BaseItem extends Item {

    public BaseItem(String _id, Item.Properties _properties) {
        super(_properties.setId(ItemRegistry.ITEMS.key(_id)));
    }
}