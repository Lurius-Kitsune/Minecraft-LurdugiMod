package com.luriusfox.lurdugi.items;

import java.awt.Color;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.luriusfox.lurdugi.tools.ColorTool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

public class SionixItem extends BaseItem {

    public static final String id = "sionix";

    Color start = new Color(0xCB2D3E); // Red
    Color end = new Color(0x004FFF); // Blue
    boolean isGodUser = false;

    public SionixItem() {
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

    // Apply gradient name
    @Override
    public Component getName(ItemStack _stack) {
        // Récupère le nom traduit normalement
        return ColorTool.ComputeStringComponent(super.getName(_stack).getString(), !isGodUser, start, end);
    }
    
    @SuppressWarnings("deprecation") // TODO REMOVE THIS
    @Override
    public void appendHoverText(ItemStack p_41421_, TooltipContext p_333372_, TooltipDisplay p_396484_,
            Consumer<Component> p_392123_, TooltipFlag p_41424_) {
        // Add custom description
        p_392123_.accept(ColorTool.ComputeStringComponent("Secret's Item", !isGodUser, start, end));
        p_392123_.accept(ColorTool.ComputeStringComponent(Component.translatable("item.lurdugi.sionix.desc").getString(), !isGodUser, start, end));
        // NOTE Find a solution
        super.appendHoverText(p_41421_, p_333372_, p_396484_, p_392123_, p_41424_);
    }

    // tick
    @Override
    public void inventoryTick(ItemStack _stack, Level _level, Entity _entity, @Nullable EquipmentSlot _slot, int _slotIndex) {
        
        //make random color
        if (_entity instanceof Player _player) {
            // Apply a random color effect every 20 ticks
            // if (_level.getGameTime() % 5 == 0) {
            //     int _randomColor = (int) (Math.random() * 0xFFFFFF);
            //     start = Color.fromHex(_randomColor);
            //     end = Color.fromHex(_randomColor ^ 0xFFFFFF); // Invert color for contrast
            // }
            if(_player.getName().getString().equals("LuriusFox") != isGodUser) isGodUser = !isGodUser; // Toggle isGodUser for testing purposes
        }

        super.inventoryTick(_stack, _level, _entity, _slot, _slotIndex);
    }
}