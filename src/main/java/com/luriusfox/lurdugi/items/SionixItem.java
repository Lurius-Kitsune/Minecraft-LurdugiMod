package com.luriusfox.lurdugi.items;

import com.luriusfox.lurdugi.registry.ItemRegistry;
import com.luriusfox.lurdugi.tools.Color;
import com.luriusfox.lurdugi.tools.Gradient;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SionixItem extends Item {

    public static final String id = "sionix";

    public SionixItem() {
        super(new Item.Properties().stacksTo(200)
        .food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0.2f).build())
        .setId(ItemRegistry.ITEMS.key(id)));
    }

    //apply effect on eat
    @Override
     public ItemStack finishUsingItem(ItemStack _stack, Level _world, LivingEntity _entity) {
        if (!_world.isClientSide) {
            _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0)); 
            // 200 ticks = 10 secondes (20 ticks = 1 sec), niveau 0
        }
        return super.finishUsingItem(_stack, _world, _entity);
    }

    // Apply gradient name
    @Override
    public Component getName(ItemStack stack) {
        // Récupère le nom traduit normalement
        String _name = Component.translatable(this.getDescriptionId()).getString();
        // Assuming GradientStringComponent is a method that creates a gradient string component
        return Gradient.GradientStringComponent(_name, Color.fromHex(0xCB2D3E), Color.fromHex(0x004FFF)); // Example colors, replace with your desired colors
    }
}