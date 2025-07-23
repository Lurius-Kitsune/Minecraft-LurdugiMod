package com.luriusfox.lurdugi.registry;

import java.util.Set;

import com.luriusfox.lurdugi.LurDuGiMod;
import com.luriusfox.lurdugi.test.TestTNTBlockEntity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class EntityRegistry {
    

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, LurDuGiMod.MODID);


    // Register your BlockEntityTypes here
    public static final RegistryObject<BlockEntityType<TestTNTBlockEntity>> TEST_TNT =  BLOCK_ENTITIES.register("test_tnt",
            () -> new BlockEntityType<>(TestTNTBlockEntity::new, Set.of(BlocksRegistry.TEST_TNT.get()))
    );
}