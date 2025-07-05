package com.luriusfox.lurdugi.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LuriusItem extends BaseItem {

    public static final String id = "luwurius";

    public LuriusItem() {
        super(id, new Item.Properties().stacksTo(200)
        .food(new FoodProperties.Builder().alwaysEdible().nutrition(1).saturationModifier(0.2f).build()));
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
}